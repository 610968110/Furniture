package com.furniture.ui.fragment.room;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.DrawableRes;

import com.furniture.base.BaseRoomFragment;
import com.furniture.bean.ActionBean;
import com.furniture.bean.SocketBean;
import com.furniture.bean.action.ColdAction;
import com.furniture.bean.action.DryAction;
import com.furniture.bean.action.HotAction;
import com.furniture.bean.action.StuffyAction;
import com.furniture.bean.action.WetAction;
import com.furniture.bean.action1.EatAction;
import com.furniture.bean.action1.IntelligenceAction;
import com.furniture.bean.action1.MeetingGuestsAction;
import com.furniture.bean.action1.MovieAction;
import com.furniture.bean.action1.ReadAction;
import com.furniture.bean.action2.AirAction;
import com.furniture.bean.action2.AirConditionerAction;
import com.furniture.bean.action2.CurtainsAction;
import com.furniture.bean.action2.KeTingLightAction;
import com.furniture.bean.action2.KeTingLightBeltAction;
import com.furniture.bean.action2.ScreenWindowAction;
import com.furniture.bean.action2.ZouDaoLightAction;
import com.furniture.injector.components.ActivityComponent;
import com.furniture.injector.components.DaggerFragmentComponent;
import com.furniture.injector.modules.FragmentModule;

import static com.furniture.constant.Device.SCE;
import static com.furniture.constant.Device.SEN;

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
 * 客厅
 */

public class KeTingRoomFragment extends BaseRoomFragment {

    public static final String ROOM = "R1";

    public static KeTingRoomFragment newInstance(@DrawableRes int bg) {
        Bundle args = new Bundle();
        args.putInt("bg", bg);
        KeTingRoomFragment fragment = new KeTingRoomFragment();
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

    }

    @Override
    public int bgRes() {
        return getArguments().getInt("bg");
    }

    @Override
    public String room() {
        return ROOM;
    }

    @Override
    public ActionBean[] initTopAction() {
        //冷
        ActionBean bean1 = new ColdAction(getActivity(), ROOM, SEN);
        //热
        ActionBean bean2 = new HotAction(getActivity(), ROOM, SEN);
        //干
        ActionBean bean3 = new DryAction(getActivity(), ROOM, SEN);
        //湿
        ActionBean bean4 = new WetAction(getActivity(), ROOM, SEN);
        //闷
        ActionBean bean5 = new StuffyAction(getActivity(), ROOM, SEN);
       return new ActionBean[]{bean1, bean2, bean3, bean4, bean5};
//      return new ActionBean[]{bean1, bean2, bean3, bean5};
    }

    @Override
    public ActionBean[] initCenterAction() {
        //智能
        ActionBean bean1 = new IntelligenceAction(getActivity(), ROOM, SCE);
        //会客
        ActionBean bean2 = new MeetingGuestsAction(getActivity(), ROOM, SCE);
        //用餐
        ActionBean bean3 = new EatAction(getActivity(), ROOM, SCE);
        //阅读
        ActionBean bean4 = new ReadAction(getActivity(), ROOM, SCE);
        //观影
        ActionBean bean5 = new MovieAction(getActivity(), ROOM, SCE);
        return new ActionBean[]{bean1, bean2, bean3, bean4, bean5};
    }

    @Override
    public ActionBean[] initAllAction() {
        //空调
        ActionBean bean1 = new AirConditionerAction(getActivity(), ROOM, "AHU1");
        //新风
        ActionBean bean2 = new AirAction(getActivity(), ROOM, "FAU1");
        //窗帘
        ActionBean bean3 = new CurtainsAction(getActivity(), ROOM, "Curt1");
        //纱窗
        ActionBean bean4 = new ScreenWindowAction(getActivity(), ROOM, "Gau1");
        //客厅筒灯
        ActionBean bean5 = new KeTingLightAction(getActivity(), ROOM, "L1");
        //客厅灯带
        ActionBean bean6 = new KeTingLightBeltAction(getActivity(), ROOM, "L2");
        //走道筒灯
        ActionBean bean7 = new ZouDaoLightAction(getActivity(), ROOM, "L3");
        //餐厅吊灯
        //ActionBean bean8 = new DinnerRoomTopLightAction(getActivity(), ROOM, "L4");
        //客厅射灯
        //ActionBean bean9 = new ParlourMappingAction(getActivity(), ROOM, "L5");
        //电视射灯
        //ActionBean bean10 = new TVMappingAction(getActivity(), ROOM, "L6");
        //沙发射灯
        //ActionBean bean11 = new SofaMappingAction(getActivity(), ROOM, "L7");
        //顶面灯带
        //ActionBean bean12 = new TopLightBeltAction(getActivity(), ROOM, "L8");
        //墙面灯带
        //ActionBean bean13 = new WallLightBeltAction(getActivity(), ROOM, "L9");
        //走廊灯带
        //ActionBean bean14 = new CorridorLightBeltAction(getActivity(), ROOM, "L10");
        //阳台灯带
       // ActionBean bean15 = new BalconyLightBeltAction(getActivity(), ROOM, "L11");
        //return new ActionBean[]{bean1, bean2, bean3, bean4, bean5, bean6, bean7, bean8, bean9,
        //bean10, bean11,bean12, bean13};
        return new ActionBean[]{bean1, bean2, bean3, bean4, bean5, bean6, bean7};

    }

    @Override
    public void onSocketMsgReceive(SocketBean bean, String devid) {

    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {

    }
}
