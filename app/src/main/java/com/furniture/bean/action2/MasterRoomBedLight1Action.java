package com.furniture.bean.action2;

import android.content.Context;
import android.text.TextUtils;

import com.furniture.R;
import com.furniture.bean.ActionBean;
import com.furniture.bean.json.AllState;
import com.furniture.bean.json.control.DeviceLightCtrl;
import com.furniture.event.NotifyRoomItem;
import com.furniture.task.ActionClick;
import com.furniture.ui.activity.LampActivity;
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

public class MasterRoomBedLight1Action extends ActionBean {

    public static final String ID = "";

    public MasterRoomBedLight1Action(Context context, String room, String deviceName) {
        this(context, room, deviceName, null);
        setTask(new ActionClick() {
            @Override
            public void actionClick(boolean isLongClick) {
                super.actionClick(isLongClick);
                xLogUtil.e(this, "主卧床头灯1");
                onClick(context, isLongClick);
            }
        });
    }

    public MasterRoomBedLight1Action(Context context, String room, String deviceName, ActionClick task) {
        super("主卧床头灯1", room, deviceName, R.drawable.icon_light_door, R.drawable.icon_light_door_s, task);
    }

    @Override
    public void onClick(Context context, boolean isLongClick) {
        if (isLongClick) {
            LampActivity.getIntent(context, TextUtils.isEmpty(getOtherName()) ? getName() : getOtherName(), room, getDeviceName(), isOpen()).start();
        } else {
            open(context, !isOpen());
        }
    }

    public void open(Context context, boolean isOpen) {
        if (context instanceof MainActivity) {
            MainActivity activity = (MainActivity) context;
            xLogUtil.e(this, "主卧床头灯1");
            activity.send(new DeviceLightCtrl(room, getDeviceName(), ID, isOpen));
            setOpen(isOpen);
            EventBus.getDefault().post(new NotifyRoomItem(2));
        }
    }

    @Override
    public void onRefresh(AllState.Params.Item.Field field) {
        super.onRefresh(field);
        setOpen(field.isStasOpen());
    }
}
