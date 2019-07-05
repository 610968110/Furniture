package com.furniture.bean.jinanbean;

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
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: liboxin
 * Email: liboxin@worktile.com
 * Date: 2019/6/11
 * Time: 19:34
 * Desc:
 */

public class SupperLight extends ActionBean {

    public static final String ID = "";
    private String showName = "";

    public SupperLight(Context context, String room, String deviceName, String showName) {
        this(context, room, deviceName, showName, null);
        setTask(new ActionClick() {
            @Override
            public void actionClick(boolean isLongClick) {
                super.actionClick(isLongClick);
                xLogUtil.e(this, showName);
                onClick(context, isLongClick);
            }
        });
    }

    public SupperLight(Context context, String room, String deviceName, String showName, ActionClick task) {
        super(showName, room, deviceName, R.drawable.icon_light_door, R.drawable.icon_light_door_s, task);
        this.showName = showName;
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
            xLogUtil.e(this, showName);
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
