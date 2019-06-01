package com.furniture.bean.action;

import android.content.Context;
import android.os.Handler;

import com.furniture.R;
import com.furniture.bean.ActionBean;
import com.furniture.bean.json.AllState;
import com.furniture.bean.json.control.DeviceWet;
import com.furniture.event.NotifyRoomItem;
import com.furniture.task.ActionClick;
import com.furniture.ui.activity.MainActivity;

import org.greenrobot.eventbus.EventBus;

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

public class WetAction extends ActionBean {

    public static final String NAME = "Wet";
    public static final String ID = "";

    public WetAction(Context context, String room, String deviceName) {
        this(context, room, deviceName, null);
        setTask(new ActionClick() {
            @Override
            public void actionClick(boolean isLongClick) {
                super.actionClick(isLongClick);
                xLogUtil.e(this, "湿");
                onClick(context, isLongClick);
            }
        });
    }

    public WetAction(Context context, String room, String deviceName, ActionClick task) {
        super("湿", room, deviceName, R.drawable.icon_air_wet, R.drawable.icon_air_wet_s, task);
    }

    @Override
    public void onClick(Context context, boolean isLongClick) {
        if (!isLongClick) {
            open(context, !isOpen());
        }
    }

    public void open(Context context, boolean isOpen) {
        if (context instanceof MainActivity) {
            MainActivity activity = (MainActivity) context;
            xLogUtil.e(this, "湿");
            activity.send(new DeviceWet(room, getDeviceName(), ID, isOpen));
            setOpen(true);
            NotifyRoomItem event = new NotifyRoomItem(1);
            EventBus.getDefault().post(event);
            new Handler().postDelayed(() -> {
                setOpen(false);
                EventBus.getDefault().post(event);
            }, 1000);
        }
    }

    public String controlDeviceName() {
        return getDeviceName() + NAME;
    }

    @Override
    public void onRefresh(AllState.Params.Item.Field field) {
        super.onRefresh(field);
        setOpen(false);
    }
}
