package com.furniture.ui.fragment.room2;

import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.furniture.R;
import com.furniture.base.BaseRoomFragment;
import com.furniture.bean.ActionBean;
import com.furniture.bean.SocketBean;
import com.furniture.bean.action.ColdAction;
import com.furniture.bean.action.DryAction;
import com.furniture.bean.action.HotAction;
import com.furniture.bean.action.StuffyAction;
import com.furniture.bean.action.WetAction;
import com.furniture.bean.action1.IntelligenceAction;
import com.furniture.bean.action1.MovieAction;
import com.furniture.bean.action1.ReadAction;
import com.furniture.bean.action1.RomanticAction;
import com.furniture.bean.action1.SleepAction;
import com.furniture.bean.action2.AirAction;
import com.furniture.bean.action2.AirConditionerAction;
import com.furniture.bean.action2.BedHeadMappingAction;
import com.furniture.bean.action2.BigBedMappingAction;
import com.furniture.bean.action2.CurtainsAction;
import com.furniture.bean.action2.DoorLightAction;
import com.furniture.bean.action2.ScreenWindowAction;
import com.furniture.bean.action2.TVMappingAction;
import com.furniture.bean.action2.TopLightBeltAction;
import com.furniture.bean.action2.WallLightBeltAction;
import com.furniture.bean.action2.WardrobeLightBeltAction;
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
 * @date 2018/8/25.
 */

public class FourthFloorFragment extends BaseRoomFragment {

    public static final String ROOM = "R4";

    public static FourthFloorFragment newInstance() {
        Bundle args = new Bundle();
        FourthFloorFragment fragment = new FourthFloorFragment();
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
        setDescSymbol1("");
        setDescSymbol2("");
        setName1("PH值");
        setName2("导电率");
        setDescContent1("7.0");
        setDescContent2("6");
        setDescImg1(R.drawable.water);
        setDescImg2(R.drawable.water);
    }

    @Override
    public int bgRes() {
        return R.drawable.fourth_room_bg;
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
        //空调
        ActionBean bean6 = new AirConditionerAction(getActivity(), ROOM, "AHU1");
        return new ActionBean[]{bean1, bean2, bean3, bean4, bean5, bean6};
    }

    @Override
    public ActionBean[] initCenterAction() {
        //智能
        ActionBean bean1 = new IntelligenceAction(getActivity(), ROOM, SCE);
        //睡眠
        ActionBean bean2 = new SleepAction(getActivity(), ROOM, SCE);
        //浪漫
        ActionBean bean3 = new RomanticAction(getActivity(), ROOM, SCE);
        //阅读
        ActionBean bean4 = new ReadAction(getActivity(), ROOM, SCE);
        //观影
        ActionBean bean5 = new MovieAction(getActivity(), ROOM, SCE);
        return new ActionBean[]{};
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
        //门口筒灯
        ActionBean bean5 = new DoorLightAction(getActivity(), ROOM, "L1");
        //床头射灯
        ActionBean bean6 = new BedHeadMappingAction(getActivity(), ROOM, "L2");
        //电视射灯
        ActionBean bean7 = new TVMappingAction(getActivity(), ROOM, "L3");
        //大床射灯
        ActionBean bean8 = new BigBedMappingAction(getActivity(), ROOM, "L4");
        //顶面灯带
        ActionBean bean9 = new TopLightBeltAction(getActivity(), ROOM, "L5");
        //墙面灯带
        ActionBean bean10 = new WallLightBeltAction(getActivity(), ROOM, "L6");
        //衣柜灯带
        ActionBean bean11 = new WardrobeLightBeltAction(getActivity(), ROOM, "L7");
        return new ActionBean[]{};
    }

    @Override
    public void onSocketMsgReceive(SocketBean bean, String devid) {

    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {

    }

    @Override
    public boolean setShowBigTemp() {
        return false;
    }

    @Override
    public boolean setShowDesc1() {
        return true;
    }

    @Override
    public boolean setShowDesc2() {
        return true;
    }

    @Override
    protected int lineCount() {
        return 6;
    }
}
