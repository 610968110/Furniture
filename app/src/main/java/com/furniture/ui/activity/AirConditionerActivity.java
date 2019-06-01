package com.furniture.ui.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.furniture.R;
import com.furniture.base.BaseControlActivity;
import com.furniture.bean.ActionBean;
import com.furniture.bean.SelectBean;
import com.furniture.bean.SocketBean;
import com.furniture.bean.action2.AirConditionerAction;
import com.furniture.bean.control.SwitchAction;
import com.furniture.bean.json.AllState;
import com.furniture.bean.json.control.DeviceAirCCM;
import com.furniture.bean.json.control.DeviceSpeedC;
import com.furniture.bean.json.control.DeviceTemp;
import com.furniture.event.AirConditionerTempBean;
import com.furniture.injector.components.AppComponent;
import com.furniture.injector.components.DaggerActivityComponent;
import com.furniture.injector.modules.ActivityModule;
import com.furniture.ui.view.control.AirConditionerView;
import com.furniture.ui.view.control.TextProgressBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import lbx.xtoollib.XIntent;
import lbx.xtoollib.base.BaseDataAdapter;
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
 * @date 2018/8/27.
 * 空调
 */

public class AirConditionerActivity extends BaseControlActivity {

    private AirConditionerView mAirConditionerView;
    private TextProgressBar mTextProgressBar;
    private ActionBean mMainBean;
    private static final int MAX = 30;
    private static final int MIN = 16;
    /**
     * -自动
     * 1-低
     * 2-中
     * 3-高
     */
    private int[] speed = new int[]{1, 2, 3};

