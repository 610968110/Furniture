package com.furniture.type;

import com.furniture.R;
import com.furniture.base.BaseFragment;
import com.furniture.factory.FragmentFactory;
import com.furniture.ui.fragment.main.CommunityFragment;
import com.furniture.ui.fragment.main.MainHomeFragment;
import com.furniture.ui.fragment.main.MineFragment;

import lbx.xtoollib.bean.FragmentInfo;

import static com.furniture.Config.APP_TYPE;
import static com.furniture.Config.TYPE_DEMO_JINAN;
import static com.furniture.Config.TYPE_DEMO_SHANGHAI;
import static com.furniture.Config.TYPE_NORMAL;
import static com.furniture.Config.TYPE_ONE;

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

public enum MainPager {

    HOME(0, new FragmentInfo(R.drawable.bottombar_home, getFirstPagerName())) {
        @Override
        public <T extends BaseFragment> T createFragment(FragmentFactory factory) {
            return (T) factory.createFragment(MainHomeFragment.class);
        }
    },
    COMMUNITY(1, new FragmentInfo(R.drawable.bottombar_community, R.string.main_2)) {
        @Override
        public <T extends BaseFragment> T createFragment(FragmentFactory factory) {
            return (T) factory.createFragment(CommunityFragment.class);
        }
    },
    MINE(2, new FragmentInfo(R.drawable.bottombar_mine, R.string.main_3)) {
        @Override
        public <T extends BaseFragment> T createFragment(FragmentFactory factory) {
            return (T) factory.createFragment(MineFragment.class);
        }
    };

    public static int getFirstPagerName() {
        int name = -1;
        switch (APP_TYPE) {
            case TYPE_NORMAL:
                name = R.string.main_1;
                break;
            case TYPE_ONE:
                name = R.string.main_1_2;
                break;
            case TYPE_DEMO_JINAN:
            case TYPE_DEMO_SHANGHAI:
                name = R.string.main_1;
                break;
            default:
                break;
        }
        return name;
    }

    /**
     * 位置
     */
    private int mPos;
    /**
     * fragment 信息
     */
    private FragmentInfo mInfo;

    MainPager(int pos, FragmentInfo fragmentInfo) {
        this.mPos = pos;
        this.mInfo = fragmentInfo;
    }

    public int getPos() {
        return mPos;
    }

    public FragmentInfo getInfo() {
        return mInfo;
    }

    public abstract <T extends BaseFragment> T createFragment(FragmentFactory factory);
}
