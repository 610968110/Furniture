package com.furniture.ui.fragment.shanghainext40;

import android.os.Bundle;
import android.support.annotation.DrawableRes;

import com.furniture.bean.ActionBean;
import com.furniture.bean.action2.AirAction;
import com.furniture.bean.action2.AirConditionerAction;
import com.furniture.bean.action2.CurtainsAction;
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

public class JiNanXuanguanFragment extends DinnerRoomFragment {

    public static JiNanXuanguanFragment newInstance(@DrawableRes int bg) {
        Bundle args = new Bundle();
        args.putInt("bg", bg);
        JiNanXuanguanFragment fragment = new JiNanXuanguanFragment();
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
                new SupperLight(getActivity(), ROOM, "L1", "筒灯"),
                new SupperLight(getActivity(), ROOM, "L2", "灯带1"),
                new SupperLight(getActivity(), ROOM, "L3", "灯带2"),
                new SupperLight(getActivity(), ROOM, "L4", "灯带3"),
                // 窗帘
                new CurtainsAction(getActivity(), ROOM, "Curt1"),
        };
    }
}
