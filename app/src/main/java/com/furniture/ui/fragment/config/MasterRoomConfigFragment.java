package com.furniture.ui.fragment.config;

import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.furniture.base.BaseRoomConfigFragment;
import com.furniture.injector.components.ActivityComponent;
import com.furniture.ui.fragment.room.DinnerRoomFragment;

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

public class MasterRoomConfigFragment extends BaseRoomConfigFragment {

    public static MasterRoomConfigFragment newInstance() {
        Bundle args = new Bundle();
        MasterRoomConfigFragment fragment = new MasterRoomConfigFragment();
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
        return DinnerRoomFragment.ROOM;
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
