package com.furniture.base;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.IBinder;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.furniture.Config;
import com.furniture.R;
import com.furniture.adapter.ControlAdapter;
import com.furniture.bean.ActionBean;
import com.furniture.bean.SelectBean;
import com.furniture.bean.SocketBean;
import com.furniture.bean.action2.AirAction;
import com.furniture.bean.action2.AirConditionerAction;
import com.furniture.bean.json.AllState;
import com.furniture.bean.json.IGson;
import com.furniture.bean.json.control.DeviceCtrl;
import com.furniture.constant.INet;
import com.furniture.databinding.ItemWindowSelectBinding;
import com.furniture.event.SwitchControlEvent;
import com.furniture.impl.IClickTask;
import com.furniture.service.CoreService;
import com.furniture.ui.view.TopBar;
import com.furniture.utils.GsonUtil;
import com.vilyever.socketclient.SocketClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import lbx.xtoollib.XTools;
import lbx.xtoollib.base.BaseDataAdapter;
import lbx.xtoollib.phone.xLogUtil;

import static com.furniture.Config.IP;
import static com.furniture.Config.PORT;

/**
 * .  ┏┓　　　┏┓
 * .┏┛┻━━━┛┻┓
 * .┃　　　　　　　┃
 * .┃　　　━　　　┃
 * .┃　┳┛　┗┳　┃
 * .┃　　　　　　　┃
 * .┃　　　┻　　　┃
 * .┃　　　　　　　┃
 * .┗━┓　　　┏━┛
 * .    ┃　　　┃        神兽保佑
 * .    ┃　　　┃          代码无BUG!
 * .    ┃　　　┗━━━┓
 * .    ┃　　　　　　　┣┓
 * .    ┃　　　　　　　┏┛
 * .    ┗┓┓┏━┳┓┏┛
 * .      ┃┫┫　┃┫┫
 * .      ┗┻┛　┗┻┛
 *
 * @author lbx
 * @date 2018/8/25.
 */

public abstract class BaseControlActivity extends BaseActivity {

    @BindView(R.id.tb_control)
    TopBar mTopBar;
    @BindView(R.id.fl_main_control)
    FrameLayout mMainLayout;
    @BindView(R.id.fl_progress_control)
    FrameLayout mProgressLayout;
    @BindView(R.id.tv_desc)
    TextView mTextView;
    @BindView(R.id.tv_select)
    TextView mSelectTextView;
    @BindView(R.id.ll_select)
    LinearLayout mSelectLinearLayout;
    @BindView(R.id.rv_list_control)
    RecyclerView mRecyclerView;
    private ControlAdapter mAdapter;
    protected static int[] colors = new int[]{R.color.close_control_color, R.color.open_control_color};
    private List<SelectBean> mList;
    private PopupWindow mWindow;
    protected String room = "";
    protected String name = "";
    protected String deviceName = "";
    protected boolean isOpen;
    private CoreService mCoreService;
    private ServiceConnection mConnection;
    private ActionBean mControlBean;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public int getLayoutID() {
        EventBus.getDefault().register(this);
        return R.layout.activity_control;
    }

