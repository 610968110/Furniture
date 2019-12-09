package com.furniture.ui.fragment.shanghainext80;

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

public class JiNanHome80Fragment extends HomeFragment {

    public static JiNanHome80Fragment newInstance() {
        return JiNanHome80Fragment.newInstance(-1);
    }

    public static JiNanHome80Fragment newInstance(@DrawableRes int bg) {
        Bundle args = new Bundle();
        args.putInt("bg", bg);
        JiNanHome80Fragment fragment = new JiNanHome80Fragment();
        fragment.setArguments(args);
        return fragment;
    }
}
