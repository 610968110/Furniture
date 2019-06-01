package com.furniture.ui.activity;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.furniture.Config;
import com.furniture.R;
import com.furniture.base.BaseActivity;
import com.furniture.bean.UserInfo;
import com.furniture.databinding.ActivityChangeNickNameBinding;
import com.furniture.injector.components.AppComponent;
import com.furniture.injector.components.DaggerActivityComponent;
import com.furniture.injector.modules.ActivityModule;
import com.furniture.ui.view.TopBar;

import javax.inject.Inject;

import butterknife.BindView;
import lbx.xtoollib.XIntent;
import lbx.xtoollib.XTools;

/**
 * @author lbx
 */
public class ChangeNickNameActivity extends BaseActivity {

    @BindView(R.id.tb_changeNickName)
    TopBar mTopBar;
    @BindView(R.id.et_changeNickName)
    EditText mEditView;
    @BindView(R.id.btn_changeNickName)
    Button mButton;
    private ActivityChangeNickNameBinding mBinding;
    @Inject
    UserInfo mUserInfo;
    private String mNickName;

    public static XIntent getIntent(Context context) {
        return new XIntent(context, ChangeNickNameActivity.class);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_change_nick_name;
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
        mBinding = (ActivityChangeNickNameBinding) binding;
    }

    @Override
    public void initView(View view) {
        mTopBar.bindActivity(this);
        mTopBar.setTitle("修改昵称");
    }

    @Override
    public void initData() {
        mBinding.setInfo(mUserInfo);
        mNickName = mUserInfo.name.get();
    }

    @Override
    public void initListener() {
        mButton.setOnClickListener(v -> {
            if (TextUtils.isEmpty(mUserInfo.name.get())) {
                XTools.UiUtil().showToast("昵称不能为空");
            } else {
                XTools.UiUtil().showToast("修改成功");
                Config.DEFAULT_USER_INFO_MAP.replace(mUserInfo).save();
                finish();
            }
        });
    }

    @Override
    public void finish() {
        if (TextUtils.isEmpty(mUserInfo.name.get())) {
            mUserInfo.name.set(mNickName);
        }
        super.finish();
    }
}
