package com.furniture.ui.fragment.room202104

import android.os.Bundle
import android.support.annotation.DrawableRes
import android.view.View
import com.furniture.bean.ActionBean
import com.furniture.bean.action2.AirConditionerAction
import com.furniture.bean.action2.CurtainsAction
import com.furniture.bean.action2.ScreenWindowAction
import com.furniture.bean.jinanbean.SupperLight
import com.furniture.ui.fragment.room.DinnerRoomFragment

/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: liboxin
 * Email: liboxin@worktile.com
 * Date: 2019/6/11
 * Time: 18:45
 * Desc:
 */
class JiNanXuanguanFFragment : DinnerRoomFragment() {
    override fun initAllAction(): Array<ActionBean> {
        return arrayOf(
            //新风
//            AirAction(activity, ROOM, "FAU1"),
            SupperLight(activity, ROOM, "L1", "吊灯"),
            SupperLight(activity, ROOM, "L2", "射灯"),
            SupperLight(activity, ROOM, "L3", "灯带"),
            SupperLight(activity, ROOM, "L4", "衣帽间灯"),
            SupperLight(activity, ROOM, "L5", "手盆灯"),
            SupperLight(activity, ROOM, "L5", "淋浴灯"),
            SupperLight(activity, ROOM, "L5", "衣帽间灯带"),
            SupperLight(activity, ROOM, "L5", "露台射灯"),
            CurtainsAction(activity, ROOM, "Curt1", "布帘"),
            ScreenWindowAction(activity, ROOM, "Gau1", "纱帘"),
            //空调
            AirConditionerAction(activity, ROOM, "AHU1"))
    }

//    override fun initCenterAction(): Array<ActionBean> {
//        return arrayOf(
//            // 起床
//            WeekUpAction(activity, ROOM, Device.SCE).apply { open = 1 },
//            // 睡眠
//            SleepAction(activity, ROOM, Device.SCE).apply { open = 2 }
//        )
//    }

//    override fun initTopAction(): Array<ActionBean> {
//        return arrayOf()
//    }

    override fun showEnvironmentView(): Int {
        return View.INVISIBLE
    }

    companion object {
        const val ROOM = "R2"

        @JvmStatic
        fun newInstance(@DrawableRes bg: Int): JiNanXuanguanFFragment {
            val args = Bundle()
            args.putInt("bg", bg)
            val fragment = JiNanXuanguanFFragment()
            fragment.arguments = args
            return fragment
        }
    }
}