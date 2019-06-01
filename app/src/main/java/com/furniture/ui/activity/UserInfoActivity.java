package com.furniture.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;

import com.furniture.Config;
import com.furniture.R;
import com.furniture.base.BaseActivity;
import com.furniture.bean.UserInfo;
import com.furniture.databinding.ActivityUserInfoBinding;
import com.furniture.injector.components.AppComponent;
import com.furniture.injector.components.DaggerActivityComponent;
import com.furniture.injector.modules.ActivityModule;

import javax.inject.Inject;

import lbx.xtoollib.XIntent;
import lbx.xtoollib.XTools;

/**
 * @author lbx
 */
public class UserInfoActivity extends BaseActivity {

    private ActivityUserInfoBinding mBinding;
    private static final int IMAGE = 0x010;
    @Inject
    UserInfo mUserInfo;

    public static XIntent getIntent(Context context) {
        return new XIntent(context, UserInfoActivity.class);
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
        return R.layout.activity_user_info;
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {
        mBinding = (ActivityUserInfoBinding) binding;
        mBinding.setUser(mUserInfo);
        mBinding.setContext(this);
    }

    @Override
    public void initView(View view) {
        mBinding.tbUser.bindActivity(this);
        mBinding.tbUser.setTitle("个人信息");
    }

    @Override
    public void initData() {

    }

    public void changeIcon() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case IMAGE:
                    Uri selectedImage = data.getData();
                    if (selectedImage != null) {
                        Bitmap bitmap = XTools.BitmapUtil().uri2Bmp(selectedImage, UserInfoActivity.this);
                        if (bitmap != null) {
                            Bitmap icon = XTools.BitmapUtil().zoomBmp(bitmap, 300, 300);
                            bitmap.recycle();
                            if (icon != null) {
                                mUserInfo.setIcon(icon);
                                icon.recycle();
                                mBinding.invalidateAll();
                                Config.DEFAULT_USER_INFO_MAP.replace(mUserInfo).save();
                                XTools.UiUtil().showToast("更换成功");
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
