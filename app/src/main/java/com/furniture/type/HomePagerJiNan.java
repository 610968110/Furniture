package com.furniture.type;

import com.furniture.R;
import com.furniture.base.BaseFragment;
import com.furniture.impl.IMainPager;
import com.furniture.ui.fragment.jinanb6b.JiNanB2FFragment;
import com.furniture.ui.fragment.jinanb6b.JiNanDinnerFFragment;
import com.furniture.ui.fragment.jinanb6b.JiNanHomeFFragment;
import com.furniture.ui.fragment.jinanb6b.JiNanJiacengFFragment;
import com.furniture.ui.fragment.jinanb6b.JiNanXuanguanFFragment;

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
//    HOME_PAGE("首页", JiNanHome80Fragment.newInstance(R.drawable.jinan_bg_1)),
//    DINING_ROOM("客餐厅", JiNanDinner80Fragment.newInstance(R.drawable.nanjing11)),
//    MASTER_ROOM("主卧", JiNanXuanguan80Fragment.newInstance(R.drawable.nanjing12)),
//    SECOND_ROOM("次卧", JiNanJiaceng80Fragment.newInstance(R.drawable.nanjing13)),
//    CHILD_ROOM("卫生间", JiNanB280Fragment.newInstance(R.drawable.nanjing14));

//    // 这里是上海完成后的制作的下一个版本，没说是哪的，包名 shanghainextF
//    HOME_PAGE("首页", JiNanHomeFFragment.newInstance(R.drawable.jinan_bg_1)),
//    DINING_ROOM("客餐厅", JiNanDinnerFFragment.newInstance(R.drawable.nanjing_f_1)),
//    MASTER_ROOM("主卧", JiNanXuanguanFFragment.newInstance(R.drawable.nanjing_f_2)),
//    SECOND_ROOM("次卧", JiNanJiacengFFragment.newInstance(R.drawable.nanjing_f_3)),
//    CHILD_ROOM("儿童房", JiNanB2FFragment.newInstance(R.drawable.nanjing_f_4));

//    HOME_PAGE("首页", JiNanHomeFragment.newInstance(R.drawable.jinan_bg_1)),
//    DINING_ROOM("一层", JiNanDinnerFragment.newInstance(R.drawable.jinan_new_1)),
//    MASTER_ROOM("二层", JiNanXuanguanFragment.newInstance(R.drawable.jinan_new_2)),
//    SECOND_ROOM("B1层", JiNanJiacengFragment.newInstance(R.drawable.jinan_new_3)),
//    CHILD_ROOM("B2层", JiNanB2Fragment.newInstance(R.drawable.jinan_new_4));

    // 中铁建1  包名 zhongtiejian1
//    HOME_PAGE("首页", JiNanHomeFFragment.newInstance(R.drawable.jinan_bg_1)),
//    DINING_ROOM("客餐厅", JiNanDinnerFFragment.newInstance(R.drawable.zhongtiejian_bg_2)),
//    MASTER_ROOM("父母房", JiNanXuanguanFFragment.newInstance(R.drawable.zhongtiejian_bg_3)),
//    SECOND_ROOM("卫生间", JiNanJiacengFFragment.newInstance(R.drawable.zhongtiejian_bg_4)),
//    CHILD_ROOM("楼梯", JiNanB2FFragment.newInstance(R.drawable.zhongtiejian_bg_5));

    // 中铁建2  包名 zhongtiejian2
//    HOME_PAGE("首页", JiNanHomeFFragment.newInstance(R.drawable.jinan_bg_1)),
//    DINING_ROOM("主卧", JiNanDinnerFFragment.newInstance(R.drawable.zhongtiejian_bg_2)),
//    MASTER_ROOM("男孩房", JiNanXuanguanFFragment.newInstance(R.drawable.zhongtiejian_bg_3)),
//    SECOND_ROOM("女孩房", JiNanJiacengFFragment.newInstance(R.drawable.zhongtiejian_bg_4)),
//    CHILD_ROOM("客卫", JiNanB2FFragment.newInstance(R.drawable.zhongtiejian_bg_5));

    // 竖屏版  包名 port
//    HOME_PAGE("首页", JiNanHomeFFragment.newInstance(R.drawable.port_bg1)),
//    DINING_ROOM("餐客厅", JiNanDinnerFFragment.newInstance(R.drawable.port_bg2)),
//    MASTER_ROOM("主卧", JiNanXuanguanFFragment.newInstance(R.drawable.port_bg3)),
//    SECOND_ROOM("次卧", JiNanJiacengFFragment.newInstance(R.drawable.port_bg4)),
//    CHILD_ROOM("儿童房", JiNanB2FFragment.newInstance(R.drawable.port_bg5));

    // 竖屏版同一批横屏版  包名 porth
//    HOME_PAGE("首页", JiNanHomeFFragment.newInstance(R.drawable.port_bg1)),
//    DINING_ROOM("餐客厅", JiNanDinnerFFragment.newInstance(R.drawable.port_h_bg2)),
//    MASTER_ROOM("主卧", JiNanXuanguanFFragment.newInstance(R.drawable.port_h_bg3)),
//    SECOND_ROOM("次卧", JiNanJiacengFFragment.newInstance(R.drawable.port_h_bg4)),
//    CHILD_ROOM("儿童房", JiNanB2FFragment.newInstance(R.drawable.port_h_bg5));

    // 济南b6 a户型  jinanb6a
//    HOME_PAGE("首页", JiNanHomeFFragment.newInstance(R.drawable.home_bg_v_h)),
//    DINING_ROOM("餐客厅", JiNanDinnerFFragment.newInstance(R.drawable.jinan_b6a_bg2)),
//    MASTER_ROOM("餐厅", JiNanXuanguanFFragment.newInstance(R.drawable.jinan_b6a_bg3)),
//    SECOND_ROOM("主卧", JiNanJiacengFFragment.newInstance(R.drawable.jinan_b6a_bg4)),
//    CHILD_ROOM("老人房", JiNanB2FFragment.newInstance(R.drawable.jinan_b6a_bg5));

    // 济南b6 b户型  jinanb6b
    HOME_PAGE("首页", JiNanHomeFFragment.newInstance(R.drawable.home_bg_v_h)),
    DINING_ROOM("客厅", JiNanDinnerFFragment.newInstance(R.drawable.jinan_b6a_bg2)),
    MASTER_ROOM("餐厅", JiNanXuanguanFFragment.newInstance(R.drawable.jinan_b6a_bg3)),
    SECOND_ROOM("主卧", JiNanJiacengFFragment.newInstance(R.drawable.jinan_b6a_bg4)),
    CHILD_ROOM("老人房", JiNanB2FFragment.newInstance(R.drawable.jinan_b6a_bg5));

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
