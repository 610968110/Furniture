package com.furniture.ui.activity;

import android.content.Context;
import android.view.View;

import com.furniture.R;
import com.furniture.base.BaseControlActivity;
import com.furniture.bean.ActionBean;
import com.furniture.bean.SelectBean;
import com.furniture.bean.SocketBean;
import com.furniture.bean.control.AutoAction;
import com.furniture.bean.control.SwitchAction;
import com.furniture.bean.json.AllState;
import com.furniture.injector.components.AppComponent;
import com.furniture.injector.components.DaggerActivityComponent;
import com.furniture.injector.modules.ActivityModule;
import com.furniture.ui.view.control.HeatingView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
 * @date 2018/8/26.
 * 地暖
 */

public class HeatingActivity extends BaseControlActivity {

    private HeatingView mHeatingView;
    private ActionBean mMainBean;
    private static final int MAX = 50;
    private static final int MIN = 16;

    public static XIntent getIntent(Context context, String name, String room, boolean isOpen) {
        XIntent intent = new XIntent(context, HeatingActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("room", room);
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
        mHeatingView = new HeatingView(this);
        mHeatingView.setOnSeekBarProgressListener(mListener);
        mHeatingView.setImg(R.drawable.pic_hui, R.drawable.pic_shebei_diduan);
        mHeatingView.set(MAX, MIN);
        return mHeatingView;
    }

    @Override
    public View getProgressControlView() {
        return null;
    }

    @Override
    public List<ActionBean> getListControlList() {
        List<ActionBean> list = new ArrayList<>();
        ActionBean bean = new AutoAction(this, room, name);
        //开关
        mMainBean = new SwitchAction(this, room, name,"","");
        list.add(bean);
        list.add(mMainBean);
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
        mHeatingView.setOpen(true);
        mMainBean.setOpen(true);
        mHeatingView.setProgressEnable(true);
    }

    @Override
    public void setClose() {
        setDescTextColor(colors[0]);
        mHeatingView.setOpen(false);
        mMainBean.setOpen(false);
        mHeatingView.setProgressEnable(false);
    }

    @Override
    public void onSocketMsgReceive(SocketBean bean, String devid) {

    }

    @Override
    public void initData(AllState.Params.Item item) {
        setDesc(18);
        mHeatingView.setProgress(getCircleProgress());
        mHeatingView.setProgressEnable(isOpen);
    }

    private void setDesc(int temp) {
        setDescText(String.format(Locale.CHINA, "当前室内温度为%s℃", temp));
    }

    private HeatingView.OnSeekBarChangeListener mListener = new HeatingView.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(int progress) {
            saveCircleProgress(progress);
            xLogUtil.e("progress:" + progress);
            int realProgress = progress2Real(MAX, MIN, progress);
            xLogUtil.e(this, "地暖:" + realProgress);
        }

        @Override
        public void onStartTrackingTouch() {

        }

        @Override
        public void onStopTrackingTouch() {

        }
    };
}
