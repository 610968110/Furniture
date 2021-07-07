package com.furniture.base;

import android.os.Looper;
import android.support.annotation.DrawableRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.furniture.Config;
import com.furniture.R;
import com.furniture.adapter.RoomAdapter;
import com.furniture.bean.ActionBean;
import com.furniture.bean.EditActionNameBean;
import com.furniture.bean.SocketBean;
import com.furniture.bean.action2.AirAction;
import com.furniture.bean.action2.AirConditionerAction;
import com.furniture.bean.action2.HeatingAction;
import com.furniture.bean.json.AllState;
import com.furniture.bean.json.Der;
import com.furniture.bean.json.GetAllState;
import com.furniture.bean.json.IGson;
import com.furniture.bean.json.control.DeviceCtrl;
import com.furniture.constant.Device;
import com.furniture.constant.INet;
import com.furniture.event.ActionAllType;
import com.furniture.event.NotifyRoomItem;
import com.furniture.event.ResetOpenAction;
import com.furniture.event.SwitchControlEvent;
import com.furniture.function.ActionMap;
import com.furniture.impl.IClickTask;
import com.furniture.ui.activity.MainActivity;
import com.furniture.ui.view.BigTemperatureTextView;
import com.furniture.ui.view.DescView;
import com.furniture.ui.view.EnvironmentView;
import com.furniture.utils.GsonUtil;
import com.furniture.utils.ListUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import lbx.xtoollib.XTools;
import lbx.xtoollib.base.BaseDataAdapter;
import lbx.xtoollib.listener.BaseAppBarStateChangeListener;
import lbx.xtoollib.phone.xLogUtil;

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
 * @date 2018/8/24.
 */

public abstract class BaseRoomFragment extends BaseFragment {

    @BindView(R.id.tv_big_temp)
    BigTemperatureTextView mBigTempTextView;
    @BindView(R.id.ev_room)
    EnvironmentView mEnvironmentView;
    @BindView(R.id.im_room_bg)
    ImageView mBgView;
    @BindView(R.id.rv_top)
    RecyclerView mTopRecyclerView;
    @BindView(R.id.rv_center)
    RecyclerView mCenterRecyclerView;
    @BindView(R.id.rv_all)
    RecyclerView mAllRecyclerView;
    @BindView(R.id.collLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.appbar)
    AppBarLayout mAppBar;
    @BindView(R.id.dv_1)
    DescView mDescView1;
    @BindView(R.id.dv_2)
    DescView mDescView2;
    private List<ActionBean> mTopList;
    private List<ActionBean> mCenterList;
    private List<ActionBean> mAllList;
    private MainActivity mMainActivity;
    private RoomAdapter mTopAdapter;
    private RoomAdapter mCenterAdapter;
    private RoomAdapter mAllAdapter;

    @Override
    public int getLayoutID() {
        EventBus.getDefault().register(this);
        return R.layout.fragment_room;
    }

