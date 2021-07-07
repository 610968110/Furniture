package com.furniture.ui.fragment.shanghainext80;

import android.os.Bundle;
import android.support.annotation.DrawableRes;

import com.furniture.bean.ActionBean;
import com.furniture.bean.action1.EatAction;
import com.furniture.bean.action1.IntelligenceAction;
import com.furniture.bean.action1.MeetingGuestsAction;
import com.furniture.bean.action1.MovieAction;
import com.furniture.bean.action1.ReadAction;
import com.furniture.bean.action2.AirAction;
import com.furniture.bean.action2.AirConditionerAction;
import com.furniture.bean.action2.CurtainsAction;
import com.furniture.bean.action2.HeatingAction;
import com.furniture.bean.action2.ScreenWindowAction;
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

public class JiNanDinner80Fragment extends KeTingRoomFragment {

    public static JiNanDinner80Fragment newInstance(@DrawableRes int bg) {
        Bundle args = new Bundle();
        args.putInt("bg", bg);
        JiNanDinner80Fragment fragment = new JiNanDinner80Fragment();
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
                new SupperLight(getActivity(), ROOM, "L1", "客厅筒灯"),
                new SupperLight(getActivity(), ROOM, "L2", "客厅射灯"),
                new SupperLight(getActivity(), ROOM, "L3", "客厅灯带"),
                new SupperLight(getActivity(), ROOM, "L4", "玄关筒灯"),
                new SupperLight(getActivity(), ROOM, "L5", "客厅射灯2"),
                new SupperLight(getActivity(), ROOM, "L6", "客厅壁灯"),
                new SupperLight(getActivity(), ROOM, "L7", "厨房灯带1"),
                new SupperLight(getActivity(), ROOM, "L8", "厨房灯带2"),
                // 窗帘
                new CurtainsAction(getActivity(), ROOM, "Curt1"),
                // 纱窗
                new ScreenWindowAction(getActivity(), ROOM, "Gau1"),
                // 地暖
                new HeatingAction(getActivity(), ROOM, "FH1")
        };
    }

    @Override
    public ActionBean[] initCenterAction() {
        //智能
        ActionBean bean1;
        //会客
        ActionBean bean2;
//        if (Config.APP_TYPE == Config.TYPE_DEMO_SHANGHAI) {
//            bean1 = new IntelligenceAction(getActivity(), "R", "Home");
//            bean2 = new MeetingGuestsAction(getActivity(), "R", "Home");
//        } else {
        bean1 = new IntelligenceAction(getActivity(), ROOM, SCE);
        bean2 = new MeetingGuestsAction(getActivity(), ROOM, SCE);
//        }
        //用餐
        ActionBean bean3 = new EatAction(getActivity(), ROOM, SCE);
        //阅读
        ActionBean bean4 = new ReadAction(getActivity(), ROOM, SCE);
        //观影
        ActionBean bean5 = new MovieAction(getActivity(), ROOM, SCE);
        return new ActionBean[]{bean1, bean2, bean3, bean4, bean5};
    }
}
