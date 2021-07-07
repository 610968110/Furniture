package com.furniture.ui.fragment.shanghainextF;

import android.os.Bundle;
import android.support.annotation.DrawableRes;

import com.furniture.bean.ActionBean;
import com.furniture.bean.action2.AirAction;
import com.furniture.bean.action2.AirConditionerAction;
import com.furniture.bean.action2.CurtainsAction;
import com.furniture.bean.action2.HeatingAction;
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
                // 地暖
                new HeatingAction(getActivity(), ROOM, "FH1"),
                new SupperLight(getActivity(), ROOM, "L1", "主卧射灯"),
                new SupperLight(getActivity(), ROOM, "L2", "主卧筒灯"),
                new SupperLight(getActivity(), ROOM, "L3", "主卧灯带"),
                new SupperLight(getActivity(), ROOM, "L4", "衣柜灯带"),
                new SupperLight(getActivity(), ROOM, "L5", "玄关筒灯"),
                new SupperLight(getActivity(), ROOM, "L6", "洗手池筒灯"),
                new SupperLight(getActivity(), ROOM, "L7", "洗手间柜体灯带"),
                new SupperLight(getActivity(), ROOM, "L8", "淋浴间筒灯"),
                new SupperLight(getActivity(), ROOM, "L9", "浴缸筒灯"),
                new SupperLight(getActivity(), ROOM, "L10", "衣帽间筒灯"),
                new CurtainsAction(getActivity(), ROOM, "Curt1","布帘"),
                new ScreenWindowAction(getActivity(), ROOM, "Gau1","纱帘"),

        };
    }
}
