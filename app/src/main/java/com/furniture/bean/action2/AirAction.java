package com.furniture.bean.action2;

import android.content.Context;
import android.text.TextUtils;

import com.furniture.R;
import com.furniture.bean.ActionBean;
import com.furniture.bean.json.AllState;
import com.furniture.bean.json.control.DeviceCtrl;
import com.furniture.task.ActionClick;
import com.furniture.ui.activity.AirActivity;
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
 */

public class AirAction extends ActionBean {

    public static final String ID = "5b73dffb0778b51f87a30a69";
    /**
     * 开关
     */
    public static final String NAME = "FAU1";

    public AirAction(Context context, String room, String deviceName) {
        this(context, room, deviceName, null);
        setTask(new ActionClick() {
            @Override
            public void actionClick(boolean isLongClick) {
                super.actionClick(isLongClick);
                xLogUtil.e(this, "新风");
                onClick(context, isLongClick);
            }
        });
    }

    public AirAction(Context context, String room, String deviceName, ActionClick task) {
        super("新风", room, deviceName, R.drawable.icon_air_freshair, R.drawable.icon_air_freshair_s, task);
    }

    @Override
    public void onClick(Context context, boolean isLongClick) {
        if (isLongClick) {
            AirActivity.getIntent(context, TextUtils.isEmpty(getOtherName()) ? getName() : getOtherName(), room, isOpen()).start();
        } else {
            open(context, !isOpen());
        }
    }

    public void open(Context context, boolean isOpen) {
        if (context instanceof MainActivity) {
            MainActivity activity = (MainActivity) context;
            xLogUtil.e(this, "新风开关");
            activity.send(new DeviceCtrl(getClickId(), room, getDeviceName(), ID, isOpen));
        }
    }

    @Override
    public void onRefresh(AllState.Params.Item.Field field) {
        super.onRefresh(field);
        setOpen(field.isCtrlOpen());
    }
}
