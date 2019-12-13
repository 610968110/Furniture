package com.furniture.ui.fragment.shanghainextF;

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

public class JiNanHomeFFragment extends HomeFragment {

    public static JiNanHomeFFragment newInstance() {
        return JiNanHomeFFragment.newInstance(-1);
    }

    public static JiNanHomeFFragment newInstance(@DrawableRes int bg) {
        Bundle args = new Bundle();
        args.putInt("bg", bg);
        JiNanHomeFFragment fragment = new JiNanHomeFFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
