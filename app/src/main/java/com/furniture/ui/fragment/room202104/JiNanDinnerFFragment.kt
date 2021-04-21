package com.furniture.ui.fragment.room202104

import android.os.Bundle
import android.support.annotation.DrawableRes
import android.view.View
import com.furniture.bean.ActionBean
import com.furniture.bean.action1.EatAction
import com.furniture.bean.action1.GoHomeAction
import com.furniture.bean.action1.IntelligenceAction
import com.furniture.bean.action1.LeisureAction
import com.furniture.bean.action1.MeetingGuestsAction
import com.furniture.bean.action1.MovieAction
import com.furniture.bean.action1.NoAction
import com.furniture.bean.action1.OutHomeAction
import com.furniture.bean.action1.ReadAction
import com.furniture.bean.action2.AirAction
import com.furniture.bean.action2.AirConditionerAction
import com.furniture.bean.action2.CurtainsAction
import com.furniture.bean.action2.DEHAction
import com.furniture.bean.action2.HeatingAction
import com.furniture.bean.action2.HumidifierAction
import com.furniture.bean.action2.ScreenWindowAction
import com.furniture.bean.jinanbean.SupperLight
import com.furniture.constant.Device
import com.furniture.ui.fragment.room.KeTingRoomFragment

/**
 * Author: liboxin
 * Date: 2019/6/11
 * Time: 18:44
 * Desc:
 */
class JiNanDinnerFFragment : KeTingRoomFragment() {

    override fun initAllAction(): Array<ActionBean> {
        return arrayOf(
            SupperLight(activity, ROOM, "L1", "吊灯"),
            SupperLight(activity, ROOM, "L2", "射灯"),
            CurtainsAction(activity, ROOM, "Curt1", "布帘"),
            ScreenWindowAction(activity, ROOM, "Gau1", "纱帘"),
            //新风
            AirAction(activity, ROOM, "FAU1"),
            //空调
            AirConditionerAction(activity, ROOM, "AHU1"),
            // 地暖
            HeatingAction(activity, ROOM, "FH1"),
            // 除湿器
            DEHAction(activity, ROOM, "Deh1"),
            // 加湿器
            HumidifierAction(activity, ROOM, "Humi1")
        )
    }

    override fun initCenterAction(): Array<ActionBean> {
        return arrayOf(
            IntelligenceAction(activity, ROOM, Device.SCE).apply { open = 0 },
            // 会客
            MeetingGuestsAction(activity, ROOM, Device.SCE).apply { open = 1 },
            // 用餐
            EatAction(activity, ROOM, Device.SCE).apply { open = 2 },
            ReadAction(activity, ROOM, Device.SCE).apply { open = 3 },
            MovieAction(activity, ROOM, Device.SCE).apply { open = 4 }
        )
    }

//    override fun initTopAction(): Array<ActionBean> {
//        return arrayOf()
//    }

    override fun showEnvironmentView(): Int {
        return View.VISIBLE
    }

    companion object {
        const val ROOM = "R1"

        @JvmStatic
        fun newInstance(@DrawableRes bg: Int): JiNanDinnerFFragment {
            val args = Bundle()
            args.putInt("bg", bg)
            val fragment = JiNanDinnerFFragment()
            fragment.arguments = args
            return fragment
        }
    }
}