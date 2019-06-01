package com.furniture.ui.activity;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.furniture.Config;
import com.furniture.R;
import com.furniture.base.BaseActivity;
import com.furniture.bean.ChangePwdBean;
import com.furniture.bean.UserInfo;
import com.furniture.databinding.ActivityChangePasswordBinding;
import com.furniture.injector.components.AppComponent;
import com.furniture.injector.components.DaggerActivityComponent;
import com.furniture.injector.modules.ActivityModule;
import com.furniture.ui.view.TopBar;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observable;
import lbx.xtoollib.XIntent;
import lbx.xtoollib.XTools;
import lbx.xtoollib.phone.xLogUtil;

/**
 * @author lbx
 */
public class ChangePasswordActivity extends BaseActivity {

    private ChangePwdBean mChangePwdBean;
    @BindView(R.id.tb_pwd)
    TopBar mTopBar;
    @Inject
    UserInfo mUserInfo;

    public static XIntent getIntent(Context context) {
        return new XIntent(context, ChangePasswordActivity.class);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_change_password;
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
        mChangePwdBean = new ChangePwdBean();
        ((ActivityChangePasswordBinding) binding).setBean(mChangePwdBean);
    }

    @Override
    public void initView(View view) {
        mTopBar.bindActivity(this);
        mTopBar.setTitle("修改密码");
    }

    @Override
    public void initData() {

    }

    public void save(View view) {
        xLogUtil.e("mChangePwdBean:" + mChangePwdBean);
        Observable.combineLatest(
                Observable.just(mChangePwdBean.getOldPwd()),
                Observable.just(mChangePwdBean.getNewPwd()),
                Observable.just(mChangePwdBean.getSurePwd()),
                (s, s2, s3) -> !XTools.UiUtil().textIsEmpty(s, "请输入旧密码") &&
                        !XTools.UiUtil().textIsEmpty(s2, "请输入新密码") &&
                        !XTools.UiUtil().textIsEmpty(s3, "请再次输入新密码"))
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        if (mUserInfo.getPassword().equals(mChangePwdBean.getOldPwd())) {
                            if (mChangePwdBean.getNewPwd().equals(mChangePwdBean.getSurePwd())) {
                                mUserInfo.setPassword(mChangePwdBean.getNewPwd());
                                Config.DEFAULT_USER_INFO_MAP.replace(mUserInfo).save();
                                XTools.UiUtil().showToast("修改成功");
                                finish();
                            } else {
                                XTools.UiUtil().showToast("两次输入的密码不一致");
                            }
                        } else {
                            XTools.UiUtil().showToast("旧密码输入错误");
                        }
                    }
                });
    }
}
