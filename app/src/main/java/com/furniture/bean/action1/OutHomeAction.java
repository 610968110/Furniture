package com.furniture.bean.action1;

import android.content.Context;

import com.furniture.Config;
import com.furniture.R;
import com.furniture.bean.ActionBean;
import com.furniture.bean.json.AllState;
import com.furniture.bean.json.control.DeviceAction2;
import com.furniture.constant.Device;
import com.furniture.event.ResetOpenAction;
import com.furniture.impl.IHome;
import com.furniture.impl.IModeAction;
import com.furniture.task.ActionClick;
import com.furniture.ui.activity.MainActivity;

import org.greenrobot.eventbus.EventBus;

import lbx.xtoollib.XTools;
import lbx.xtoollib.phone.xLogUtil;

import static com.furniture.constant.DoMain.OUT_HOME_SAVE;

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

public class OutHomeAction extends ActionBean implements IModeAction,IHome {

    public static final String NAME = Device.HOME + "Mode";

    public OutHomeAction(Context context, String room, String deviceName) {
        this(context, room, deviceName, null);
        setTask(new ActionClick() {
            @Override
            public void actionClick(boolean isLongClick) {
                super.actionClick(isLongClick);
                xLogUtil.e(this, "离家");
                onClick(context, isLongClick);
            }
        });
    }

    public OutHomeAction(Context context, String room, String deviceName, ActionClick task) {
        super("离家", room, deviceName, R.drawable.icon_scene_lihome, R.drawable.icon_scene_lihome_s, task);
        refreshOtherName();
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
            xLogUtil.e(this, "离家");
            EventBus.getDefault().post(new ResetOpenAction(1));
            if (Config.APP_TYPE == Config.TYPE_DEMO_SHANGHAI)
                activity.send(new DeviceAction2(room, getDeviceName(), "", 0), true);
            else
                activity.send(new DeviceAction2(room, getDeviceName(), "", 2), true);
            setOpen(isOpen);
        }
    }

    public String controlDeviceName() {
        return getDeviceName() + NAME;
    }

    @Override
    public void onRefresh(AllState.Params.Item.Field field) {
        super.onRefresh(field);
        setOpen(field.LM == 2);
    }

    @Override
    public String getModeDeviceName() {
        return NAME;
    }

    @Override
    public boolean isHome() {
        return true;
    }

    @Override
    public void refreshOtherName() {
        setOtherName( XTools.SpUtil().getString(OUT_HOME_SAVE, "离家"));
    }
}
