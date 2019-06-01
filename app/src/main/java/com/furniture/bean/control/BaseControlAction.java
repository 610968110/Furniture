package com.furniture.bean.control;

import android.content.Context;
import android.support.annotation.DrawableRes;

import com.furniture.base.BaseControlActivity;
import com.furniture.bean.ActionBean;
import com.furniture.task.ActionClick;

import lbx.xtoollib.XTools;

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

public abstract class BaseControlAction extends ActionBean {

    protected String controlName;

    public BaseControlAction(BaseControlActivity activity, String room, String name, String controlName,
                             @DrawableRes int icon, @DrawableRes int openIcon, ActionClick task) {
        super(name, room, "", icon, openIcon, task);
        this.controlName = controlName;
        setOpen(XTools.SpUtil().getBoolean(room + getName() + controlName, false));
        if (task == null) {
            setTask(new ActionClick() {
                @Override
                public void actionClick(boolean isLongClick) {
                    super.actionClick(isLongClick);
                    onClick(activity, isLongClick);
                }
            });
        }
    }

    @Override
    public void onClick(Context context, boolean isLongClick) {
        setOpen(!isOpen());
        XTools.SpUtil().putBoolean(room + getName() + controlName, isOpen());
    }
}
