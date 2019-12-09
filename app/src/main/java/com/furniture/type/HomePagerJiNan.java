package com.furniture.type;

import com.furniture.R;
import com.furniture.base.BaseFragment;
import com.furniture.impl.IMainPager;
import com.furniture.ui.fragment.shanghainext80.JiNanB280Fragment;
import com.furniture.ui.fragment.shanghainext80.JiNanDinner80Fragment;
import com.furniture.ui.fragment.shanghainext80.JiNanHome80Fragment;
import com.furniture.ui.fragment.shanghainext80.JiNanJiaceng80Fragment;
import com.furniture.ui.fragment.shanghainext80.JiNanXuanguan80Fragment;

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

    // 这里是之前做的上海的完整版
//    HOME_PAGE("首页", JiNanHomeFragment.newInstance(R.drawable.jinan_bg_1)),
//    DINING_ROOM("餐客厅", JiNanDinnerFragment.newInstance(R.drawable.jinan_bg_2)),
//    MASTER_ROOM("玄关", JiNanXuanguanFragment.newInstance(R.drawable.jinan_bg_3)),
//    SECOND_ROOM("夹层", JiNanJiacengFragment.newInstance(R.drawable.jinan_bg_4)),
//    CHILD_ROOM("B2层", JiNanB2Fragment.newInstance(R.drawable.jinan_bg_5));

    // 这里是之前做的上海的完整版后又让换图片
//    HOME_PAGE("首页", JiNanHomeFragment.newInstance(R.drawable.jinan_bg_1)),
//    DINING_ROOM("餐客厅", JiNanDinnerFragment.newInstance(R.drawable.shanghai_bg_1)),
//    MASTER_ROOM("主卧", JiNanXuanguanFragment.newInstance(R.drawable.shanghai_bg_2)),
//    SECOND_ROOM("儿童房", JiNanJiacengFragment.newInstance(R.drawable.shanghai_bg_3)),
//    CHILD_ROOM("书房", JiNanB2Fragment.newInstance(R.drawable.shanghai_bg_4));

    // 2这里是之前做的上海的完整版后又让换图片和改名 这里的fragment包名后多了个2
//    HOME_PAGE("首页", JiNanHomeFragment.newInstance(R.drawable.jinan_bg_1)),
//    DINING_ROOM("餐客厅", JiNanDinnerFragment.newInstance(R.drawable.new_2)),
//    MASTER_ROOM("主卧", JiNanXuanguanFragment.newInstance(R.drawable.new_3)),
//    SECOND_ROOM("老人房", JiNanJiacengFragment.newInstance(R.drawable.new_4)),
//    CHILD_ROOM("儿童房", JiNanB2Fragment.newInstance(R.drawable.new_5));

    // 这里是上海完成后的制作的下一个版本，没说是哪的，包名 shanghainext40
//    HOME_PAGE("首页", JiNanHome40Fragment.newInstance(R.drawable.jinan_bg_1)),
//    DINING_ROOM("客餐厅", JiNanDinner40Fragment.newInstance(R.drawable.nanjing1)),
//    MASTER_ROOM("玄关", JiNanXuanguan40Fragment.newInstance(R.drawable.nanjing2)),
//    SECOND_ROOM("主卧", JiNanJiaceng40Fragment.newInstance(R.drawable.nanjing3)),
//    CHILD_ROOM("卫生间", JiNanB240Fragment.newInstance(R.drawable.nanjing4));

    // 这里是上海完成后的制作的下一个版本，没说是哪的，包名 shanghainext80
    HOME_PAGE("首页", JiNanHome80Fragment.newInstance(R.drawable.jinan_bg_1)),
    DINING_ROOM("客餐厅", JiNanDinner80Fragment.newInstance(R.drawable.nanjing11)),
    MASTER_ROOM("主卧", JiNanXuanguan80Fragment.newInstance(R.drawable.nanjing12)),
    SECOND_ROOM("次卧", JiNanJiaceng80Fragment.newInstance(R.drawable.nanjing13)),
    CHILD_ROOM("卫生间", JiNanB280Fragment.newInstance(R.drawable.nanjing14));

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
