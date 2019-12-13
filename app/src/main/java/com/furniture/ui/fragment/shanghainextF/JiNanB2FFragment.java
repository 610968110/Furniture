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
import com.furniture.ui.fragment.room.BookRoomFragment;

/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: liboxin
 * Email: liboxin@worktile.com
 * Date: 2019/6/11
 * Time: 18:47
 * Desc:
 */

public class JiNanB2FFragment extends BookRoomFragment {

    public static JiNanB2FFragment newInstance(@DrawableRes int bg) {
        Bundle args = new Bundle();
        args.putInt("bg", bg);
        JiNanB2FFragment fragment = new JiNanB2FFragment();
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
                new SupperLight(getActivity(), ROOM, "L1", "电视射灯"),
                new SupperLight(getActivity(), ROOM, "L2", "玄关灯"),
                new SupperLight(getActivity(), ROOM, "L3", "床头射灯"),
                new SupperLight(getActivity(), ROOM, "L4", "主灯带"),
                new SupperLight(getActivity(), ROOM, "L5", "辅灯带"),
                new CurtainsAction(getActivity(), ROOM, "Curt1","布帘"),
                new ScreenWindowAction(getActivity(), ROOM, "Gau1","纱帘"),
                // 地暖
                new HeatingAction(getActivity(), ROOM, "FH1"),
        };
    }
}
