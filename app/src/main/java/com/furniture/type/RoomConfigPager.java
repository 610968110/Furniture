package com.furniture.type;

import com.furniture.base.BaseRoomConfigFragment;
import com.furniture.ui.fragment.config.ChildRoomConfigFragment;
import com.furniture.ui.fragment.config.DiningRoomConfigFragment;
import com.furniture.ui.fragment.config.MasterRoomConfigFragment;
import com.furniture.ui.fragment.config.SecondRoomConfigFragment;

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

public enum RoomConfigPager implements IRoomConfig {

    DINING_CONFIG("客餐厅", DiningRoomConfigFragment.newInstance()),
    MASTER_CONFIG("主卧", MasterRoomConfigFragment.newInstance()),
    SECOND_CONFIG("次卧", SecondRoomConfigFragment.newInstance()),
    CHILD_CONFIG("儿童房", ChildRoomConfigFragment.newInstance());

    private String name;
    private BaseRoomConfigFragment fragment;

    RoomConfigPager(String name, BaseRoomConfigFragment fragment) {
        this.name = name;
        this.fragment = fragment;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BaseRoomConfigFragment getFragment() {
        return fragment;
    }
}