    public static XIntent getIntent(Context context, String name, String room, String deviceName, boolean isOpen) {
        XIntent intent = new XIntent(context, AirConditionerActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("room", room);
        intent.putExtra("deviceName", deviceName);
        intent.putExtra("isOpen", isOpen);
        return intent;
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
    public View getMainControlView() {
        mAirConditionerView = new AirConditionerView(this);
        mAirConditionerView.setImg(R.drawable.pic_hui, R.drawable.pic_shebei_diduan);
        mAirConditionerView.set(MAX, MIN);
        return mAirConditionerView;
    }

    @Override
    public View getProgressControlView() {
        mTextProgressBar = new TextProgressBar(this);
        mTextProgressBar.setThumb(R.drawable.icon_xinfeng_xiao);
        mTextProgressBar.setTexts("低速", "中速", "高速");
        return mTextProgressBar;
    }

    @Override
    public List<ActionBean> getListControlList() {
        List<ActionBean> list = new ArrayList<>();
        //开关
        mMainBean = new SwitchAction(this, room, name, AirConditionerAction.NAME, AirConditionerAction.ID);
//        list.add(mMainBean);
        return list;
    }

    @Override
    public ActionBean getControlBean() {
        return mMainBean;
    }

    @Override
    public List<SelectBean> selectClickList() {
        List<SelectBean> list = new ArrayList<>();
        list.add(new SelectBean("制冷", "1"));
//        list.add(new SelectBean("除湿", "4"));
        list.add(new SelectBean("通风", "3"));
        list.add(new SelectBean("制热", "2"));
//        list.add(new SelectBean("自动", "1"));
        return list;
    }

    @Override
    public View.OnClickListener selectClick(View view) {
        return v -> makeWindow(new BaseDataAdapter.OnItemClickListener<SelectBean>() {
            @Override
            public void onItemClick(RecyclerView recyclerView, int id, int position, SelectBean entity) {
                xLogUtil.e(this, "entity:" + entity);
                if (isControlOpen()) {
                    select(entity);
                    send(new DeviceAirCCM(room, AirConditionerAction.NAME, AirConditionerAction.ID, Integer.valueOf(entity.getType())));
                }
            }

            @Override
            public void onItemLongClick(RecyclerView recyclerView, int id, int position, SelectBean entity) {

            }
        }).showAsDropDown(view, 0, 5);
    }

    @Override
    public void setOpen() {
        setDescTextColor(colors[1]);
        mAirConditionerView.setOpen(true);
        mAirConditionerView.setProgressEnable(true);
        mMainBean.setOpen(true);
    }

    @Override
    public void setClose() {
        setDescTextColor(colors[0]);
        mAirConditionerView.setOpen(false);
        mAirConditionerView.setProgressEnable(false);
        mMainBean.setOpen(false);
    }

    @Override
    public void onSocketMsgReceive(SocketBean bean, String devid) {

    }

    private void setDesc(int temp) {
        setDescText(String.format(Locale.CHINA, "当前室内温度为%s℃", temp));
    }

    @Override
    public void initListener() {
        super.initListener();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        mTextProgressBar.setOnSeekBarChangeListener(progress -> {
            int pos = progress2Pos(progress);
            xLogUtil.e(this, "progress:" + pos);
            if (isControlOpen()) {
                send(new DeviceSpeedC(room, AirConditionerAction.NAME, AirConditionerAction.ID, speed[pos]));
            }
            saveProgress(progress);
        });
        mAirConditionerView.setOnTempSelectListener(new AirConditionerView.OnTempSelectListener() {
            @Override
            public void onSelect(int temp) {
                xLogUtil.e(this, "空调:" + temp);
                if (isControlOpen()) {
                    saveCircleProgress(temp);
                }
            }
        });
    }

    @Override
    public void initData(AllState.Params.Item item) {
        xLogUtil.e("空调:" + item);
        mAirConditionerView.setCenterText(getCircleProgress() + "");
        //左上方文字 制冷制热
        if (item != null) {
            AllState.Params.Item.Field field = item.field;
            select(new SelectBean("", field.Mode));
            int setT = (int) field.SetT;
            saveCircleProgress(setT);
            mAirConditionerView.setCenterText(setT + "");
            setDesc((int) field.Temp);
        }
        //setDesc(18);
        mTextProgressBar.setProgress(getProgress());
        mAirConditionerView.setProgressEnable(isOpen);
    }

    private int progress2Pos(int progress) {
        int pos = 0;
        if (progress > 90) {
            pos = 3;
        } else if (progress > 65) {
            pos = 2;
        } else if (progress > 10) {
            pos = 1;
        }
        return pos;
    }


    /**
     * 空调设置页面选择温度
     */
    @Subscribe
    public void onTempSelect(AirConditionerTempBean bean) {
        int temp = bean.getTemp();
        xLogUtil.e(this, "空调设置温度:" + temp);
        mAirConditionerView.setCenterText(temp + "");
        if (isControlOpen()) {
            saveCircleProgress(temp);
        }
        send(new DeviceTemp(room, AirConditionerAction.NAME, AirConditionerAction.ID, temp));
    }

    @Override
    protected void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }
    //    private HeatingView.OnSeekBarChangeListener mListener = new HeatingView.OnSeekBarChangeListener() {
//        @Override
//        public void onProgressChanged(int progress) {
//            int realProgress = progress2Real(MAX, MIN, progress);
//            xLogUtil.e(this, "空调:" + realProgress);
//            mRealProgress = realProgress;
//            mAirConditionerView.setCenterText(realProgress + "");
//            if (isControlOpen()) {
//                saveCircleProgress(progress);
//            }
//        }
//
//        @Override
//        public void onStartTrackingTouch() {
//            xLogUtil.e("onStartTrackingTouch");
//        }
//
//        @Override
//        public void onStopTrackingTouch() {
//            xLogUtil.e("onStopTrackingTouch");
//            if (isControlOpen() && mRealProgress != -1) {
//                send(new DeviceTemp(room, AirConditionerAction.NAME, AirConditionerAction.ID, mRealProgress));
//                mRealProgress = -1;
//            }
//        }
//    };
}
