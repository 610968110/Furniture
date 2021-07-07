package com.furniture.ui.fragment.zhongtiejian1;

import android.os.Bundle;
import android.support.annotation.DrawableRes;

import com.furniture.bean.ActionBean;
import com.furniture.bean.action2.CurtainsAction;
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

    public static final String ROOM = "R3";

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
                new SupperLight(getActivity(), ROOM, "L4", "楼梯筒灯"),
                new SupperLight(getActivity(), ROOM, "L5", "楼梯灯带"),
                new SupperLight(getActivity(), ROOM, "L6", "楼梯射灯"),
                new SupperLight(getActivity(), ROOM, "L7", "走廊灯"),
                new CurtainsAction(getActivity(), ROOM, "Curt1","布帘"),
                new ScreenWindowAction(getActivity(), ROOM, "Gau1","纱帘"),

        };
    }
}
