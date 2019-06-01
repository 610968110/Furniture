package com.furniture.injector.components;


import com.furniture.injector.ForFragment;
import com.furniture.injector.modules.FragmentModule;
import com.furniture.ui.fragment.HomeTimeFragment;
import com.furniture.ui.fragment.main.CommunityFragment;
import com.furniture.ui.fragment.main.MainHomeFragment;
import com.furniture.ui.fragment.main.MineFragment;
import com.furniture.ui.fragment.room.BookRoomFragment;
import com.furniture.ui.fragment.room.KeTingRoomFragment;
import com.furniture.ui.fragment.room.HomeFragment;
import com.furniture.ui.fragment.room.DinnerRoomFragment;
import com.furniture.ui.fragment.room.MasterRoomFragment;
import com.furniture.ui.fragment.room2.FirstFloorFragment;
import com.furniture.ui.fragment.room2.FourthFloorFragment;
import com.furniture.ui.fragment.room2.Home2Fragment;
import com.furniture.ui.fragment.room2.SecondFloorFragment;
import com.furniture.ui.fragment.room2.ThirdFloorFragment;

import dagger.Component;

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
 * @date 2015/9/3.
 */
@ForFragment
@Component(dependencies = {ActivityComponent.class}, modules = {FragmentModule.class})
public interface FragmentComponent {

    void inject(HomeFragment fragment);

    void inject(MineFragment fragment);

    void inject(HomeTimeFragment fragment);

    void inject(MasterRoomFragment fragment);

    void inject(DinnerRoomFragment fragment);

    void inject(KeTingRoomFragment fragment);

    void inject(BookRoomFragment fragment);

    void inject(MainHomeFragment fragment);

    void inject(CommunityFragment fragment);

    void inject(FirstFloorFragment fragment);

    void inject(SecondFloorFragment fragment);

    void inject(ThirdFloorFragment fragment);

    void inject(FourthFloorFragment fragment);

    void inject(Home2Fragment fragment);
}
