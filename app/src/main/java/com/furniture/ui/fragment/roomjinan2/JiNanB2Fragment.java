package com.furniture.ui.fragment.roomjinan2;

import android.os.Bundle;
import android.support.annotation.DrawableRes;

import com.furniture.bean.ActionBean;
import com.furniture.bean.action2.AirAction;
import com.furniture.bean.action2.AirConditionerAction;
import com.furniture.bean.action2.CurtainsAction;
import com.furniture.bean.jinanbean.SupperLight;
import com.furniture.ui.fragment.room.BookRoomFragment;

/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: liboxin
 * Email: liboxin@worktile.com
 * Date: 2019/6/11
 * Time: 18:47
 * Desc:
 */

public class JiNanB2Fragment extends BookRoomFragment {

    public static JiNanB2Fragment newInstance(@DrawableRes int bg) {
        Bundle args = new Bundle();
        args.putInt("bg", bg);
        JiNanB2Fragment fragment = new JiNanB2Fragment();
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
                new SupperLight(getActivity(), ROOM, "L1", "主卧主灯"),
                new SupperLight(getActivity(), ROOM, "L2", "主卧灯带R"),
                new SupperLight(getActivity(), ROOM, "L3", "主卧灯带G"),
                new SupperLight(getActivity(), ROOM, "L4", "主卧灯带B"),
                // 窗帘
                new CurtainsAction(getActivity(), ROOM, "Curt1"),
        };
    }
}