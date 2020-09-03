package com.furniture.ui.activity;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.databinding.ViewDataBinding;
import android.os.IBinder;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.furniture.Config;
import com.furniture.service.NoticeService;
import com.furniture.R;
import com.furniture.adapter.MainFragmentAdapter;
import com.furniture.base.BaseActivity;
import com.furniture.base.BaseFragment;
import com.furniture.bean.SocketBean;
import com.furniture.bean.json.AllState;
import com.furniture.bean.json.GetAllState;
import com.furniture.bean.json.IGson;
import com.furniture.constant.Device;
import com.furniture.constant.INet;
import com.furniture.databinding.ActivityMainBinding;
import com.furniture.event.ActionAllType;
import com.furniture.factory.FragmentFactory;
import com.furniture.factory.FragmentMainFactory;
import com.furniture.injector.components.AppComponent;
import com.furniture.injector.components.DaggerActivityComponent;
import com.furniture.injector.modules.ActivityModule;
import com.furniture.service.CoreService;
import com.furniture.type.MainPager;
import com.furniture.ui.view.bottomBar.BottomBar;
import com.furniture.utils.GsonUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import lbx.xtoollib.XIntent;
import lbx.xtoollib.XTools;
import lbx.xtoollib.phone.xLogUtil;

/**
 * @author lbx
 */
public class MainActivity extends BaseActivity {

    private BottomBar mBottomBar;
    private ActivityMainBinding mBinding;
    private MainFragmentAdapter mAdapter;
    private List<BaseFragment> mList;
    private ServiceConnection mConnection;
    private ServiceConnection mConnectionWarning;
    private CoreService mCoreService;
    private NoticeService mNoticeService;
    private Disposable mDisposable;
    private boolean getAll;
    private ScheduledExecutorService mPool;
    private AlertDialog noticeDialog;

    public static XIntent getIntent(Context context) {
        return new XIntent(context, MainActivity.class);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
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
        mBinding = (ActivityMainBinding) binding;
    }

    @Override
    public void initView(View view) {
        if (mBottomBar == null) {
            mBottomBar = new BottomBar(this);
        }
    }

    @Override
    public void initData() {
        addData();
    }

    private void addData() {
        mList = new ArrayList<>();
        //添加Fragment
        FragmentFactory factory = new FragmentMainFactory();
        MainPager[] values = MainPager.values();
        for (MainPager fragment : values) {
            BaseFragment f = fragment.createFragment(factory);
            if (f != null) {
                //将切换tab的图片与fragment绑定
                f.setFragmentInfo(fragment.getInfo());
                mList.add(f);
            }
        }
        if (mAdapter == null) {
            //pager
            mAdapter = new MainFragmentAdapter(getSupportFragmentManager(), mList);
            mBinding.vpMain.setAdapter(mAdapter);
            mBinding.vpMain.setOffscreenPageLimit(Integer.MAX_VALUE);
            //mBottomBar
            mBottomBar.bind(mBinding.vpMain, (TextView) null, true, 0);
            mBottomBar.setTextStateColor(R.color.bottombar_item_textcolor_normal, R.color.bottombar_item_textcolor_select);
        }
        if (mBinding.flBottomBar.getChildCount() == 0) {
            //添加BottomBar
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    XTools.ResUtil().getDimen(R.dimen.home_bottom_bar_width), ViewGroup.LayoutParams.MATCH_PARENT);
            params.gravity = Gravity.CENTER;
            mBinding.flBottomBar.addView(mBottomBar, params);
        }
        if (mConnection == null) {
            mConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    mCoreService = ((CoreService.CoreBind) service).getCoreService();
                    refreshAll();
                    getAll = true;
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {

                }
            };
            CoreService.bind(this, mConnection);
        }
        if (mConnectionWarning == null) {
            mConnectionWarning = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    mNoticeService = ((NoticeService.CoreBind) service).getNoticeService();
                    mNoticeService.mainActivity = MainActivity.this;
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {

                }
            };
            NoticeService.Companion.bind(this, mConnectionWarning);
        }
    }

    public void refreshAll() {
        if (mCoreService != null) {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
            }
            if (Config.APP_TYPE != Config.TYPE_DEMO_SIMPLE) {
                //查询超时
                mDisposable = Observable.timer(5000, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(aLong -> XTools.UiUtil().getSystemDialog(
                                MainActivity.this,
                                "请重新登陆",
                                "读取状态信息失败",
                                (dialog, which) -> {
                                    if (!Config.DEBUG) {
                                        finish();
                                    }
                                }, null)
                                .show());
            }
            send(new GetAllState());
            addDisposable(mDisposable);
        }
    }

    @Override
    protected void onResume() {
        if (getAll) {
            send(new GetAllState());
        }
        super.onResume();
    }

    /**
     * 刷新所有图标
     * {@link com.furniture.base.BaseRoomFragment#onRefresh(ActionAllType)}
     *
     * @param bean bean
     */
    @Subscribe
    public void onSocketMsg(SocketBean bean) {
        try {
            String json = bean.getJson();
            GsonUtil.Result result = GsonUtil.getInstance().getResult(json);
            if (INet.ALL_TYPE.equals(result.getType())) {
                xLogUtil.e(this, "初始化所有图标");
                if (mDisposable != null && !mDisposable.isDisposed()) {
                    mDisposable.dispose();
                    mDisposable = null;
                }
                AllState allState = GsonUtil.getInstance().fromJson(json, AllState.class);
                Config.AllState = allState;
                Config.actionMap.put(allState.params.devices);
                EventBus.getDefault().post(new ActionAllType(allState, Config.actionMap));
            } else {
                String devid = result.getDevid();
                if (devid.contains(Device.HOME)
                        || devid.contains(Device.SCE)
                        || devid.contains(Device.SEN)) {
                    xLogUtil.e(this, "离家/回家,重新查询");
                    send(new GetAllState());
                }
            }
        } catch (Exception ignored) {
        } finally {
            XTools.UiUtil().closeProgressDialog();
        }
    }

    @Override
    protected void onDestroy() {
        if (mConnection != null) {
            CoreService.unBind(this, mConnection);
        }
        mCoreService = null;
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (mPool != null) {
            mPool.shutdownNow();
            mPool = null;
        }
        NoticeService.Companion.unbind(this, mConnectionWarning);
        super.onDestroy();
    }


    public void showWarningDialog() {
        if (noticeDialog == null || !noticeDialog.isShowing()) {
            noticeDialog = new AlertDialog.Builder(this)
                    .setTitle("警告")
                    .setMessage("智能报警系统启动")
                    .setCancelable(false)
                    .create();
            noticeDialog.show();
        }
    }

    public void dismissWarningDialog() {
        if (noticeDialog != null && noticeDialog.isShowing()) {
            noticeDialog.dismiss();
        }
        noticeDialog = null;
    }

    public void send(IGson gson, boolean eventBus) {
        send(gson, eventBus, true);
    }

    public void send(IGson gson, boolean eventBus, boolean showDialog) {
        if (eventBus) {
            xLogUtil.e(this, "重新注册EventBus");
            try {
                EventBus.getDefault().register(this);
            } catch (Exception ignored) {
            }
        }
        if (mCoreService != null) {
            if (showDialog) {
                XTools.UiUtil().showProgressDialog(this, !Config.DEBUG);
            }
            mCoreService.sendMsg(gson);
        }
    }

    public void send(IGson gson) {
        send(gson, false);
    }
}
