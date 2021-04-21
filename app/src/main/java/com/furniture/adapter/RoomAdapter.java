package com.furniture.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.furniture.R;
import com.furniture.bean.ActionBean;
import com.furniture.databinding.ItemRoomActionBinding;

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
 * @date 2018/8/24.
 */

public class RoomAdapter extends BaseDataAdapter<ActionBean, ItemRoomActionBinding, BaseDataAdapter.BaseHolder> {

    private int itemHeight;

    public RoomAdapter(Context context, List<ActionBean> list, int itemHeight) {
        super(context, list);
        this.itemHeight = itemHeight;
    }

    @Override
    public BaseHolder getHolder(View view, ViewDataBinding binding) {
        return new BaseHolder(view, binding);
    }

    @Override
    public int itemLayout() {
        return R.layout.item_room_action;
    }

    @Override
    public void dataBinding(ItemRoomActionBinding binding, int position, ActionBean entity, BaseHolder baseHolder) {
        binding.setBean(entity);
        binding.getRoot().getLayoutParams().height = itemHeight;
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
