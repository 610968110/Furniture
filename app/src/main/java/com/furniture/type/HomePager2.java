package com.furniture.type;

import com.furniture.R;
import com.furniture.base.BaseFragment;
import com.furniture.impl.IMainPager;
import com.furniture.ui.fragment.room.BookRoomFragment;
import com.furniture.ui.fragment.room.DinnerRoomFragment;
import com.furniture.ui.fragment.room.KeTingRoomFragment;
import com.furniture.ui.fragment.room.MasterRoomFragment;
import com.furniture.ui.fragment.room2.Home2Fragment;

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

public enum HomePager2 implements IMainPager {

//    HOME_PAGE("中粮健康生态园", Home2Fragment.newInstance()),
//    DINING_ROOM("一层展示区", FirstFloorFragment.newInstance()),
//    MASTER_ROOM("二层展示区", SecondFloorFragment.newInstance()),
//    SECOND_ROOM("三层会所", ThirdFloorFragment.newInstance()),
//    CHILD_ROOM("B1员工休闲餐饮区", FourthFloorFragment.newInstance());

    HOME_PAGE("中粮健康生态园", Home2Fragment.newInstance()),
    DINING_ROOM("一层展示区", KeTingRoomFragment.newInstance(R.drawable.first_room_bg)),
    MASTER_ROOM("二层展示区", DinnerRoomFragment.newInstance(R.drawable.second_room_bg)),
    SECOND_ROOM("三层会所", MasterRoomFragment.newInstance(R.drawable.third_room_bg)),
    CHILD_ROOM("B1员工休闲餐饮区", BookRoomFragment.newInstance(R.drawable.fourth_room_bg));

    private String name;
    private BaseFragment fragment;

    HomePager2(String name, BaseFragment fragment) {
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
