package com.furniture.ui.fragment.shanghainextF;

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

public class JiNanDinnerFFragment extends KeTingRoomFragment {

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
                //空调
                new AirConditionerAction(getActivity(), ROOM, "AHU1"),
                //新风
                new AirAction(getActivity(), ROOM, "FAU1"),
                new SupperLight(getActivity(), ROOM, "L1", "玄关射灯"),
                new SupperLight(getActivity(), ROOM, "L2", "玄关鞋柜灯带"),
                new SupperLight(getActivity(), ROOM, "L3", "沙发区射灯"),
                new SupperLight(getActivity(), ROOM, "L4", "主灯"),
                new SupperLight(getActivity(), ROOM, "L5", "餐厅灯带"),
                new SupperLight(getActivity(), ROOM, "L6", "电视柜灯带"),
                new SupperLight(getActivity(), ROOM, "L7", "餐厅花灯"),
                new SupperLight(getActivity(), ROOM, "L8", "餐厅射灯1"),
                new SupperLight(getActivity(), ROOM, "L9", "餐厅射灯2"),
                new SupperLight(getActivity(), ROOM, "L10", "吧台射灯"),
                new SupperLight(getActivity(), ROOM, "L11", "壁炉灯带"),
                new SupperLight(getActivity(), ROOM, "L12", "厨房筒灯"),
                new SupperLight(getActivity(), ROOM, "L13", "会客厅主灯带"),
                new SupperLight(getActivity(), ROOM, "L14", "会客厅辅灯带"),
                new SupperLight(getActivity(), ROOM, "L15", "会客厅主灯"),
                new SupperLight(getActivity(), ROOM, "L16", "会客厅射灯"),
                new CurtainsAction(getActivity(), ROOM, "Curt1","布帘1"),
                new ScreenWindowAction(getActivity(), ROOM, "Gau1","纱帘1"),
                new CurtainsAction(getActivity(), ROOM, "Curt2","布帘2"),
                new ScreenWindowAction(getActivity(), ROOM, "Gau2","纱帘2"),
                new CurtainsAction(getActivity(), ROOM, "Curt3","布帘3"),
                new ScreenWindowAction(getActivity(), ROOM, "Gau3","纱帘3"),
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
