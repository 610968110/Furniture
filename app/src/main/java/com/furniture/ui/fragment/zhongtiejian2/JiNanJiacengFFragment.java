package com.furniture.ui.fragment.zhongtiejian2;

import android.os.Bundle;
import android.support.annotation.DrawableRes;

import com.furniture.bean.ActionBean;
import com.furniture.bean.action2.CurtainsAction;
import com.furniture.bean.action2.ScreenWindowAction;
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

public class JiNanJiacengFFragment extends MasterRoomFragment {

    public static final String ROOM = "R6";

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
                new SupperLight(getActivity(), ROOM, "L1", "女孩房门口筒灯"),
                new SupperLight(getActivity(), ROOM, "L2", "女孩房主灯"),
                new SupperLight(getActivity(), ROOM, "L3", "女孩房筒灯"),
                new SupperLight(getActivity(), ROOM, "L4", "女孩房灯带"),
                new CurtainsAction(getActivity(), ROOM, "Curt1","布帘"),
                new ScreenWindowAction(getActivity(), ROOM, "Gau1","纱帘"),
        };
    }
}
