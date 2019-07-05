package com.furniture.ui.fragment.roomjinan;

import android.os.Bundle;
import android.support.annotation.DrawableRes;

import com.furniture.bean.ActionBean;
import com.furniture.bean.action2.AirAction;
import com.furniture.bean.action2.AirConditionerAction;
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
                new SupperLight(getActivity(), ROOM, "L1", "健身房筒灯"),
                new SupperLight(getActivity(), ROOM, "L2", "健身房灯带"),
                new SupperLight(getActivity(), ROOM, "L3", "楼梯灯"),
                new SupperLight(getActivity(), ROOM, "L4", "吧台灯带"),
                new SupperLight(getActivity(), ROOM, "L5", "酒柜灯带"),
                new SupperLight(getActivity(), ROOM, "L6", "电梯灯带"),
                new SupperLight(getActivity(), ROOM, "L7", "过道筒灯"),
                new SupperLight(getActivity(), ROOM, "L8", "吧台主灯"),
                new SupperLight(getActivity(), ROOM, "L9", "门口筒灯"),
                new SupperLight(getActivity(), ROOM, "L10", "花盆灯"),
        };
    }
}
