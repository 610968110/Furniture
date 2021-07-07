package com.furniture.ui.fragment.zhongtiejian2;

import android.os.Bundle;
import android.support.annotation.DrawableRes;

import com.furniture.bean.ActionBean;
import com.furniture.bean.action2.AirAction;
import com.furniture.bean.action2.AirConditionerAction;
import com.furniture.bean.action2.CurtainsAction;
import com.furniture.bean.action2.ScreenWindowAction;
import com.furniture.bean.jinanbean.SupperLight;
import com.furniture.ui.fragment.room.DinnerRoomFragment;

/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: liboxin
 * Email: liboxin@worktile.com
 * Date: 2019/6/11
 * Time: 18:45
 * Desc:
 */

public class JiNanXuanguanFFragment extends DinnerRoomFragment {

    public static final String ROOM = "R5";

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
                //新风
                new AirAction(getActivity(), ROOM, "FAU1"),
                new SupperLight(getActivity(), ROOM, "L1", "男孩房门口筒灯"),
                new SupperLight(getActivity(), ROOM, "L2", "男孩房射灯"),
                new SupperLight(getActivity(), ROOM, "L3", "男孩房主灯"),
                new SupperLight(getActivity(), ROOM, "L4", "男孩房灯带"),
                new CurtainsAction(getActivity(), ROOM, "Curt1","布帘"),
                new ScreenWindowAction(getActivity(), ROOM, "Gau1","纱帘"),

        };
    }
}
