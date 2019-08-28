package com.furniture.type;

import com.furniture.R;
import com.furniture.base.BaseFragment;
import com.furniture.impl.IMainPager;
import com.furniture.ui.fragment.roomjinan.JiNanB2Fragment;
import com.furniture.ui.fragment.roomjinan.JiNanDinnerFragment;
import com.furniture.ui.fragment.roomjinan.JiNanHomeFragment;
import com.furniture.ui.fragment.roomjinan.JiNanJiacengFragment;
import com.furniture.ui.fragment.roomjinan.JiNanXuanguanFragment;

/**
 * .  ┏┓　　　┏┓
 * .┏┛┻━━━┛┻┓
 * .┃　　　　　　　┃
 * .┃　　　━　　　┃
 * .┃　┳┛　┗┳　┃
 * .┃　　　　　　　┃
 * .┃　　　┻　　　┃
 * .┃　　　　　　　┃
 * .┗━┓　　　┏━┛
 * .    ┃　　　┃        神兽保佑
 * .    ┃　　　┃          代码无BUG!
 * .    ┃　　　┗━━━┓
 * .    ┃　　　　　　　┣┓
 * .    ┃　　　　　　　┏┛
 * .    ┗┓┓┏━┳┓┏┛
 * .      ┃┫┫　┃┫┫
 * .      ┗┻┛　┗┻┛
 *
 * @author lbx
 * @date 2018/8/24.
 */

public enum HomePagerJiNan implements IMainPager {

    HOME_PAGE("首页", JiNanHomeFragment.newInstance(R.drawable.jinan_bg_1)),
    DINING_ROOM("餐客厅", JiNanDinnerFragment.newInstance(R.drawable.jinan_bg_2)),
    MASTER_ROOM("玄关", JiNanXuanguanFragment.newInstance(R.drawable.jinan_bg_3)),
    SECOND_ROOM("夹层", JiNanJiacengFragment.newInstance(R.drawable.jinan_bg_4)),
    CHILD_ROOM("B2层", JiNanB2Fragment.newInstance(R.drawable.jinan_bg_5));

//    HOME_PAGE("首页", JiNanHomeFragment.newInstance(R.drawable.jinan_bg_1)),
//    DINING_ROOM("一层", JiNanDinnerFragment.newInstance(R.drawable.jinan_new_1)),
//    MASTER_ROOM("二层", JiNanXuanguanFragment.newInstance(R.drawable.jinan_new_2)),
//    SECOND_ROOM("B1层", JiNanJiacengFragment.newInstance(R.drawable.jinan_new_3)),
//    CHILD_ROOM("B2层", JiNanB2Fragment.newInstance(R.drawable.jinan_new_4));

    private String name;
    private BaseFragment fragment;

    HomePagerJiNan(String name, BaseFragment fragment) {
        this.name = name;
        this.fragment = fragment;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BaseFragment getFragment() {
        return fragment;
    }
}
