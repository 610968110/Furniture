package com.furniture.ui.fragment.main;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.View;

import com.furniture.R;
import com.furniture.base.BaseFragment;
import com.furniture.bean.UserInfo;
import com.furniture.databinding.FragmentMineBinding;
import com.furniture.injector.components.ActivityComponent;
import com.furniture.injector.components.DaggerFragmentComponent;
import com.furniture.injector.modules.FragmentModule;
import com.furniture.ui.activity.RoomConfigActivity;
import com.furniture.ui.activity.FamilyManageActivity;
import com.furniture.ui.activity.SettingActivity;
import com.furniture.ui.activity.UserInfoActivity;
import com.furniture.ui.activity.UserManageActivity;

import javax.inject.Inject;

import lbx.xtoollib.XTools;
import lbx.xtoollib.phone.xLogUtil;

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
 * 主页三个tab_3 我的
 */

public class MineFragment extends BaseFragment {

    @Inject
    UserInfo mUserInfo;
    private FragmentMineBinding mBinding;

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_mine;
    }

    @Override
    public void bindComponent(ActivityComponent activityComponent) {
        mFragmentComponent = DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule())
                .activityComponent(activityComponent)
                .build();
        mFragmentComponent.inject(this);
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {
        mBinding = (FragmentMineBinding) binding;
        mBinding.setFragment(this);
        mBinding.setUser(mUserInfo);
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        //刷新头像
        mBinding.invalidateAll();
    }

    public void setting() {
        xLogUtil.e(this, "setting");
        SettingActivity.getIntent(getContext()).start();
    }

    public void userManage() {
        xLogUtil.e(this, "userManage");
        UserManageActivity.getIntent(getContext()).start();
    }

    public void sceneManage() {
        xLogUtil.e(this, "sceneManage");
        RoomConfigActivity.getIntent(getContext()).start();
    }

    public void familyManage() {
        xLogUtil.e(this, "familyManage");
        if (mUserInfo.isManage()) {
            FamilyManageActivity.getIntent(getContext()).start();
        } else {
            XTools.UiUtil().showToast("您没有权限");
        }
    }

    public void info() {
        xLogUtil.e(this, "info");
        UserInfoActivity.getIntent(getContext()).start();
    }
}
