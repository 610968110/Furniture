package com.furniture.ui.fragment.roomjinan2;

import android.os.Bundle;
import android.support.annotation.DrawableRes;

import com.furniture.ui.fragment.room.HomeFragment;

/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: liboxin
 * Email: liboxin@worktile.com
 * Date: 2019/6/11
 * Time: 18:43
 * Desc:
 */

public class JiNanHomeFragment extends HomeFragment {

    public static JiNanHomeFragment newInstance() {
        return JiNanHomeFragment.newInstance(-1);
    }

    public static JiNanHomeFragment newInstance(@DrawableRes int bg) {
        Bundle args = new Bundle();
        args.putInt("bg", bg);
        JiNanHomeFragment fragment = new JiNanHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
