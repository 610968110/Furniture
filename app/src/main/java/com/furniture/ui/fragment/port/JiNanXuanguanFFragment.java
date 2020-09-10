package com.furniture.ui.fragment.port;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.View;

import com.furniture.bean.ActionBean;
import com.furniture.bean.action1.SleepAction;
import com.furniture.bean.action1.WeekUpAction;
import com.furniture.bean.action2.AirConditionerAction;
import com.furniture.bean.action2.CurtainsAction;
import com.furniture.bean.action2.ScreenWindowAction;
import com.furniture.bean.jinanbean.SupperLight;
import com.furniture.ui.fragment.room.DinnerRoomFragment;

import static com.furniture.constant.Device.SCE;

/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: liboxin
 * Email: liboxin@worktile.com
 * Date: 2019/6/11
 * Time: 18:45
 * Desc:
 */

public class JiNanXuanguanFFragment extends DinnerRoomFragment {

    public static final String ROOM = "R2";

    public static JiNanXuanguanFFragment newInstance(@DrawableRes int bg) {
        Bundle args = new Bundle();
        args.putInt("bg", bg);
        JiNanXuanguanFFragment fragment = new JiNanXuanguanFFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public ActionBean[] initAllAction() {
        return new ActionBean[]{
                //空调
                new AirConditionerAction(getActivity(), ROOM, "AHU1"),
//                //新风
//                new AirAction(getActivity(), ROOM, "FAU1"),
                new SupperLight(getActivity(), ROOM, "L1", "衣帽间筒灯"),
                new SupperLight(getActivity(), ROOM, "L2", "走廊筒灯"),
                new SupperLight(getActivity(), ROOM, "L3", "卧室主灯"),
                new CurtainsAction(getActivity(), ROOM, "Curt1", "布帘"),
                new ScreenWindowAction(getActivity(), ROOM, "Gau1", "纱帘"),

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
