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

//    DINING_CONFIG("餐客厅", DiningRoomConfigFragment.newInstance()),
//    MASTER_CONFIG("玄关", MasterRoomConfigFragment.newInstance()),
//    SECOND_CONFIG("夹层", SecondRoomConfigFragment.newInstance()),
//    CHILD_CONFIG("B2层", ChildRoomConfigFragment.newInstance());

    // 这里是上海完成后的制作的下一个版本，没说是哪的，包名 shanghainext40
//    DINING_CONFIG("客餐厅", DiningRoomConfigFragment.newInstance()),
//    MASTER_CONFIG("玄关", MasterRoomConfigFragment.newInstance()),
//    SECOND_CONFIG("主卧", SecondRoomConfigFragment.newInstance()),
//    CHILD_CONFIG("卫生间", ChildRoomConfigFragment.newInstance());

    // 这里是上海完成后的制作的下一个版本，没说是哪的，包名 shanghainext80
    DINING_CONFIG("客餐厅", DiningRoomConfigFragment.newInstance()),
    MASTER_CONFIG("主卧", MasterRoomConfigFragment.newInstance()),
    SECOND_CONFIG("次卧", SecondRoomConfigFragment.newInstance()),
    CHILD_CONFIG("卫生间", ChildRoomConfigFragment.newInstance());

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
