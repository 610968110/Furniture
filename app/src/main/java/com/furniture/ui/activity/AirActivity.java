package com.furniture.ui.activity;

import android.content.Context;
import android.view.View;

import com.furniture.R;
import com.furniture.base.BaseControlActivity;
import com.furniture.bean.ActionBean;
import com.furniture.bean.SelectBean;
import com.furniture.bean.SocketBean;
import com.furniture.bean.action2.AirAction;
import com.furniture.bean.control.SwitchAction;
import com.furniture.bean.json.AllState;
import com.furniture.bean.json.control.DeviceSpeedAir;
import com.furniture.injector.components.AppComponent;
import com.furniture.injector.components.DaggerActivityComponent;
import com.furniture.injector.modules.ActivityModule;
import com.furniture.ui.view.control.AutoTextProgressBar;
import com.furniture.ui.view.control.MainControlView;

import java.util.ArrayList;
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
 * 新风
 */

public class AirActivity extends BaseControlActivity {

    private MainControlView mAirView;
    private AutoTextProgressBar mTextProgressBar;
    private ActionBean mMainBean;
    private int mTextPos;
    /**
     * 1-低
     * 2-中
     * 3-高
     * 4-自动
     */
    private int[] speed = new int[]{4, 1, 2, 3};

    public static XIntent getIntent(Context context, String name, String room, boolean isOpen) {
        XIntent intent = new XIntent(context, AirActivity.class);
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
        mAirView = new MainControlView(this);
        mAirView.setImg(R.drawable.pic_hui, R.drawable.pic_shebei_bg, R.drawable.icon_xinfeng);
        return mAirView;
    }

    @Override
    public View getProgressControlView() {
        mTextProgressBar = new AutoTextProgressBar(this);
        mTextProgressBar.setThumb(R.drawable.icon_xinfeng_xiao);
        mTextProgressBar.setTexts("自动", "低速", "中速", "高速");
        return mTextProgressBar;
    }

    @Override
    public List<ActionBean> getListControlList() {
        List<ActionBean> list = new ArrayList<>();
        //开关
        mMainBean = new SwitchAction(this, room, name, AirAction.NAME, AirAction.ID);
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
        mAirView.setOpen(true);
    }

    @Override
    public void setClose() {
        setDescTextColor(colors[0]);
        mMainBean.setOpen(false);
        mAirView.setOpen(false);
    }

    @Override
    public void onSocketMsgReceive(SocketBean bean, String devid) {

    }

    @Override
    public void initListener() {
        super.initListener();
        mTextProgressBar.setOnSeekBarChangeListener(new AutoTextProgressBar.OnSeekBarChangeListenerWithText() {
            @Override
            public void onTextPosSelect(int pos) {
                mTextPos = pos;
            }

            @Override
            public void onProgressChanged(int progress) {
                xLogUtil.e(this, "progress:" + progress);
                if (isControlOpen()) {
                    saveProgress(progress);
                    send(new DeviceSpeedAir(room, AirAction.NAME, AirAction.ID, speed[mTextPos]));
                }
            }
        });
    }

    @Override
    public void initData(AllState.Params.Item item) {
        mTextProgressBar.setProgress(getProgress());
    }

}
