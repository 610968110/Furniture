package com.furniture.ui.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.IBinder;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.furniture.Config;
import com.furniture.R;
import com.furniture.base.BaseActivity;
import com.furniture.bean.LoginBean;
import com.furniture.bean.SocketBean;
import com.furniture.bean.UserInfo;
import com.furniture.bean.json.GetUUID;
import com.furniture.bean.json.SendUUID;
import com.furniture.constant.DoMain;
import com.furniture.databinding.ActivityLoginBinding;
import com.furniture.injector.components.AppComponent;
import com.furniture.injector.components.DaggerActivityComponent;
import com.furniture.injector.modules.ActivityModule;
import com.furniture.manager.SocketManager;
import com.furniture.service.CoreService;
import com.furniture.ui.view.TopBar;
import com.furniture.utils.GsonUtil;
import com.vilyever.socketclient.SocketClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import lbx.xtoollib.XIntent;
import lbx.xtoollib.XTools;
import lbx.xtoollib.phone.xLogUtil;

import static com.furniture.Config.IP;
import static com.furniture.Config.PORT;

/**
 * @author lbx
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.tb_login)
    TopBar topBar;
    LoginBean mLoginBean;
    private UserInfo mUserInfo;
    private CoreService mCoreService;
    private Snackbar mSnackbar;

    public static XIntent getIntent(Context context) {
        return new XIntent(context, LoginActivity.class);
    }

    @Override
    public int getLayoutID() {
        EventBus.getDefault().register(this);
        return R.layout.activity_login;
    }

    @Override
    public void bindComponent(AppComponent appComponent) {
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(appComponent)
                .build();
        mActivityComponent.inject(this);
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {
        ActivityLoginBinding b = (ActivityLoginBinding) binding;
        mLoginBean = new LoginBean();
        b.setLoginBean(mLoginBean);
    }


    @Override
    public void initView(View view) {
        topBar.setTitle("登录");
    }

    @Override
    public void initData() {
        mLoginBean.setAccount(XTools.SpUtil().getString(DoMain.SP_ACCOUNT_SAVE, ""));
        mLoginBean.setPassword(XTools.SpUtil().getString(DoMain.SP_PWD_SAVE, ""));
        mLoginBean.setIp(XTools.SpUtil().getString(DoMain.SP_IP_SAVE, ""));
        if (Config.DEBUG) {
            mLoginBean.setAccount("admin");
            mLoginBean.setPassword("admin");
            mLoginBean.setIp("114.252.118.161:9696");  // 济南
//            mLoginBean.setIp("114.252.122.197:9696");  //北京
        }
    }

    public void login(View view) {
        if (!checkEmpty()) {
            return;
        }
        XTools.UiUtil().showProgressDialog(this, true);
        String url = mLoginBean.getIp();
        if (url.contains(":")) {
            String[] split = url.split(":");
            if (split.length > 0) {
                IP = split[0];
            }
            if (split.length > 1) {
                PORT = Integer.valueOf(split[1]);
            }
        } else {
            IP = url;
        }

        xLogUtil.e("mLoginBean:" + mLoginBean);
        xLogUtil.e("socket:" + Config.IP + "  " + PORT);

        UserInfo userInfo = Config.DEFAULT_USER_INFO_MAP.get(mLoginBean.getAccount());
        for (String key : Config.DEFAULT_USER_INFO_MAP.keySet()) {
            xLogUtil.e("key:" + key);
            xLogUtil.e(Config.DEFAULT_USER_INFO_MAP.get(key).toString());
        }
        if (userInfo == null) {
            XTools.UiUtil().showToast("用户不存在");
            XTools.UiUtil().closeProgressDialog();
        } else {
            //账号密码验证成功
            if (userInfo.getPassword().equals(mLoginBean.getPassword())) {
                if (Config.APP_TYPE == Config.TYPE_DEMO_SIMPLE) {
                    //小demo
                    loginResult(userInfo);
                } else if (!Config.DEBUG) {
                    connectIp(userInfo);
                } else {
                    loginResult(userInfo);
                }
            } else {
                XTools.UiUtil().showToast("账号或密码错误");
                XTools.UiUtil().closeProgressDialog();
            }
        }
    }

    private void connectIp(final UserInfo userInfo) {
        this.mUserInfo = userInfo;
        if (mCoreService == null) {
            CoreService.bind(this, mConnection);
        } else {
            connectSocket(IP, PORT);
        }
    }

    private void connectSocket(String ip, int port) {
        if (mCoreService != null) {
            mCoreService.addOnSocketConnectListener(mListener);
            mCoreService.socketConnect(ip, port);
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            CoreService.CoreBind coreBind = (CoreService.CoreBind) service;
            mCoreService = coreBind.getCoreService();
            connectSocket(IP, PORT);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private CoreService.OnSocketConnectListener mListener = new CoreService.OnSocketConnectListener() {
        @Override
        public void onSuccess() {
            getUUID();
        }

        @Override
        public void onFailed(String err) {
            XTools.UiUtil().showToast("onFailed socket连接失败:" + err);
            loginResult(null);
        }

        @Override
        public void onDisconnect(SocketClient client, String err) {
            XTools.UiUtil().showToast("onDisconnect socket连接失败");
            loginResult(null);
        }
    };

    private void getUUID() {
        View decorView = LoginActivity.this.getWindow().getDecorView();
        if (mSnackbar == null) {
            mSnackbar = Snackbar.make(decorView, "连接成功，正在获取UUID...", 10000)
                    .addCallback(new Snackbar.Callback() {
                        @Override
                        public void onDismissed(Snackbar transientBottomBar, int event) {
                            XTools.UiUtil().closeProgressDialog();
                            if (event == DISMISS_EVENT_TIMEOUT) {
                                XTools.UiUtil().toastInUI("获取UUID超时");
                            }
                            mSnackbar = null;
                        }
                    })
                    .setActionTextColor(Color.WHITE);
            mSnackbar.getView().setBackgroundColor(XTools.ResUtil().getColor(R.color.colorPrimary));
            mSnackbar.show();
        }
        mCoreService.sendMsg(new SendUUID());
    }

    @Subscribe
    public void onMsg(SocketBean bean) {
        try {
            //获取uuid
            GetUUID json = GsonUtil.getInstance().fromJson(bean.getJson(), GetUUID.class);
            Config.UUID = json.getUuid();
            loginResult(mUserInfo);
        } catch (Exception e) {
            XTools.UiUtil().toastInUI("解析UUID异常");
            if (mSnackbar != null && mSnackbar.isShown()) {
                mSnackbar.dismiss();
            }
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        if (mCoreService != null) {
            CoreService.unBind(this, mConnection);
        }
        super.onDestroy();
    }

    private void loginResult(final UserInfo userInfo) {
        XTools.UiUtil().closeProgressDialog();
        if (userInfo != null) {
            SocketManager.getInstance().unRegisterReceiver();
            SocketManager.getInstance().disconnect();
            MainActivity.getIntent(LoginActivity.this).start();
            XTools.SpUtil().putString(DoMain.SP_ACCOUNT_SAVE, mLoginBean.getAccount());
            XTools.SpUtil().putString(DoMain.SP_IP_SAVE, mLoginBean.getIp());
            XTools.SpUtil().putString(DoMain.SP_PWD_SAVE, mLoginBean.getPassword());
            Config.loginInfo = userInfo;
            XTools.UiUtil().closeProgressDialog();
            finish();
            xLogUtil.e("登陆成功");
        }
    }

    private boolean checkEmpty() {
        return !XTools.UiUtil().textIsEmpty(mLoginBean.getAccount(), "请输入手机号") &&
                !XTools.UiUtil().textIsEmpty(mLoginBean.getPassword(), "请输入密码") &&
                !XTools.UiUtil().textIsEmpty(mLoginBean.getIp(), "请输入网关IP");
    }
}
