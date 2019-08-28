package com.furniture.ui.fragment.main;

import android.databinding.ViewDataBinding;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.furniture.Config;
import com.furniture.R;
import com.furniture.adapter.MainHomeFragmentAdapter;
import com.furniture.base.BaseFragment;
import com.furniture.base.BaseRoomFragment;
import com.furniture.databinding.FragmentMainHomeBinding;
import com.furniture.impl.IMainPager;
import com.furniture.injector.components.ActivityComponent;
import com.furniture.injector.components.DaggerFragmentComponent;
import com.furniture.injector.modules.FragmentModule;
import com.furniture.type.HomePager;
import com.furniture.type.HomePager2;
import com.furniture.type.HomePager3Simple;
import com.furniture.type.HomePagerJiNan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import lbx.xtoollib.view.tablayout.XTabLayout;

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
 * 主页三个tab1
 */

public class MainHomeFragment extends BaseFragment {

    private List<IMainPager> mList;
    private MainHomeFragmentAdapter mAdapter;
    @BindView(R.id.tl_main_home)
    XTabLayout mTabLayout;
    private FragmentMainHomeBinding mBinding;
    @BindView(R.id.vp_main_home)
    ViewPager mViewPager;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_main_home;
    }

    @Override
    public void bindComponent(ActivityComponent activityComponent) {
        mFragmentComponent = DaggerFragmentComponent.builder()
                .activityComponent(activityComponent)
                .fragmentModule(new FragmentModule())
                .build();
        mFragmentComponent.inject(this);
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {
        mBinding = (FragmentMainHomeBinding) binding;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {
        mList = new ArrayList<>();
        //data
        IMainPager[] values = HomePager.values();
        if (Config.APP_TYPE == Config.TYPE_NORMAL) {
            values = HomePager.values();
        } else if (Config.APP_TYPE == Config.TYPE_ONE) {
            values = HomePager2.values();
        } else if (Config.APP_TYPE == Config.TYPE_DEMO_SIMPLE) {
            values = HomePager3Simple.values();
            mTabLayout.setVisibility(View.GONE);
        } else if (Config.APP_TYPE == Config.TYPE_DEMO_JINAN||
                Config.APP_TYPE == Config.TYPE_DEMO_SHANGHAI) {
            values = HomePagerJiNan.values();
        }
        Collections.addAll(mList, values);
        //view
        mAdapter = new MainHomeFragmentAdapter(getActivity().getSupportFragmentManager(), mList);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(values.length);
    }


    @Override
    public void initListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mList != null) {
                    BaseFragment baseFragment = mList.get(position).getFragment();
                    if (baseFragment instanceof BaseRoomFragment) {
                        ((BaseRoomFragment) baseFragment).onPageSelect();
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
