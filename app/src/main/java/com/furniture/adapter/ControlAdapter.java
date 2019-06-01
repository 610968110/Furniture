package com.furniture.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.view.ViewGroup;

import com.furniture.R;
import com.furniture.bean.ActionBean;
import com.furniture.databinding.ItemControlActionBinding;

import java.util.List;

import lbx.xtoollib.XTools;
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
 * @date 2018/8/25.
 */
public class ControlAdapter extends BaseDataAdapter<ActionBean, ItemControlActionBinding, BaseDataAdapter.BaseHolder> {

    public ControlAdapter(Context context, List<ActionBean> list) {
        super(context, list);
    }

    @Override
    public BaseHolder getHolder(View view, ViewDataBinding binding) {
        return new BaseHolder(view, binding);
    }

    @Override
    public int itemLayout() {
        return R.layout.item_control_action;
    }

    @Override
    public void dataBinding(ItemControlActionBinding binding, int position, ActionBean entity, BaseHolder baseHolder) {
        binding.setBean(entity);
        ViewGroup.LayoutParams layoutParams = binding.getRoot().getLayoutParams();
        layoutParams.width = XTools.ResUtil().getDimen(R.dimen.bottom_action_view_width);
    }

    @Override
    public boolean itemClickEnable() {
        return true;
    }

    @Override
    public boolean itemLongClickEnable() {
        return false;
    }
}
