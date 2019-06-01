package com.furniture.type;

import com.furniture.R;
import com.furniture.base.BaseFragment;
import com.furniture.impl.IMainPager;
import com.furniture.ui.fragment.room.BookRoomFragment;
import com.furniture.ui.fragment.room.KeTingRoomFragment;
import com.furniture.ui.fragment.room.HomeFragment;
import com.furniture.ui.fragment.room.DinnerRoomFragment;
import com.furniture.ui.fragment.room.MasterRoomFragment;

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

public enum HomePager implements IMainPager {

    HOME_PAGE("首页", HomeFragment.newInstance()),
    DINING_ROOM("客厅", KeTingRoomFragment.newInstance(R.drawable.bg_kecanting)),
    MASTER_ROOM("餐厅", DinnerRoomFragment.newInstance(R.drawable.bg_zhuwo)),
    SECOND_ROOM("主卧", MasterRoomFragment.newInstance(R.drawable.bg_ciwo)),
    CHILD_ROOM("书房", BookRoomFragment.newInstance(R.drawable.bg_ertongfang));

    private String name;
    private BaseFragment fragment;

    HomePager(String name, BaseFragment fragment) {
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
