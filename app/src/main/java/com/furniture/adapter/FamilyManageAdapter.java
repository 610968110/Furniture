package com.furniture.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.furniture.R;
import com.furniture.bean.UserInfo;
import com.furniture.databinding.ItemFamilyManageBinding;

import java.util.List;

import lbx.xtoollib.base.BaseDataAdapter;

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
 * @date 2018/8/30.
 */

public class FamilyManageAdapter extends BaseDataAdapter<UserInfo, ItemFamilyManageBinding, BaseDataAdapter.BaseHolder> {

    public FamilyManageAdapter(Context context, List<UserInfo> list) {
        super(context, list);
    }

    @Override
    public BaseHolder getHolder(View view, ViewDataBinding binding) {
        return new BaseHolder(view, binding);
    }

    @Override
    public int itemLayout() {
        return R.layout.item_family_manage;
    }

    @Override
    public void dataBinding(ItemFamilyManageBinding binding, int position, UserInfo entity, BaseHolder baseHolder) {
        binding.setUser(entity);
    }

    @Override
    public boolean itemClickEnable() {
        return true;
    }

    @Override
    public boolean itemLongClickEnable() {
        return true;
    }
}
