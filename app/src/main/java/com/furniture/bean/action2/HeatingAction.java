package com.furniture.bean.action2;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.furniture.R;
import com.furniture.bean.ActionBean;
import com.furniture.bean.json.AllState;
import com.furniture.bean.json.control.DeviceCtrl;
import com.furniture.task.ActionClick;
import com.furniture.ui.activity.HeatingActivity;
import com.furniture.ui.activity.MainActivity;

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
 * @date 2018/9/5.
 * 地暖
 */

public class HeatingAction extends ActionBean {

    public static final String ID = "5b73c1540778b5689255a4ca";
    /**
     * 开关
     */
    public static final String NAME = "FH1";

    public HeatingAction(Context context, String room, String deviceName) {
        this(context, room, deviceName, null);
        setTask(new ActionClick() {
            @Override
            public void actionClick(boolean isLongClick) {
                super.actionClick(isLongClick);
                xLogUtil.e(this, "地暖");
                onClick(context, isLongClick);
            }
        });
    }

    public HeatingAction(Context context, String room, String deviceName, ActionClick task) {
        super("地暖", room, deviceName, R.drawable.icon_air_hot, R.drawable.icon_air_hot_s, task);
    }

    @Override
    public void onClick(Context context, boolean isLongClick) {
        if (isLongClick) {
            HeatingActivity.getIntent(context, TextUtils.isEmpty(getOtherName()) ? getName() : getOtherName(), room, getDeviceName(), isOpen()).start();
        } else {
            Log.e("xys", "~~~~~~~~~~~~~~~~:" + isOpen());
            open(context, !isOpen());
        }
    }

    public void open(Context context, boolean isOpen) {
        if (context instanceof MainActivity) {
            MainActivity activity = (MainActivity) context;
            xLogUtil.e(this, "地暖开关");
            activity.send(new DeviceCtrl(getClickId(), room, getDeviceName(), ID, isOpen));
        }
    }

    @Override
    public void onRefresh(AllState.Params.Item.Field field) {
        super.onRefresh(field);
        setOpen(field.isCtrlOpen());
    }
}
