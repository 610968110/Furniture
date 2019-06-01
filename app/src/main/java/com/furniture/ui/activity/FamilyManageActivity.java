package com.furniture.ui.activity;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.furniture.Config;
import com.furniture.R;
import com.furniture.adapter.FamilyManageAdapter;
import com.furniture.base.BaseActivity;
import com.furniture.bean.UserInfo;
import com.furniture.databinding.ActivityFamilyManageBinding;
import com.furniture.injector.components.AppComponent;
import com.furniture.injector.components.DaggerActivityComponent;
import com.furniture.injector.modules.ActivityModule;
import com.furniture.ui.view.DeleteDialog;
import com.furniture.ui.view.UserInfoDialog;

import java.util.ArrayList;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import lbx.xtoollib.XIntent;
import lbx.xtoollib.XTools;
import lbx.xtoollib.base.BaseDataAdapter;
import lbx.xtoollib.phone.xLogUtil;

/**
 * @author lbx
 */
public class FamilyManageActivity extends BaseActivity {

    private ActivityFamilyManageBinding mBinding;
    private FamilyManageAdapter mAdapter;
    @BindView(R.id.rv_family_manage)
    RecyclerView mRecyclerView;
    private ArrayList<UserInfo> mUsersList;
    @Inject
    UserInfo mUserInfo;

    public static XIntent getIntent(Context context) {
        return new XIntent(context, FamilyManageActivity.class);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_family_manage;
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
        mBinding = (ActivityFamilyManageBinding) binding;
    }

    @Override
    public void initView(View view) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.tbFamilyManage.bindActivity(this);
        mBinding.tbFamilyManage.setTitle("家庭管理");
        mBinding.tbFamilyManage.setRightTextViewAction("添加成员", v -> {
            xLogUtil.e(this, "添加成员");
            addUser();
        });
    }

    @Override
    public void initData() {
        mUsersList = Config.DEFAULT_USER_INFO_MAP.toList();
        mAdapter = new FamilyManageAdapter(this, mUsersList);
        mBinding.setUser(mUserInfo);
        mBinding.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        mAdapter.setOnItemClickListener(new BaseDataAdapter.OnItemClickListener<UserInfo>() {
            @Override
            public void onItemClick(RecyclerView recyclerView, int id, int position, UserInfo entity) {
                xLogUtil.e(this, "点击:" + entity);
                changeUser(entity);
            }

            @Override
            public void onItemLongClick(RecyclerView recyclerView, int id, int position, UserInfo entity) {
                xLogUtil.e(this, "长按:" + entity);
                deleteUser(entity);
            }
        });
    }

    private void addUser() {
        new UserInfoDialog.Builder()
                .style(UserInfoDialog.ADD)
                .userList(mUsersList)
                .onUserInfoListener((dialog, user, sure) -> {
                    if (sure && user != null) {
                        if (mUsersList != null && mUsersList.contains(user)) {
                            XTools.UiUtil().showToast("用户已存在");
                            return;
                        }
                        xLogUtil.e(this, "添加信息:" + user);
                        Config.DEFAULT_USER_INFO_MAP.put(user).save();
                        mAdapter.notifyDataSetChanged();
                        XTools.UiUtil().showToast("添加成功");
                    }
                    dialog.dismiss();
                })
                .build(FamilyManageActivity.this)
                .show();
    }

    private void changeUser(UserInfo entity) {
        new UserInfoDialog.Builder()
                .style(UserInfoDialog.EDIT)
                .userList(mUsersList)
                .bindUser(entity)
                .onUserInfoListener((dialog, user, sure) -> {
                    if (sure && user != null) {
                        xLogUtil.e(this, "修改信息:" + user);
                        Config.DEFAULT_USER_INFO_MAP.replace(user).save();
                        mAdapter.notifyDataSetChanged();
                        XTools.UiUtil().showToast("修改成功");
                    }
                    dialog.dismiss();
                })
                .build(FamilyManageActivity.this)
                .show();
    }

    private void deleteUser(UserInfo entity) {
        String name = entity.name.get();
        new DeleteDialog.Builder()
                .text(String.format(Locale.CHINA, "确定删除 %s ?", name))
                .lightText(name)
                .onButtonClickListener((dialog, sure) -> {
                    if (sure) {
                        if (entity.isManage()) {
                            XTools.UiUtil().showToast("不能删除管理员");
                            return;
                        } else if (name.equals(mUserInfo.name.get())) {
                            XTools.UiUtil().showToast("不能删除自己");
                            return;
                        } else {
                            xLogUtil.e(this, "删除成员:" + entity);
                            Config.DEFAULT_USER_INFO_MAP.remove(entity).save();
                            mAdapter.notifyDataSetChanged();
                            XTools.UiUtil().showToast("删除成功");
                        }
                    }
                    dialog.dismiss();
                })
                .build(FamilyManageActivity.this)
                .show();
    }
}
