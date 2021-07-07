package com.furniture.ui.fragment.porth;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.View;

import com.furniture.bean.ActionBean;
import com.furniture.bean.action1.SleepAction;
import com.furniture.bean.action1.WeekUpAction;
import com.furniture.bean.jinanbean.SupperLight;
import com.furniture.ui.fragment.room.MasterRoomFragment;

import static com.furniture.constant.Device.SCE;

/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: liboxin
 * Email: liboxin@worktile.com
 * Date: 2019/6/11
 * Time: 18:46
 * Desc:
 */

public class JiNanJiacengFFragment extends MasterRoomFragment {

    public static final String ROOM = "R3";

    public static JiNanJiacengFFragment newInstance(@DrawableRes int bg) {
        Bundle args = new Bundle();
        args.putInt("bg", bg);
        JiNanJiacengFFragment fragment = new JiNanJiacengFFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public ActionBean[] initAllAction() {
        return new ActionBean[]{
//                //空调
//                new AirConditionerAction(getActivity(), ROOM, "AHU1"),
                new SupperLight(getActivity(), ROOM, "L1", "卧室主灯"),
//                new CurtainsAction(getActivity(), ROOM, "Curt1","布帘"),
//                new ScreenWindowAction(getActivity(), ROOM, "Gau1","纱帘"),
        };
    }

    @Override
    public ActionBean[] initCenterAction() {
        return new ActionBean[]{
                // 起床
                new WeekUpAction(getActivity(), ROOM, SCE),
                // 睡眠
                new SleepAction(getActivity(), ROOM, SCE)
        };
    }

    @Override
    public ActionBean[] initTopAction() {
        return new ActionBean[]{};
    }

    @Override
    protected int showEnvironmentView(){
        return View.INVISIBLE;
    }
}
