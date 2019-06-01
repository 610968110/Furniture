package com.furniture.ui.activity;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.furniture.R;
import com.furniture.base.BaseActivity;
import com.furniture.databinding.ActivityUserInfoBinding;
import com.furniture.databinding.ActivityUserManageBinding;
import com.furniture.injector.components.AppComponent;
import com.furniture.injector.components.DaggerActivityComponent;
import com.furniture.injector.modules.ActivityModule;

import lbx.xtoollib.XIntent;

/**
 * @author lbx
 */
public class UserManageActivity extends BaseActivity {

    private ActivityUserManageBinding mBinding;

    public static XIntent getIntent(Context context) {
        return new XIntent(context, UserManageActivity.class);
    }

    @Override
    public void bindComponent(AppComponent appComponent) {
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(new ActivityModule(this))
                .build();
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_user_manage;
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {
        mBinding = (ActivityUserManageBinding) binding;
    }

    @Override
    public void initView(View view) {
        mBinding.tbUserManage.bindActivity(this);
        mBinding.tbUserManage.setTitle("用户管理");
    }

    @Override
    public void initData() {

    }
}
