package com.furniture.ui.fragment.roomjinan;

import android.os.Bundle;
import android.support.annotation.DrawableRes;

import com.furniture.Config;
import com.furniture.bean.ActionBean;
import com.furniture.bean.action1.EatAction;
import com.furniture.bean.action1.IntelligenceAction;
import com.furniture.bean.action1.MeetingGuestsAction;
import com.furniture.bean.action1.MovieAction;
import com.furniture.bean.action1.ReadAction;
import com.furniture.bean.action2.AirAction;
import com.furniture.bean.action2.AirConditionerAction;
import com.furniture.bean.jinanbean.KeTingMainLightAction;
import com.furniture.bean.jinanbean.SupperLight;
import com.furniture.bean.jinanbean.TVWallLightBeltAction;
import com.furniture.bean.jinanbean.TopLightAction;
import com.furniture.bean.jinanbean.TopLightBeltAction;
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

public class JiNanDinnerFragment extends KeTingRoomFragment {

    public static JiNanDinnerFragment newInstance(@DrawableRes int bg) {
        Bundle args = new Bundle();
        args.putInt("bg", bg);
        JiNanDinnerFragment fragment = new JiNanDinnerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public ActionBean[] initAllAction() {
        //空调
        ActionBean bean1 = new AirConditionerAction(getActivity(), ROOM, "AHU1");
        //新风
        ActionBean bean2 = new AirAction(getActivity(), ROOM, "FAU1");
        //客厅主灯
        ActionBean bean3 = new KeTingMainLightAction(getActivity(), ROOM, "L1");
        //天花筒灯
        ActionBean bean4 = new TopLightAction(getActivity(), ROOM, "L2");
        //天花灯带
        ActionBean bean5 = new TopLightBeltAction(getActivity(), ROOM, "L3");
        //电视墙灯带
        ActionBean bean6 = new TVWallLightBeltAction(getActivity(), ROOM, "L4");
        ActionBean bean7 = new SupperLight(getActivity(), ROOM, "L5", "窗帘灯带");
        ActionBean bean8 = new SupperLight(getActivity(), ROOM, "L6", "壁柜灯");
        ActionBean bean9 = new SupperLight(getActivity(), ROOM, "L7", "餐厅吊灯");
        ActionBean bean10 = new SupperLight(getActivity(), ROOM, "L8", "餐厅筒灯");
        ActionBean bean11 = new SupperLight(getActivity(), ROOM, "L9", "餐厅灯带");
        return new ActionBean[]{bean1, bean2, bean3, bean4, bean5, bean6, bean7,
                bean8, bean9, bean10, bean11};
    }

    @Override
    public ActionBean[] initCenterAction() {
        //智能
        ActionBean bean1;
        //会客
        ActionBean bean2;
        if (Config.APP_TYPE == Config.TYPE_DEMO_SHANGHAI) {
            bean1 = new IntelligenceAction(getActivity(), "R", "Home");
            bean2 = new MeetingGuestsAction(getActivity(), "R", "Home");
        } else {
            bean1 = new IntelligenceAction(getActivity(), ROOM, SCE);
            bean2 = new MeetingGuestsAction(getActivity(), ROOM, SCE);
        }
        //用餐
        ActionBean bean3 = new EatAction(getActivity(), ROOM, SCE);
        //阅读
        ActionBean bean4 = new ReadAction(getActivity(), ROOM, SCE);
        //观影
        ActionBean bean5 = new MovieAction(getActivity(), ROOM, SCE);
        return new ActionBean[]{bean1, bean2, bean3, bean4, bean5};
    }
}
