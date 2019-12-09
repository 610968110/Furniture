package com.furniture.ui.fragment.shanghainext40;

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

public class JiNanHome40Fragment extends HomeFragment {

    public static JiNanHome40Fragment newInstance() {
        return JiNanHome40Fragment.newInstance(-1);
    }

    public static JiNanHome40Fragment newInstance(@DrawableRes int bg) {
        Bundle args = new Bundle();
        args.putInt("bg", bg);
        JiNanHome40Fragment fragment = new JiNanHome40Fragment();
        fragment.setArguments(args);
        return fragment;
    }
}
