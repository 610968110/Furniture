package com.furniture.ui.activity;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.furniture.R;
import com.furniture.base.BaseActivity;
import com.furniture.databinding.ActivitySettingBinding;
import com.furniture.injector.components.AppComponent;
import com.furniture.injector.components.DaggerActivityComponent;
import com.furniture.injector.modules.ActivityModule;

import lbx.xtoollib.XIntent;
import lbx.xtoollib.phone.xLogUtil;
import lbx.xtoollib.window.ActivityUtil;

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
 * @date 2018/8/30
 */
public class SettingActivity extends BaseActivity {

    private ActivitySettingBinding mBinding;

    public static XIntent getIntent(Context context) {
        return new XIntent(context, SettingActivity.class);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_setting;
    }

    @Override
    public void bindComponent(AppComponent appComponent) {
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(appComponent)
                .build();
        mActivityComponent.inject(this);
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {
        mBinding = (ActivitySettingBinding) binding;
        mBinding.setContext(this);
    }

    @Override
    public void initView(View view) {
        mBinding.tbSetting.bindActivity(this);
        mBinding.tbSetting.setTitle("设置");
    }

    @Override
    public void initData() {

    }

    public void changePwd() {
        xLogUtil.e(this, "changePwd");
        ChangePasswordActivity.getIntent(this).start();
    }

    public void changeNickName() {
        xLogUtil.e(this, "changeNickName");
        ChangeNickNameActivity.getIntent(this).start();
    }

    public void logout() {
        xLogUtil.e(this, "logout");
        ActivityUtil.getInstance().finishAll();
        LoginActivity.getIntent(this).start();
    }
}
