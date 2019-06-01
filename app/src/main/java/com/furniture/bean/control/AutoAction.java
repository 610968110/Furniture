package com.furniture.bean.control;

import android.content.Context;

import com.furniture.R;
import com.furniture.base.BaseControlActivity;
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

public class AutoAction extends BaseControlAction {

    public AutoAction(BaseControlActivity activity, String room, String controlName) {
        this(activity, room, controlName, null);
    }

    public AutoAction(BaseControlActivity activity, String room, String controlName, ActionClick task) {
        super(activity, room, "自动", controlName, R.drawable.btn_dinuan, R.drawable.btn_dinuan_s, task);
    }

    @Override
    public void onClick(Context context, boolean isLongClick) {
        super.onClick(context, isLongClick);
    }
}
