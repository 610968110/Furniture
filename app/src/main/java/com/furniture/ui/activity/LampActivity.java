package com.furniture.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.furniture.R;
import com.furniture.base.BaseControlActivity;
import com.furniture.bean.ActionBean;
import com.furniture.bean.SelectBean;
import com.furniture.bean.SocketBean;
import com.furniture.bean.control.SwitchAction;
import com.furniture.bean.json.AllState;
import com.furniture.bean.json.control.DeviceLight;
import com.furniture.injector.components.AppComponent;
import com.furniture.injector.components.DaggerActivityComponent;
import com.furniture.injector.modules.ActivityModule;
import com.furniture.ui.view.control.HeatingView;
import com.furniture.ui.view.control.LampView;
import com.furniture.ui.view.control.TextProgressBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lbx.xtoollib.XIntent;
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
 * 各种射灯
 */

public class LampActivity extends BaseControlActivity {

    private LampView mLampView;
    private TextProgressBar mTextProgressBar;
    private ActionBean mMainBean;
    private String deviceName;

    public static XIntent getIntent(Context context, String name, String room, String deviceName, boolean isOpen) {
        XIntent intent = new XIntent(context, LampActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("room", room);
        intent.putExtra("isOpen", isOpen);
        intent.putExtra("deviceName", deviceName);
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
    public void initIntent(Intent intent) {
        super.initIntent(intent);
        deviceName = intent.getStringExtra("deviceName");
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mTextProgressBar.setMax(255);
    }

    @Override
    public void initData(AllState.Params.Item item) {
        if (item != null) {
            mTextProgressBar.setProgress(Integer.valueOf(item.field.Brtn));
        }
        mLampView.setProgress(getCircleProgress());
        mLampView.setProgressEnable(isOpen);
        int[] centerProgress = getCenterProgress();
        if (centerProgress.length >= 2) {
            xLogUtil.e("centerProgress:" + Arrays.toString(centerProgress));
            mLampView.selectPos(centerProgress[0], centerProgress[1]);
        }
    }

    @Override
    public View getMainControlView() {
        mLampView = new LampView(this);
        mLampView.setOnSelectColorListener(mColorListener);
        mLampView.setOnSeekBarProgressListener(mListener);
        return mLampView;
    }

    @Override
    public View getProgressControlView() {
        mTextProgressBar = new TextProgressBar(this);
        mTextProgressBar.setThumb(R.drawable.yanse_anniu);
        mTextProgressBar.setTexts("最暗", "微亮", "较亮", "很亮", "最亮");
        return mTextProgressBar;
    }

    @Override
    public List<ActionBean> getListControlList() {
        List<ActionBean> list = new ArrayList<>();
        //开关
        mMainBean = new SwitchAction(this, room, name, "", "");
//        list.add(mMainBean);
        return list;
    }

    @Override
    public ActionBean getControlBean() {
        return mMainBean;
    }

    @Override
    public List<SelectBean> selectClickList() {
        return null;
    }

    @Override
    public View.OnClickListener selectClick(View view) {
        return null;
    }

    @Override
    public void setOpen() {
        setDescTextColor(colors[1]);
        mMainBean.setOpen(true);
        mLampView.setOpen(true);
        mLampView.setProgressEnable(true);
    }

    @Override
    public void setClose() {
        setDescTextColor(colors[0]);
        mMainBean.setOpen(false);
        mLampView.setOpen(false);
        mLampView.setProgressEnable(false);
    }

    @Override
    public void onSocketMsgReceive(SocketBean bean, String devid) {

    }

    @Override
    public void initListener() {
        super.initListener();
        //下方进度条
        mTextProgressBar.setOnSeekBarChangeListener(progress -> {
            xLogUtil.e(this, "客厅射灯 progress:" + progress);
            if (isControlOpen()) {
                send(new DeviceLight(room, deviceName, "", progress));
            }
        });
    }

    /**
     * 大圈周边进度条
     */
    private HeatingView.OnSeekBarChangeListener mListener = new HeatingView.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(int progress) {
            xLogUtil.e(this, "客厅射灯 circle progress:" + progress);
            if (isControlOpen()) {
                saveCircleProgress(progress);
            }
        }

        @Override
        public void onStartTrackingTouch() {

        }

        @Override
        public void onStopTrackingTouch() {

        }
    };

    /**
     * 大圈颜色进度条
     */
    private LampView.OnColorSelectListener mColorListener = new LampView.OnColorSelectListener() {
        @Override
        public void onColorSelect(int color, int x, int y) {
//            setDescText("测试");
            setDescTextColorInt(color);
        }

        @Override
        public void onColorUpSelect(int color, int x, int y) {
            xLogUtil.e(this, "color:" + color);
            if (isControlOpen()) {
                saveCenterProgress(x, y);
            }
        }
    };
}
