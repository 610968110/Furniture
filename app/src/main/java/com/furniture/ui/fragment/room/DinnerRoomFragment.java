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
import com.furniture.bean.action1.IntelligenceAction;
import com.furniture.bean.action1.MovieAction;
import com.furniture.bean.action1.ReadAction;
import com.furniture.bean.action1.RomanticAction;
import com.furniture.bean.action1.SleepAction;
import com.furniture.bean.action2.AirAction;
import com.furniture.bean.action2.AirConditionerAction;
import com.furniture.bean.action2.CurtainsAction;
import com.furniture.bean.action2.DinnerRoomLightAction;
import com.furniture.bean.action2.DinnerRoomLightBeltAction;
import com.furniture.bean.action2.DinnerRoomMainLightAction;
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
 * 餐厅
 */

public class DinnerRoomFragment extends BaseRoomFragment {

    public static final String ROOM = "R2";

    public static DinnerRoomFragment newInstance(@DrawableRes int bg) {
        Bundle args = new Bundle();
        args.putInt("bg", bg);
        DinnerRoomFragment fragment = new DinnerRoomFragment();
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
        //餐厅主灯
        ActionBean bean5 = new DinnerRoomMainLightAction(getActivity(), ROOM, "L1");
        //餐厅筒灯
        ActionBean bean6 = new DinnerRoomLightAction(getActivity(), ROOM, "L2");
        //餐厅灯带
        ActionBean bean7 = new DinnerRoomLightBeltAction(getActivity(), ROOM, "L3");
        //主卧床头灯1
//        ActionBean bean8 = new MasterRoomBedLight1Action(getActivity(), ROOM, "L4");
//        //主卧过道灯
//        ActionBean bean9 = new MasterRoomGuoDaoLightAction(getActivity(), ROOM, "L5");
//        //主卧床头灯2
//        ActionBean bean10 = new MasterRoomBedLight2Action(getActivity(), ROOM, "L6");
        //顶面灯带
        //ActionBean bean11 = new TopLightBeltAction(getActivity(), ROOM, "L7");
        //墙面灯带
        //ActionBean bean12 = new WallLightBeltAction(getActivity(), ROOM, "L8");
        //阳台灯带
        //ActionBean bean13 = new BalconyLightBeltAction(getActivity(), ROOM, "L9");
        return new ActionBean[]{bean1, bean2, bean3, bean4, bean5, bean6, bean7};
        //return new ActionBean[]{bean1, bean2, bean3, bean4, bean5, bean6, bean7, bean8, bean9,
        //bean10, bean11,bean12, bean13};
    }

    @Override
    public void onSocketMsgReceive(SocketBean bean, String devid) {

    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {

    }
}
