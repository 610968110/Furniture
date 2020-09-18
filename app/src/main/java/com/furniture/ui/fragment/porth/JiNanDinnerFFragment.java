package com.furniture.ui.fragment.porth;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.View;

import com.furniture.bean.ActionBean;
import com.furniture.bean.action1.GoHomeAction;
import com.furniture.bean.action1.LeisureAction;
import com.furniture.bean.action1.MeetingGuestsAction;
import com.furniture.bean.action1.OutHomeAction;
import com.furniture.bean.jinanbean.SupperLight;
import com.furniture.ui.fragment.room.KeTingRoomFragment;

import static com.furniture.constant.Device.SCE;

/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: liboxin
 * Email: liboxin@worktile.com
 * Date: 2019/6/11
 * Time: 18:44
 * Desc:
 */

public class JiNanDinnerFFragment extends KeTingRoomFragment {

    public static final String ROOM = "R1";

    public static JiNanDinnerFFragment newInstance(@DrawableRes int bg) {
        Bundle args = new Bundle();
        args.putInt("bg", bg);
        JiNanDinnerFFragment fragment = new JiNanDinnerFFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public ActionBean[] initAllAction() {
        return new ActionBean[]{
//                //空调
//                new AirConditionerAction(getActivity(), ROOM, "AHU1"),
                //                //新风
//                new AirAction(getActivity(), ROOM, "FAU1"),
                new SupperLight(getActivity(), ROOM, "L1", "玄关筒灯"),
                new SupperLight(getActivity(), ROOM, "L2", "走廊筒灯"),
                new SupperLight(getActivity(), ROOM, "L3", "餐厅主灯"),
                new SupperLight(getActivity(), ROOM, "L4", "餐厅灯带"),
                new SupperLight(getActivity(), ROOM, "L5", "客厅主灯"),
                new SupperLight(getActivity(), ROOM, "L6", "书房灯"),
                new SupperLight(getActivity(), ROOM, "L7", "灯带"),
        };
    }

    @Override
    public ActionBean[] initCenterAction() {
        return new ActionBean[]{
                // 回家
                new GoHomeAction(getActivity(), ROOM, SCE),
                // 离家
                new OutHomeAction(getActivity(), ROOM, SCE),
                // 休闲
                new LeisureAction(getActivity(), ROOM, SCE),
                // 会客
                new MeetingGuestsAction(getActivity(), ROOM, SCE)
        };
    }

    @Override
    public ActionBean[] initTopAction() {
        return new ActionBean[]{};
    }

    @Override
    protected int showEnvironmentView() {
        return View.INVISIBLE;
    }
}
