package com.furniture.bean.action1;

import android.content.Context;
import android.util.Log;

import com.furniture.Config;
import com.furniture.R;
import com.furniture.bean.ActionBean;
import com.furniture.bean.json.AllState;
import com.furniture.bean.json.control.DeviceAction2;
import com.furniture.bean.json.control.DeviceHome;
import com.furniture.event.ResetOpenAction;
import com.furniture.impl.IModeAction;
import com.furniture.task.ActionClick;
import com.furniture.ui.activity.MainActivity;

import org.greenrobot.eventbus.EventBus;

import lbx.xtoollib.phone.xLogUtil;

import static com.furniture.constant.Device.HOME;

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

public class MeetingGuestsAction extends ActionBean implements IModeAction {

    public static final String NAME = "Meeting";

    public MeetingGuestsAction(Context context, String room, String deviceName) {
        this(context, room, deviceName, null);
        setTask(new ActionClick() {
            @Override
            public void actionClick(boolean isLongClick) {
                super.actionClick(isLongClick);
                xLogUtil.e(this, "会客");
                onClick(context, isLongClick);
            }
        });
    }

    public MeetingGuestsAction(Context context, String room, String deviceName, ActionClick task) {
        super("会客", room, deviceName, R.drawable.icon_scene_meet, R.drawable.icon_scene_meet_s, task);
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
            xLogUtil.e(this, "会客");
            EventBus.getDefault().post(new ResetOpenAction(1));
            if (Config.APP_TYPE == Config.TYPE_DEMO_SHANGHAI) {
                activity.send(new DeviceHome(room, HOME, "", isOpen), true);
            } else {
                activity.send(new DeviceAction2(room, getDeviceName(), "", 1), true);
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
        Log.e("xys", "~~~~~:" + field);
        setOpen(field.LM == 1);
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
