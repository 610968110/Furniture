package com.furniture.ui.fragment.config;

import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.furniture.base.BaseRoomConfigFragment;
import com.furniture.injector.components.ActivityComponent;
import com.furniture.ui.fragment.room.BookRoomFragment;

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
 * @date 2018/9/25.
 */

public class ChildRoomConfigFragment extends BaseRoomConfigFragment {

    public static ChildRoomConfigFragment newInstance() {
        Bundle args = new Bundle();
        ChildRoomConfigFragment fragment = new ChildRoomConfigFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void bindComponent(ActivityComponent activityComponent) {

    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {

    }

    @Override
    public String room() {
        return BookRoomFragment.ROOM;
    }

    @Override
    public String[] selectBottomString() {
        return new String[]{"选择设备"};
    }

    @Override
    public void onSelectItemClick(int tag, int pos, String name) {
        xLogUtil.e(this, "onSelectItemClick");
    }
}
