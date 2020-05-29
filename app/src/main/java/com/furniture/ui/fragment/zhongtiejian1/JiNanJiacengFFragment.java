package com.furniture.ui.fragment.zhongtiejian1;

import android.os.Bundle;
import android.support.annotation.DrawableRes;

import com.furniture.bean.ActionBean;
import com.furniture.bean.action2.HeatingAction;
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
                new HeatingAction(getActivity(), ROOM, "FH1"),
                new SupperLight(getActivity(), ROOM, "L1", "筒灯"),
                new SupperLight(getActivity(), ROOM, "L2", "灯带"),
                new SupperLight(getActivity(), ROOM, "L3", "排风"),
        };
    }
}
