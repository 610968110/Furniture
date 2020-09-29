package com.furniture.ui.fragment.dujiangyan

import android.os.Bundle
import android.support.annotation.DrawableRes
import android.view.View
import com.furniture.bean.ActionBean
import com.furniture.bean.action2.AirConditionerAction
import com.furniture.bean.action2.CurtainsAction
import com.furniture.bean.action2.HeatingAction
import com.furniture.bean.action2.ScreenWindowAction
import com.furniture.bean.jinanbean.SupperLight
import com.furniture.ui.fragment.room.DinnerRoomFragment
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
            SupperLight(activity, ROOM, "L1", "客厅灯带"),
            SupperLight(activity, ROOM, "L2", "客厅射灯"),
            SupperLight(activity, ROOM, "L3", "餐厅吊灯"),
            SupperLight(activity, ROOM, "L4", "过道射灯"),
            SupperLight(activity, ROOM, "L5", "手盆灯"),
            SupperLight(activity, ROOM, "L6", "淋浴灯"),
            SupperLight(activity, ROOM, "L7", "厨房射灯"),
            SupperLight(activity, ROOM, "L8", "厨房吊灯"),
            //空调
            AirConditionerAction(activity, ROOM, "AHU1"),
            //新风
//            AirAction(activity, ROOM, "FAU1"),
            // 地暖
            HeatingAction(activity, ROOM, "FH1"),
            CurtainsAction(activity, ROOM, "Curt1", "布帘"),
            ScreenWindowAction(activity, ROOM, "Gau1", "纱帘")
        )
    }

//    override fun initCenterAction(): Array<ActionBean> {
//        return arrayOf(
//            // 回家
//            GoHomeAction(activity, ROOM, Device.SCE).apply { open = 1 },
//            // 离家
//            OutHomeAction(activity, ROOM, Device.SCE).apply { open = 2 },
//            // 休闲
//            LeisureAction(activity, ROOM, Device.SCE).apply { open = 3 },
//            // 会客
//            MeetingGuestsAction(activity, ROOM, Device.SCE).apply { open = 4 }
//        )
//    }

//    override fun initTopAction(): Array<ActionBean> {
//        return arrayOf()
//    }

    override fun showEnvironmentView(): Int {
        return View.INVISIBLE
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