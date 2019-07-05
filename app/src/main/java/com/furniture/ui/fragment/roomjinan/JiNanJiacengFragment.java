package com.furniture.ui.fragment.roomjinan;

import android.os.Bundle;
import android.support.annotation.DrawableRes;

import com.furniture.bean.ActionBean;
import com.furniture.bean.action2.AirAction;
import com.furniture.bean.action2.AirConditionerAction;
import com.furniture.bean.jinanbean.SupperLight;
import com.furniture.ui.fragment.room.MasterRoomFragment;

/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: liboxin
 * Email: liboxin@worktile.com
 * Date: 2019/6/11
 * Time: 18:46
 * Desc:
 */

public class JiNanJiacengFragment extends MasterRoomFragment {

    public static JiNanJiacengFragment newInstance(@DrawableRes int bg) {
        Bundle args = new Bundle();
        args.putInt("bg", bg);
        JiNanJiacengFragment fragment = new JiNanJiacengFragment();
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
                new SupperLight(getActivity(), ROOM, "L1", "走道筒灯"),
                new SupperLight(getActivity(), ROOM, "L2", "天花灯"),
                new SupperLight(getActivity(), ROOM, "L3", "门口筒灯"),
//                new SupperLight(getActivity(), ROOM, "L4", "楼梯灯"),
        };
    }
}
