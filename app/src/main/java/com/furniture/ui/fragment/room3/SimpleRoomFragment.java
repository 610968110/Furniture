package com.furniture.ui.fragment.room3;

import android.os.Bundle;
import android.support.annotation.DrawableRes;

import com.furniture.bean.ActionBean;
import com.furniture.bean.action2.AirAction;
import com.furniture.bean.action2.AirConditionerAction;
import com.furniture.bean.action2.BalconyLightBeltAction;
import com.furniture.bean.action2.CorridorLightBeltAction;
import com.furniture.bean.action2.CurtainsAction;
import com.furniture.bean.action2.DinnerRoomLeftMappingAction;
import com.furniture.bean.action2.DinnerRoomRightMappintAction;
import com.furniture.bean.action2.DinnerRoomTopLightAction;
import com.furniture.bean.action2.DoorLightAction;
import com.furniture.bean.action2.IntelligenceWindow;
import com.furniture.bean.action2.ParlourMappingAction;
import com.furniture.bean.action2.SofaMappingAction;
import com.furniture.bean.action2.TVMappingAction;
import com.furniture.bean.action2.TopLightBeltAction;
import com.furniture.bean.action2.WallLightBeltAction;
import com.furniture.injector.components.ActivityComponent;
import com.furniture.injector.components.DaggerFragmentComponent;
import com.furniture.injector.modules.FragmentModule;
import com.furniture.ui.fragment.room.KeTingRoomFragment;

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
 * 智能家居小demo
 */

public class SimpleRoomFragment extends KeTingRoomFragment {

    public static SimpleRoomFragment newInstance(@DrawableRes int bg) {
        Bundle args = new Bundle();
        args.putInt("bg", bg);
        SimpleRoomFragment fragment = new SimpleRoomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void bindComponent(ActivityComponent activityComponent) {
        mFragmentComponent = DaggerFragmentComponent.builder()
                .activityComponent(activityComponent)
                .fragmentModule(new FragmentModule())
                .build();
        mFragmentComponent.inject(this);
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public ActionBean[] initTopAction() {
        return null;
    }

    @Override
    public ActionBean[] initCenterAction() {
        return null;
    }

    @Override
    public ActionBean[] initAllAction() {
        //空调
        ActionBean bean1 = new AirConditionerAction(getActivity(), ROOM, "AHU1", null);
        //新风
        ActionBean bean2 = new AirAction(getActivity(), ROOM, "FAU1");
        //窗帘
        ActionBean bean3 = new CurtainsAction(getActivity(), ROOM, "Curt1", null);
        //智能窗
        ActionBean bean4 = new IntelligenceWindow(getActivity(), ROOM, "Gau1");
        //门口筒灯
        ActionBean bean5 = new DoorLightAction(getActivity(), ROOM, "L1", null);
        //餐厅射灯(左)
        ActionBean bean6 = new DinnerRoomLeftMappingAction(getActivity(), ROOM, "L2", null);
        //餐厅射灯(右)
        ActionBean bean7 = new DinnerRoomRightMappintAction(getActivity(), ROOM, "L3", null);
        //餐厅吊灯
        ActionBean bean8 = new DinnerRoomTopLightAction(getActivity(), ROOM, "L4", null);
        //客厅射灯
        ActionBean bean9 = new ParlourMappingAction(getActivity(), ROOM, "L5", null);
        //电视射灯
        ActionBean bean10 = new TVMappingAction(getActivity(), ROOM, "L6", null);
        //沙发射灯
        ActionBean bean11 = new SofaMappingAction(getActivity(), ROOM, "L7", null);
        //顶面灯带
        ActionBean bean12 = new TopLightBeltAction(getActivity(), ROOM, "L8", null);
        //墙面灯带
        ActionBean bean13 = new WallLightBeltAction(getActivity(), ROOM, "L9", null);
        //走廊灯带
        ActionBean bean14 = new CorridorLightBeltAction(getActivity(), ROOM, "L10", null);
        //阳台灯带
        ActionBean bean15 = new BalconyLightBeltAction(getActivity(), ROOM, "L11", null);
        return new ActionBean[]{bean1, bean2, bean3, bean4, bean5, bean6, bean7, bean8,
                bean9, bean10, bean11, bean12, bean13, bean14, bean15};
    }

    @Override
    public boolean setShowBigTemp() {
        return true;
    }
}
