package com.furniture.bean.control;

import android.content.Context;

import com.furniture.Config;
import com.furniture.R;
import com.furniture.base.BaseControlActivity;
import com.furniture.bean.json.control.DeviceCtrl;
import com.furniture.task.ActionClick;

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

public class SwitchAction extends BaseControlAction {

    /**
     * 例如新风开关 FAU1Ctrl
     */
    private String deviceName;
    private String pid;

    public SwitchAction(BaseControlActivity activity, String room, String controlName, String deviceName, String pid) {
        this(activity, room, controlName, deviceName, pid, null);
        setTask(new ActionClick() {
            @Override
            public void actionClick(boolean isLongClick) {
                super.actionClick(isLongClick);
                onClick(activity, isLongClick);
            }
        });
    }

    public SwitchAction(BaseControlActivity activity, String room, String controlName,
                        String deviceName, String pid, ActionClick task) {
        super(activity, room, "开启", controlName, R.drawable.btn_guanbi, R.drawable.btn_guanbi_s, task);
        this.deviceName = deviceName;
        this.pid = pid;
    }

    @Override
    public void onClick(Context context, boolean isLongClick) {
        if (context instanceof BaseControlActivity) {
            BaseControlActivity activity = (BaseControlActivity) context;
            if (Config.DEBUG) {
                activity.setOpen();
            } else {
                activity.send(new DeviceCtrl(getClickId(), room, deviceName, pid,!isOpen()));
            }
            activity.setOpen();
        }
    }
}
