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
import com.furniture.bean.action1.GoOffWorkAction;
import com.furniture.bean.action1.GoToWorkAction;
import com.furniture.bean.action1.LeisureAction;
import com.furniture.bean.action1.MeetPersonAction;
import com.furniture.bean.action1.NoonBreakAction;
import com.furniture.bean.action2.AirConditionerAction;
import com.furniture.bean.action2.CurtainsAction;
import com.furniture.bean.action2.LightAction;
import com.furniture.bean.action2.ScreenWindowAction;
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
 * 客餐厅
 */

public class SecondFloorFragment extends BaseRoomFragment {

    public static final String ROOM = "R2";

    public static SecondFloorFragment newInstance() {
        Bundle args = new Bundle();
        SecondFloorFragment fragment = new SecondFloorFragment();
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
        setName1("环境亮度");
        setName2("桌面亮度");
        setDescSymbol1("Lux");
        setDescSymbol2("Lux");
        setDescContent1("466");
        setDescContent2("198");
        setDescImg1(R.drawable.sun);
        setDescImg2(R.drawable.sun);
    }

    @Override
    public int bgRes() {
        return R.drawable.second_room_bg;
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
        //上班
        ActionBean bean1 = new GoToWorkAction(getActivity(), ROOM, SCE);
        //下班
        ActionBean bean2 = new GoOffWorkAction(getActivity(), ROOM, SCE);
        //休闲
        ActionBean bean3 = new LeisureAction(getActivity(), ROOM, SCE);
        //午休
        ActionBean bean4 = new NoonBreakAction(getActivity(), ROOM, SCE);
        //会客
        ActionBean bean5 = new MeetPersonAction(getActivity(), ROOM, SCE);
        return new ActionBean[]{bean1, bean2, bean3, bean4, bean5};
    }

    @Override
    public ActionBean[] initAllAction() {
        //窗帘
        ActionBean bean00 = new CurtainsAction(getActivity(), ROOM, "Curt1");
        //纱窗
        ActionBean bean0 = new ScreenWindowAction(getActivity(), ROOM, "Gau1");
        ActionBean bean1 = new LightAction(getActivity(), "1", ROOM, "L1");
        ActionBean bean2 = new LightAction(getActivity(), "2", ROOM, "L2");
        ActionBean bean3 = new LightAction(getActivity(), "3", ROOM, "L3");
        ActionBean bean4 = new LightAction(getActivity(), "4", ROOM, "L4");
        ActionBean bean5 = new LightAction(getActivity(), "5", ROOM, "L5");
        ActionBean bean6 = new LightAction(getActivity(), "6", ROOM, "L6");
        ActionBean bean7 = new LightAction(getActivity(), "7", ROOM, "L7");
        ActionBean bean8 = new LightAction(getActivity(), "8", ROOM, "L8");
        ActionBean bean9 = new LightAction(getActivity(), "9", ROOM, "L9");
        return new ActionBean[]{bean00, bean0, bean1, bean2, bean3, bean4, bean5, bean6, bean7, bean8, bean9};
//        return new ActionBean[]{};
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
        return false;
    }

    @Override
    public boolean setShowDesc2() {
        return false;
    }

    @Override
    protected int lineCount() {
        return 6;
    }
}
