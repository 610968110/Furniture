package com.furniture.ui.fragment.shanghainext40;

import android.os.Bundle;
import android.support.annotation.DrawableRes;

import com.furniture.bean.ActionBean;
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

public class JiNanXuanguan40Fragment extends DinnerRoomFragment {

    public static JiNanXuanguan40Fragment newInstance(@DrawableRes int bg) {
        Bundle args = new Bundle();
        args.putInt("bg", bg);
        JiNanXuanguan40Fragment fragment = new JiNanXuanguan40Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public ActionBean[] initAllAction() {
        return new ActionBean[]{
                //空调
//                new AirConditionerAction(getActivity(), ROOM, "AHU1"),
                //新风
//                new AirAction(getActivity(), ROOM, "FAU1"),
                new SupperLight(getActivity(), ROOM, "L1", "筒灯"),
                new SupperLight(getActivity(), ROOM, "L2", "灯带1"),
                new SupperLight(getActivity(), ROOM, "L3", "灯带2"),
                new SupperLight(getActivity(), ROOM, "L4", "灯带3"),
                // 窗帘
//                new CurtainsAction(getActivity(), ROOM, "Curt1"),
//                new ScreenWindowAction(getActivity(), ROOM, "Gau1"),
        };
    }
}