    @CallSuper
    @Override
    public void initIntent(Intent intent) {
        if (intent.hasExtra("name")) {
            name = intent.getStringExtra("name");
        }
        if (intent.hasExtra("room")) {
            room = intent.getStringExtra("room");
        }
        if (intent.hasExtra("deviceName")) {
            deviceName = intent.getStringExtra("deviceName");
        }
        isOpen = intent.getBooleanExtra("isOpen", false);
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {

    }

    @CallSuper
    @Override
    public void initView(View view) {
        XTools.UiUtil().showProgressDialog(this);
        Observable.timer(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> XTools.UiUtil().closeProgressDialog());
        mTopBar.bindActivity(this);
        mTopBar.setTitle(getTopBarTitle());

        LinearLayoutManager m = new LinearLayoutManager(this);
        m.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(m);

        View mainControlView = getMainControlView();
        if (mainControlView != null) {
            mMainLayout.addView(mainControlView);
        }

        View progressControlView = getProgressControlView();
        if (progressControlView != null) {
            mProgressLayout.addView(progressControlView);
        }

        List<ActionBean> list = getListControlList();
        if (list != null) {
            mAdapter = new ControlAdapter(this, list);
            mRecyclerView.setAdapter(mAdapter);
        }
        View.OnClickListener onClickListener = selectClick(mSelectLinearLayout);
        if (onClickListener != null) {
            mSelectLinearLayout.setOnClickListener(onClickListener);
            mSelectLinearLayout.setVisibility(View.VISIBLE);
            mSelectTextView.setText(getSelect());
        }
        mControlBean = getControlBean();
        if (mControlBean != null) {
            mControlBean.setOpen(isOpen);
        }
        if (isOpen) {
            setOpen();
        }
        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mCoreService = ((CoreService.CoreBind) service).getCoreService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        CoreService.bind(this, mConnection);
        mList = selectClickList();
    }

    @CallSuper
    @Override
    public void initData() {
        initData(getAttribute());
    }

    @CallSuper
    @Override
    public void initListener() {
        if (mAdapter != null) {
            mAdapter.setOnItemClickListener(new BaseDataAdapter.OnItemClickListener<ActionBean>() {
                @Override
                public void onItemClick(RecyclerView recyclerView, int id, int position, ActionBean entity) {
                    IClickTask task = entity.getTask();
                    if (task != null) {
                        String clickId = makeMsgId();
                        xLogUtil.e(this, "setClickId：" + clickId);
                        entity.setClickId(clickId);
                        task.actionClick(false);
                    }
                }

                @Override
                public void onItemLongClick(RecyclerView recyclerView, int id, int position, ActionBean entity) {

                }
            });
        }
    }

    public void setSelectEnable(boolean enable) {
        mSelectLinearLayout.setVisibility(enable ? View.VISIBLE : View.GONE);
    }

    public void setDescText(String s) {
        mTextView.setText(s);
    }

    public void setDescTextColor(@ColorRes int id) {
        mTextView.setTextColor(XTools.ResUtil().getColor(id));
    }

    public void setDescTextColorInt(int color) {
        mTextView.setTextColor(color);
    }

    public void setDescTextColor(String c) {
        mTextView.setTextColor(Color.parseColor(c));
    }

    protected int progress2Real(int max, int min, int progress) {
        return (int) Math.ceil((min + (max - min) * 1.0F * progress / 100));
    }

    protected int real2Progress(int max, int min, int progress) {
        return (int) Math.ceil(((progress - min) * 1.0F / (max - min) * 100));
    }

    protected PopupWindow makeWindow(BaseDataAdapter.OnItemClickListener<SelectBean> listener) {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        View view = XTools.UiUtil().inflate(R.layout.window_select);
        mWindow = new PopupWindow(view, XTools.WindowUtil().dip2px(50), ViewGroup.LayoutParams.WRAP_CONTENT);
        mWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mWindow.setOutsideTouchable(true);
        mWindow.setFocusable(true);

        RecyclerView recyclerView = view.findViewById(R.id.rv_window);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        WindowSelectAdapter adapter = new WindowSelectAdapter(this, mList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(listener);
        return mWindow;
    }

    protected void select(SelectBean bean) {
        if (bean != null) {
            if (mList != null) {
                for (int i = 0; i < mList.size(); i++) {
                    SelectBean selectBean = mList.get(i);
                    boolean b = selectBean.getType().equals(bean.getType());
                    mList.get(i).setSelect(b);
                    if (b) {
                        bean = mList.get(i);
                    }
                }
            }
            mSelectLinearLayout.setVisibility(View.VISIBLE);
            mSelectTextView.setText(bean.getName());
        } else {
            mSelectLinearLayout.setVisibility(View.GONE);
        }
        if (mWindow != null && mWindow.isShowing()) {
            mWindow.dismiss();
        }
    }

    protected boolean isControlOpen() {
        if (!isOpen()) {
            XTools.UiUtil().toastInUI("设备未开启");
        }
        return isOpen;
    }

    public void switchControl(boolean isOpen) {
        if (isOpen) {
            setOpen();
        } else {
            setClose();
        }
    }

    public abstract void initData(AllState.Params.Item item);

    public String getTopBarTitle() {
        return name;
    }

    public abstract View getMainControlView();

    public abstract View getProgressControlView();

    public abstract List<ActionBean> getListControlList();

    public abstract ActionBean getControlBean();

    public abstract List<SelectBean> selectClickList();

    public abstract View.OnClickListener selectClick(View view);

    public abstract void setOpen();

    public abstract void setClose();

    public abstract void onSocketMsgReceive(SocketBean bean, String devid);

    private class WindowSelectAdapter extends BaseDataAdapter<SelectBean, ItemWindowSelectBinding, BaseDataAdapter.BaseHolder> {

        private List<SelectBean> list;

        private WindowSelectAdapter(Context context, List<SelectBean> list) {
            super(context, list);
            this.list = list;
        }

        @Override
        public BaseHolder getHolder(View view, ViewDataBinding binding) {
            return new BaseHolder(view, binding);
        }

        @Override
        public int itemLayout() {
            return R.layout.item_window_select;
        }

        @Override
        public void dataBinding(ItemWindowSelectBinding binding, int position, SelectBean entity, BaseHolder baseHolder) {
            binding.setBean(entity);
            binding.setIsShowLine(isEndItem(position));
            binding.getRoot().getLayoutParams().height = XTools.WindowUtil().dip2px(30);
        }

        private boolean isEndItem(int position) {
            return list.size() == position + 1;
        }

        @Override
        public boolean itemClickEnable() {
            return true;
        }

        @Override
        public boolean itemLongClickEnable() {
            return false;
        }
    }

    protected void saveCircleProgress(int progress) {
        XTools.SpUtil().putInt(room + name + "CIRCLE_PROGRESS", progress);
    }

    protected int getCircleProgress() {
        return XTools.SpUtil().getInt(room + name + "CIRCLE_PROGRESS", -1);
    }

    protected void saveProgress(int progress) {
        XTools.SpUtil().putInt(room + name + "PROGRESS", progress);
    }

    protected int getProgress() {
        return XTools.SpUtil().getInt(room + name + "PROGRESS", -1);
    }

    protected void saveCenterProgress(int x, int y) {
        XTools.SpUtil().putIntArray(room + name + "CENTER_PROGRESS", x, y);
    }

    protected int[] getCenterProgress() {
        return XTools.SpUtil().getIntArray(room + name + "CENTER_PROGRESS");
    }

    private void saveSelect(String name) {
        XTools.SpUtil().putString(room + name + "SELECT", name);
    }

    private String getSelect() {
        return XTools.SpUtil().getString(room + name + "SELECT", "自动");
    }


    @Override
    protected void onDestroy() {
        if (mConnection != null) {
            CoreService.unBind(this, mConnection);
        }
        mCoreService = null;
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void onSocketMsg(SocketBean bean) {
        socketMsgReceive(bean);
    }

    /**
     * {@link BaseRoomFragment#onSwitchControl(SwitchControlEvent)}
     * 各个开关的控制
     *
     * @param bean bean
     */
    public void socketMsgReceive(SocketBean bean) {
        XTools.UiUtil().closeProgressDialog();
        String json = bean.getJson();
        GsonUtil.Result result = GsonUtil.getInstance().getResult(json);
        if (id.equals(result.getId())) {
            if (result.getResult() == INet.SUCCESS) {
                String devid = result.getDevid();
                if (devid.equals(room + AirAction.NAME)) {
                    xLogUtil.e(this, "BaseControlActivity 新风开关");
                    if (mControlBean != null) {
                        DeviceCtrl control = GsonUtil.getInstance().fromJson(bean.getJson(), DeviceCtrl.class);
                        boolean open = control.getParams().getField().isCtrl();
                        mControlBean.setOpen(open);
                        EventBus.getDefault().post(new SwitchControlEvent(room, name, open));
                        if (open) {
                            setOpen();
                        } else {
                            setClose();
                        }
                    }
                } else if (devid.equals(room + AirConditionerAction.NAME)) {
                    xLogUtil.e(this, "BaseControlActivity 空调开关");
                    if (mControlBean != null) {
                        DeviceCtrl control = GsonUtil.getInstance().fromJson(bean.getJson(), DeviceCtrl.class);
                        boolean open = control.getParams().getField().isCtrl();
                        mControlBean.setOpen(open);
                        EventBus.getDefault().post(new SwitchControlEvent(room, name, open));
                        if (open) {
                            setOpen();
                        } else {
                            setClose();
                        }
                    }
                } else {
                    onSocketMsgReceive(bean, devid);
                }
            }
        }
    }

    public void send(IGson gson) {
        if (mCoreService != null) {
            XTools.UiUtil().showProgressDialog(this, !Config.DEBUG);
            if (mCoreService.isConnect()) {
                mCoreService.sendMsg(gson);
            } else {
                mCoreService.socketConnect(IP, PORT);
                CoreService.OnSocketConnectListener l = new CoreService.OnSocketConnectListener() {
                    @Override
                    public void onSuccess() {
                        if (mCoreService != null) {
                            mCoreService.sendMsg(gson);
                            mCoreService.removeOnSocketConnectListener(this);
                        }
                    }

                    @Override
                    public void onFailed(String err) {
                        XTools.UiUtil().toastInUI("连接服务器异常:" + err);
                        xLogUtil.e("连接服务器异常:" + err);
                        XTools.UiUtil().closeProgressDialog();
                        if (mCoreService != null) {
                            mCoreService.removeOnSocketConnectListener(this);
                        }
                    }

                    @Override
                    public void onDisconnect(SocketClient client, String err) {
                        xLogUtil.e("断开连接");
                        if (mCoreService != null) {
                            mCoreService.removeOnSocketConnectListener(this);
                        }
                    }
                };
                mCoreService.addOnSocketConnectListener(l);
            }
        }
    }

    protected AllState.Params.Item getAttribute() {
        return Config.actionMap.get(room + deviceName);
    }
}
