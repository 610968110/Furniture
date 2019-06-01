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

public enum Room2ConfigPager implements IRoomConfig {

    DINING_CONFIG("一层展示区", DiningRoomConfigFragment.newInstance()),
    MASTER_CONFIG("二层展示区", MasterRoomConfigFragment.newInstance()),
    SECOND_CONFIG("三层会所", SecondRoomConfigFragment.newInstance()),
    CHILD_CONFIG("B1员工休闲餐饮区", ChildRoomConfigFragment.newInstance());

    private String name;
    private BaseRoomConfigFragment fragment;

    Room2ConfigPager(String name, BaseRoomConfigFragment fragment) {
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
