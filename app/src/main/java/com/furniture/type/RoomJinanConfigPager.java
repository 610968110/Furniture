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

public enum RoomJinanConfigPager implements IRoomConfig {

    DINING_CONFIG("餐客厅", DiningRoomConfigFragment.newInstance()),
    MASTER_CONFIG("玄关", MasterRoomConfigFragment.newInstance()),
    SECOND_CONFIG("夹层", SecondRoomConfigFragment.newInstance()),
    CHILD_CONFIG("B2层", ChildRoomConfigFragment.newInstance());

    private String name;
    private BaseRoomConfigFragment fragment;

    RoomJinanConfigPager(String name, BaseRoomConfigFragment fragment) {
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