    @Override
    public void onDestroy() {
        setTime(0);
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void initView(View view) {
        FragmentActivity activity = getActivity();
        if (activity instanceof MainActivity) {
            mMainActivity = (MainActivity) activity;
        }
        Looper.myQueue().addIdleHandler(() -> {
            ViewGroup.LayoutParams layoutParams = mAppBar.getLayoutParams();
            if (Config.SCREEN_ORIENTATION == Config.SCREEN_PORTRAIT) {
                layoutParams.height = getView().getHeight() / 2;
            } else {
                layoutParams.height = getView().getHeight() -
                        XTools.ResUtil().getDimen(
                                R.dimen.top_action_margin,
                                R.dimen.top_action_margin,
                                R.dimen.top_action_height);
            }
            mAppBar.setLayoutParams(layoutParams);
            return false;
        });
        mBgView.setBackgroundResource(bgRes());
        mTopRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), lineCount()));
        mCenterRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), lineCount()));
        mAllRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), lineCount()));
        mEnvironmentView.setVisibility(showEnvironmentView());
        initView();
    }

    protected int showEnvironmentView(){
        return View.VISIBLE;
    }

    protected int lineCount() {
        return 5;
    }

    @Override
    public void initData() {
        mTopList = new ArrayList<>();
        mCenterList = new ArrayList<>();
        mAllList = new ArrayList<>();
        ActionBean[] list = initTopAction();
        if (list != null && list.length != 0) {
            Collections.addAll(mTopList, list);
        } else {
            mTopRecyclerView.setVisibility(View.GONE);
        }
        ActionBean[] list1 = initCenterAction();
        if (list1 != null && list1.length != 0) {
            Collections.addAll(mCenterList, list1);
        } else {
            mCenterRecyclerView.setVisibility(View.GONE);
        }
        ActionBean[] list2 = initAllAction();
        if (list2 != null && list2.length != 0) {
            Collections.addAll(mAllList, list2);
        } else {
            mAllRecyclerView.setVisibility(View.GONE);
        }
        int dimen = XTools.ResUtil().getDimen(R.dimen.top_action_height);
        mTopAdapter = new RoomAdapter(getContext(), mTopList, dimen);
        mTopRecyclerView.setAdapter(mTopAdapter);
        mCenterAdapter = new RoomAdapter(getContext(), mCenterList, dimen);
        mCenterRecyclerView.setAdapter(mCenterAdapter);
        mAllAdapter = new RoomAdapter(getContext(), mAllList, dimen + 20);
        mAllRecyclerView.setAdapter(mAllAdapter);
        setDefaultDer();
        //存起来  留着配置页面使用
        Config.roomActionMap.putTopList(room(), mTopList);
        Config.roomActionMap.putCenterList(room(), mCenterList);
        Config.roomActionMap.putAllList(room(), mAllList);
        mBigTempTextView.setVisibility(setShowBigTemp() ? View.VISIBLE : View.GONE);
        mDescView1.setVisibility(setShowDesc1() ? View.VISIBLE : View.GONE);
        mDescView2.setVisibility(setShowDesc2() ? View.VISIBLE : View.GONE);
        if (Config.APP_TYPE == Config.TYPE_DEMO_JINAN ||
                Config.APP_TYPE == Config.TYPE_DEMO_SHANGHAI) {
//            mEnvironmentView.setHchoViewShow();
        }
    }

    public boolean setShowBigTemp() {
        return false;
    }

    public boolean setShowDesc1() {
        return false;
    }

    public boolean setShowDesc2() {
        return false;
    }

    @Override
    public void initListener() {
        mAppBar.addOnOffsetChangedListener(new BaseAppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state, int i) {

            }

            @Override
            public void onScrollChanged(AppBarLayout appBarLayout, int i) {
                float percent = i * 1.0F / (mCollapsingToolbarLayout.getMeasuredHeight()
                        - XTools.ResUtil().getDimen(R.dimen.top_action_close_height));
                // 右侧为5个的时候用这个
//                int max = XTools.ResUtil().getDimen(R.dimen.environment_top_margin) + 10;
                int max = XTools.ResUtil().getDimen(R.dimen.environment_top_margin);
                float offset = max * percent;
                mEnvironmentView.setTranslationY(offset);
            }
        });
        mTopAdapter.setOnItemClickListener(onItemClickListener);
        mCenterAdapter.setOnItemClickListener(onItemClickListener);
        mAllAdapter.setOnItemClickListener(onItemClickListener);
    }

    private BaseDataAdapter.OnItemClickListener<ActionBean> onItemClickListener = new BaseDataAdapter.OnItemClickListener<ActionBean>() {
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
            IClickTask task = entity.getTask();
            if (task != null) {
                String clickId = makeMsgId();
                xLogUtil.e(this, "setClickId：" + clickId);
                entity.setClickId(clickId);
                task.actionClick(true);
            }
        }
    };

    @Subscribe
    public void onActionNameChange(EditActionNameBean bean) {
        if (bean.getRoom().equals(room())) {
            for (ActionBean b : mCenterList) {
                ActionBean bean1 = bean.getBean();
                if (b.getName().equals(bean1.getName())) {
                    b.setOtherName(bean1.getOtherName());
                }
            }
            for (ActionBean b : mAllList) {
                ActionBean bean1 = bean.getBean();
                if (b.getName().equals(bean1.getName())) {
                    b.setOtherName(bean1.getOtherName());
                }
            }
            mCenterAdapter.notifyDataSetChanged();
            mAllAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 控制页面开关后 控制前一个页面的小图标
     * SwitchAction
     * boolean open = isOpen();
     * xLogUtil.e(this, "开启:" + !open);
     * EventBus.getDefault().post(new SwitchControlEvent(room, controlName, !open));
     * ((BaseControlActivity) context).switchControl(!open);
     * {@link BaseControlActivity#socketMsgReceive(SocketBean)}
     *
     * @param event event
     */
    @Subscribe
    public void onSwitchControl(SwitchControlEvent event) {
        if (event != null && event.getRoom().equals(room())) {
            if (mAllList != null) {
                int pos = getBeanPosFromList(mAllList, event.getName());
                if (-1 != pos && mAllAdapter != null) {
                    boolean open = event.isOPen();
                    mAllList.get(pos).setOpen(open);
                    XTools.SpUtil().putBoolean(event.getName() + event.getRoom(), open);
                    mAllAdapter.notifyItemChanged(pos);
                }
            }
        }
    }

    /**
     * 收到消息
     *
     * @param bean bean
     */
    @Subscribe
    public void onSocketMsg(SocketBean bean) {
        socketMsgReceive(bean);
    }

    /**
     * 刷新adapter
     *
     * @param notifyRoomItem notifyRoomItem
     */
    @Subscribe
    public void notifyDataSetChanged(NotifyRoomItem notifyRoomItem) {
        int region = notifyRoomItem.getRegion();
        int pos = notifyRoomItem.getPos();
        String room = notifyRoomItem.getRoom();
        String name = notifyRoomItem.getName();
        long delayed = notifyRoomItem.getDelayed();
        BaseDataAdapter adapter = null;
        if (region == 0) {
            adapter = mTopAdapter;
        } else if (region == 1) {
            adapter = mCenterAdapter;
        } else if (region == 2) {
            adapter = mAllAdapter;
        }
        if (adapter != null) {
            if (TextUtils.isEmpty(room) || TextUtils.isEmpty(name)) {
                if (pos == -1) {
                    adapter.notifyDataSetChanged();
                } else {
                    adapter.notifyItemChanged(pos);
                }
            } else {
                if (room.equals(room())) {
                    int p = ListUtil.getPosFromName(mAllList, name);
                    if (p != -1) {
                        mAllList.get(p).setOpen(notifyRoomItem.isOpen());
                    }
                }
            }
        }
    }

    @Subscribe
    public void resetOpenAction(ResetOpenAction action) {
        int item = action.getItem();
        BaseDataAdapter adapter = null;
        List<ActionBean> list = null;
        if (item == 0) {
            adapter = mTopAdapter;
            list = mTopList;
        } else if (item == 1) {
            adapter = mCenterAdapter;
            list = mCenterList;
        } else if (item == 2) {
            adapter = mAllAdapter;
            list = mAllList;
        }
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setOpen(false);
            }
        }
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * {@link MainActivity#onSocketMsg(SocketBean)}
     */
    @Subscribe
    public void onRefresh(ActionAllType t) {
        ActionMap map = t.actionMap;
        if (mTopList != null) {
            xLogUtil.e("onRefresh mTopList");
            for (ActionBean bean : mTopList) {
                AllState.Params.Item item = map.get(bean.getRoom() + bean.getDeviceName());
                if (item != null) {
                    bean.onRefresh(item.field);
                }
            }
            mTopAdapter.notifyDataSetChanged();
        }
        if (mCenterList != null) {
            for (ActionBean bean : mCenterList) {
                AllState.Params.Item item = map.get(bean.getRoom() + bean.getDeviceName());
                if (item != null) {
                    bean.onRefresh(item.field);
                }
            }
            mCenterAdapter.notifyDataSetChanged();
        }
        if (mAllList != null) {
            for (ActionBean bean : mAllList) {
                AllState.Params.Item item = map.get(bean.getRoom() + bean.getDeviceName());
                if (item != null) {
                    bean.onRefresh(item.field);
                }
            }
            mAllAdapter.notifyDataSetChanged();
        }
    }

    private int getBeanPosFromList(List<ActionBean> list, String device) {
        int pos = -1;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getName().equals(device)) {
                    pos = i;
                    break;
                }
            }
        }
        return pos;
    }

    protected void sendMsg(IGson gson) {
        if (mMainActivity != null) {
            mMainActivity.send(gson);
        } else {
            XTools.UiUtil().toastInUI("查询数据失败");
            xLogUtil.e("查询数据失败");
        }
    }

    protected void setBaseBigTemp(String temp) {
        mBigTempTextView.setTemp(changeText(temp));
    }

    protected void setDescSymbol1(String s) {
        mDescView1.setSymbolText(s);
    }

    protected void setDescContent1(String s) {
        mDescView1.setContentText(s);
    }

    protected void setDescImg1(@DrawableRes int img) {
        mDescView1.setImg(img);
    }

    protected void setDescSymbol2(String s) {
        mDescView2.setSymbolText(s);
    }

    protected void setDescContent2(String s) {
        mDescView2.setContentText(s);
    }

    protected void setName1(String s) {
        mDescView1.setName(s);
    }

    protected void setName2(String s) {
        mDescView2.setName(s);
    }

    protected void setDescImg2(@DrawableRes int img) {
        mDescView2.setImg(img);
    }

    protected void setBaseTemp(String temp) {
        mEnvironmentView.setTemp(changeText(temp));
    }

    protected void setBaseH(String h) {
        mEnvironmentView.setH(changeText(h));
    }

    protected void setBasePM(String pm) {
        mEnvironmentView.setPM(changeText(pm));
    }

    protected void setBaseCO2(String co2) {
        mEnvironmentView.setCO2(changeText(co2));
    }

    protected void setBaseHCHO(String co2) {
        mEnvironmentView.setHcho(changeText(co2));
    }

    private String changeText(String s) {
        if (TextUtils.isEmpty(s) || "0".equals(s)) {
            s = "-";
        }
        return s;
    }

    public abstract void initView();

    public abstract
    @DrawableRes
    int bgRes();

    public abstract String room();

    public abstract ActionBean[] initTopAction();

    public abstract ActionBean[] initCenterAction();

    public abstract ActionBean[] initAllAction();

    public abstract void onSocketMsgReceive(SocketBean bean, String devid);

    public void socketMsgReceive(SocketBean bean) {
        XTools.UiUtil().closeProgressDialog();
        String json = bean.getJson();
        GsonUtil.Result result = GsonUtil.getInstance().getResult(json);
        String devid = result.getDevid();
        xLogUtil.e("id:" + id);
        xLogUtil.e("result.getId():" + result.getId());
        if (id.equals(result.getId())) {
            xLogUtil.e("result.getResult():" + result.getResult());
            if (result.getResult() == INet.SUCCESS) {
                if (devid.equals(room() + AirAction.NAME)) {
                    //新风开关
                    xLogUtil.e(this, "新风开关");
                    DeviceCtrl control = GsonUtil.getInstance().fromJson(bean.getJson(), DeviceCtrl.class);
                    boolean open = control.getParams().getField().isCtrl();
                    int pos = ListUtil.getPosFromClass(mAllList, AirAction.class);
                    if (pos != -1) {
                        mAllList.get(pos).setOpen(open);
                        mAllAdapter.notifyItemChanged(pos);
                    }
                } else if (devid.equals(room() + AirConditionerAction.NAME)) {
                    //新风开关
                    xLogUtil.e(this, "空调开关");
                    DeviceCtrl control = GsonUtil.getInstance().fromJson(bean.getJson(), DeviceCtrl.class);
                    boolean open = control.getParams().getField().isCtrl();
                    int pos = ListUtil.getPosFromClass(mAllList, AirConditionerAction.class);
                    if (pos != -1) {
                        mAllList.get(pos).setOpen(open);
                        mAllAdapter.notifyItemChanged(pos);
                    }
                } else if (devid.equals(room() + HeatingAction.NAME)) {
                    //地暖开关
                    xLogUtil.e(this, "地暖开关");
                    DeviceCtrl control = GsonUtil.getInstance().fromJson(bean.getJson(), DeviceCtrl.class);
                    boolean open = control.getParams().getField().isCtrl();
                    int pos = ListUtil.getPosFromClass(mAllList, HeatingAction.class);
                    if (pos != -1) {
                        mAllList.get(pos).setOpen(open);
                        mAllAdapter.notifyItemChanged(pos);
                    }
                } else {
                    onSocketMsgReceive(bean, devid);
                }
            } else if (result.getResult() == INet.FAILED) {
                if (null == devid) {
                    try {
                        JSONObject object = new JSONObject(json);
                        String service = object.optString("service", "");
                        if ("deviceJoin".equals(service)) {
                            JSONObject params = object.getJSONObject("params");
                            JSONArray devices = params.getJSONArray("devices");
                            for (int i = 0; i < devices.length(); i++) {
                                JSONObject obj = devices.getJSONObject(i);
                                String devid1 = obj.optString("devid", "");
                                if ((room() + Device.DER).equals(devid1)) {
                                    String derJson = obj.toString();
                                    //查询环境
                                    Der der = GsonUtil.getInstance().fromJson(derJson, Der.class);
                                    setDer(der);
                                    xLogUtil.e(this, "查询环境");
                                } else if ((room() + AirConditionerAction.NAME).equals(devid1)) {
                                    xLogUtil.e(this, "设定bigTemp");
                                    JSONObject field = obj.getJSONObject("field");
                                    String setT = field.optString("SetT", "0");
                                    setBigTemp(setT);
                                }
                            }
                        }
                    } catch (Exception ignored) {
                        ignored.printStackTrace();
                    }
                }
            }
        }
        //这里可能需要接收全部设备时候对应的报文查询
//        else if (INet.ALL_TYPE.equals(result.getType())) {
//            xLogUtil.e(devid + "  " + room() + "   " + Device.DER);
//            if (devid.equals(room() + Device.DER)) {
//                Der der = GsonUtil.getInstance().fromJson(bean.getJson(), Der.class);
//                //查询环境
//                setDer(der);
//                xLogUtil.e(this, "查询环境");
//            }
//        }
//        我根据ML  显示出已经打开的模式  点击控制开关后  然后查询所有数据  再刷新
    }

    private void setBigTemp(String temp) {
        setBaseBigTemp(temp);
        XTools.SpUtil().putFloat(room() + "bigTemp", Float.valueOf(temp));
    }

    private void setDefaultBigTemp() {
        setBaseBigTemp(String.valueOf(XTools.SpUtil().getFloat(room() + "bigTemp", 0F)));
    }

    protected void setDer(Der der) {
        xLogUtil.e("der:" + der.getField().toString());
        Der.Field field = der.getField();
        int temp = Float.valueOf(field.getTemp()).intValue();
        float hcho = Float.valueOf(field.getHcho());
        int h = Float.valueOf(field.getHumi()).intValue();
        int pm25 = Float.valueOf(field.getPM25()).intValue();
        int co2 = Float.valueOf(field.getCO2()).intValue();
        setBaseTemp(temp / 10 + "");
        setBaseH(h / 10 + "");
        setBasePM(pm25 / 10 + "");
        setBaseCO2(co2 / 10 + "");
        setBaseHCHO(hcho / 10 + "");
        XTools.SpUtil().putFloat(room() + "hcho", hcho);
        XTools.SpUtil().putInt(room() + "temp", temp);
        XTools.SpUtil().putInt(room() + "h", h);
        XTools.SpUtil().putInt(room() + "pm", pm25);
        XTools.SpUtil().putInt(room() + "co2", co2);
    }

    protected void setDefaultDer() {
        setDefaultBigTemp();
        setBaseHCHO(String.valueOf(XTools.SpUtil().getFloat(room() + "hcho", 0) / 10));
        setBaseTemp(String.valueOf(XTools.SpUtil().getInt(room() + "temp", 0) / 10));
        setBaseH(String.valueOf(XTools.SpUtil().getInt(room() + "h", 0) / 10));
        setBasePM(String.valueOf(XTools.SpUtil().getInt(room() + "pm", 0) / 10));
        setBaseCO2(String.valueOf(XTools.SpUtil().getInt(room() + "co2", 0) / 10));
    }

    public void onPageSelect() {
        if (isArrivalTime()) {
//            sendMsg(new Der(makeMsgId(), room(), Device.DER, Device.DER_ID));
            sendMsg(new GetAllState());
        }
    }
}
