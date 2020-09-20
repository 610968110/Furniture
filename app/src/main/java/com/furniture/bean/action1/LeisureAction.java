package com.furniture.bean.action1;

import android.content.Context;

import com.furniture.R;
import com.furniture.bean.ActionBean;
import com.furniture.bean.json.AllState;
import com.furniture.bean.json.control.DeviceAction2;
import com.furniture.event.ResetOpenAction;
import com.furniture.impl.IModeAction;
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

public class LeisureAction extends ActionBean implements IModeAction {

    public static final String NAME = "Dining";

    public LeisureAction(Context context, String room, String deviceName) {
        this(context, room, deviceName, null);
        setTask(new ActionClick() {
            @Override
            public void actionClick(boolean isLongClick) {
                super.actionClick(isLongClick);
                xLogUtil.e(this, "休闲");
                onClick(context, isLongClick);
            }
        });
    }

    public LeisureAction(Context context, String room, String deviceName, ActionClick task) {
        super("休闲", room, deviceName, R.drawable.icon_scene_eat, R.drawable.icon_scene_eat_s, task);
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
            xLogUtil.e(this, "休闲");
            EventBus.getDefault().post(new ResetOpenAction(1));
            if (getOpen() == -1) {
                activity.send(new DeviceAction2(room, getDeviceName(), "", 3), true);
            } else {
                activity.send(new DeviceAction2(room, getDeviceName(), "", getOpen()), true);
            }
            setOpen(isOpen);
        }
    }

    public String controlDeviceName() {
        return getDeviceName() + NAME;
    }

    @Override
    public void onRefresh(AllState.Params.Item.Field field) {
        super.onRefresh(field);
        if (getOpen() == -1) {
            setOpen(field.LM == 3);
        } else {
            setOpen(field.LM == getOpen());
        }
    }

    @Override
    public String getModeDeviceName() {
        return NAME;
    }

    @Override
    public boolean isHome() {
        return false;
    }
}
