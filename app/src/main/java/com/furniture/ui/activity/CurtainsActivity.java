package com.furniture.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.furniture.R;
import com.furniture.base.BaseControlActivity;
import com.furniture.bean.ActionBean;
import com.furniture.bean.SelectBean;
import com.furniture.bean.SocketBean;
import com.furniture.bean.action2.CurtainsAction;
import com.furniture.bean.json.AllState;
import com.furniture.bean.json.control.DeviceCtrl;
import com.furniture.bean.json.control.DeviceCus;
import com.furniture.injector.components.AppComponent;
import com.furniture.injector.components.DaggerActivityComponent;
import com.furniture.injector.modules.ActivityModule;
import com.furniture.task.ActionClick;
import com.furniture.ui.view.control.MainControlView;
import com.furniture.ui.view.control.TextProgressBar;

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
 * @date 2018/8/25.
 * 窗帘
 */

public class CurtainsActivity extends BaseControlActivity {

    private MainControlView mCurtainsView;
    private TextProgressBar mTextProgressBar;
    private String deviceName = "";

    public static XIntent getIntent(Context context) {
        return new XIntent(context, CurtainsActivity.class);
    }

    public static XIntent getIntent(Context context, String name, String room, String deviceName, boolean isOpen) {
        XIntent intent = new XIntent(context, CurtainsActivity.class);
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
    public View getMainControlView() {
        mCurtainsView = new MainControlView(this);
        mCurtainsView.setImg(R.drawable.pic_shebei_bg, R.drawable.pic_shebei_bg, R.drawable.icon_chuanglian_kai);
        return mCurtainsView;
    }

    @Override
    public View getProgressControlView() {
        mTextProgressBar = new TextProgressBar(this);
        mTextProgressBar.setTexts("全关", "全开");
        return mTextProgressBar;
    }

    @Override
    public List<ActionBean> getListControlList() {
        List<ActionBean> list = new ArrayList<>();
        ActionBean bean = new ActionBean("关闭", room, R.drawable.btn_chuanglian_guan, R.drawable.btn_chuanglian_guan_s);
        bean.setTask(new ActionClick() {
            @Override
            public void actionClick(boolean isLongClick) {
                super.actionClick(isLongClick);
                boolean open = bean.isOpen();
                xLogUtil.e(CurtainsActivity.this, "关闭:" + !open);
//                if (open) {
//                    bean.setOpen(false);
//                } else {
//                    bean.setOpen(true);
//                }
                DeviceCtrl deviceCtrl = new DeviceCtrl(room, CurtainsAction.NAME, "", isOpen);
                deviceCtrl.getParams().getField().setCtrl(0);
                send(deviceCtrl);
            }
        });
        ActionBean bean1 = new ActionBean("停止", room, R.drawable.btn_chuanglian_stop, R.drawable.btn_chuanglian_stop_s);
        bean1.setTask(new ActionClick() {
            @Override
            public void actionClick(boolean isLongClick) {
                super.actionClick(isLongClick);
                boolean open = bean1.isOpen();
                xLogUtil.e(CurtainsActivity.this, "停止:" + !open);
//                if (open) {
//                    bean1.setOpen(false);
//                } else {
//                    bean1.setOpen(true);
//                }
                //isOpen没有用，被替换成了2
                DeviceCtrl deviceCtrl = new DeviceCtrl(room, CurtainsAction.NAME, "", isOpen);
                deviceCtrl.getParams().getField().setCtrl(2);
                send(deviceCtrl);
            }
        });
        ActionBean bean2 = new ActionBean("开启", room, R.drawable.btn_chuanglian_kai, R.drawable.btn_chuanglian_kai_s);
        bean2.setTask(new ActionClick() {
            @Override
            public void actionClick(boolean isLongClick) {
                super.actionClick(isLongClick);
                boolean open = bean2.isOpen();
                xLogUtil.e(CurtainsActivity.this, "开启:" + !open);
//                if (open) {
//                    bean2.setOpen(false);
//                } else {
//                    bean2.setOpen(true);
//                }
                DeviceCtrl deviceCtrl = new DeviceCtrl(room, CurtainsAction.NAME, "", isOpen);
                deviceCtrl.getParams().getField().setCtrl(1);
                send(deviceCtrl);
            }
        });
        list.add(bean);
        list.add(bean1);
        list.add(bean2);
        return list;
    }

    @Override
    public ActionBean getControlBean() {
        return null;
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

    }

    @Override
    public void setClose() {

    }

    @Override
    public void onSocketMsgReceive(SocketBean bean, String devid) {

    }

    @Override
    public void initListener() {
        super.initListener();
        mTextProgressBar.setOnSeekBarChangeListener(progress -> {
            xLogUtil.e(this, "progress:" + progress);
            if (isControlOpen()) {
//                saveProgress(progress);
                send(new DeviceCus(room, deviceName, CurtainsAction.ID, progress));
//                EventBus.getDefault().post(new NotifyRoomItem(2).setRoom(room).setName(name).setOpen(progress != 0));
            }
        });
    }

    @Override
    public void initData(AllState.Params.Item item) {
        if (item != null) {
            mTextProgressBar.setProgress((int) item.field.FB);
        }
    }
}
