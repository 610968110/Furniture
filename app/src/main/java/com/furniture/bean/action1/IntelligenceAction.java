package com.furniture.bean.action1;

import android.content.Context;

import com.furniture.Config;
import com.furniture.R;
import com.furniture.bean.ActionBean;
import com.furniture.bean.json.AllState;
import com.furniture.bean.json.control.DeviceHome;
import com.furniture.bean.json.control.IntelligenceHome;
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
 * 0:无模式  1：会客  2：用餐   3：阅读   4：观影
 */

public class IntelligenceAction extends ActionBean implements IModeAction {

    public static final String NAME = "Intgt";
    public static final String ID = "";

    public IntelligenceAction(Context context, String room, String deviceName) {
        this(context, room, deviceName, null);
        setTask(new ActionClick() {
            @Override
            public void actionClick(boolean isLongClick) {
                super.actionClick(isLongClick);
                xLogUtil.e(this, "智能");
                onClick(context, isLongClick);
            }
        });
    }

    public IntelligenceAction(Context context, String room, String deviceName, ActionClick task) {
        super("智能", room, deviceName, R.drawable.icon_scene_zhineng, R.drawable.icon_scene_zhineng_s, task);
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
            xLogUtil.e(this, "智能");
            EventBus.getDefault().post(new ResetOpenAction(1));
            if (Config.APP_TYPE == Config.TYPE_DEMO_SHANGHAI) {
                activity.send(new DeviceHome(room, HOME, "", isOpen), true);
            } else {
                activity.send(new IntelligenceHome(room, getDeviceName(), "", isOpen), true);
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
        setOpen(field.LM == 0);
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
