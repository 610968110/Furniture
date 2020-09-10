package com.furniture.ui.fragment.port;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.View;

import com.furniture.bean.ActionBean;
import com.furniture.bean.action1.SleepAction;
import com.furniture.bean.action1.WeekUpAction;
import com.furniture.bean.action2.AirConditionerAction;
import com.furniture.bean.jinanbean.SupperLight;
import com.furniture.ui.fragment.room.BookRoomFragment;

import static com.furniture.constant.Device.SCE;

/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: liboxin
 * Email: liboxin@worktile.com
 * Date: 2019/6/11
 * Time: 18:47
 * Desc:
 */

public class JiNanB2FFragment extends BookRoomFragment {

    public static final String ROOM = "R4";

    public static JiNanB2FFragment newInstance(@DrawableRes int bg) {
        Bundle args = new Bundle();
        args.putInt("bg", bg);
        JiNanB2FFragment fragment = new JiNanB2FFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public ActionBean[] initAllAction() {
        return new ActionBean[]{
                //空调
                new AirConditionerAction(getActivity(), ROOM, "AHU1"),
                new SupperLight(getActivity(), ROOM, "L1", "卧室主灯")
        };
    }

    @Override
    public ActionBean[] initCenterAction() {
        return new ActionBean[]{
                // 起床
                new WeekUpAction(getActivity(), ROOM, SCE),
                // 睡眠
                new SleepAction(getActivity(), ROOM, SCE)
        };
    }

    @Override
    protected int showEnvironmentView() {
        return View.INVISIBLE;
    }
}
