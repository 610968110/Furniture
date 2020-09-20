package com.furniture.ui.fragment.jinanb6a

import android.os.Bundle
import android.support.annotation.DrawableRes
import android.view.View
import com.furniture.bean.ActionBean
import com.furniture.bean.action1.SleepAction
import com.furniture.bean.action1.WeekUpAction
import com.furniture.bean.action2.AirConditionerAction
import com.furniture.bean.jinanbean.SupperLight
import com.furniture.constant.Device
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
            //                //新风
            //                new AirAction(getActivity(), ROOM, "FAU1"),
            SupperLight(activity, ROOM, "L1", "餐厅主灯"),
            SupperLight(activity, ROOM, "L2", "餐厅灯带"),
            SupperLight(activity, ROOM, "L3", "厨房主灯"),
            SupperLight(activity, ROOM, "L4", "厨房灯带"),
            SupperLight(activity, ROOM, "L5", "次卫筒灯"),
            SupperLight(activity, ROOM, "L6", "次卫灯带"),
            SupperLight(activity, ROOM, "L7", "次卫淋浴灯"),
            //空调
            AirConditionerAction(activity, ROOM, "AHU1"))
    }

    override fun initCenterAction(): Array<ActionBean> {
        return arrayOf(
            // 起床
            WeekUpAction(activity, ROOM, Device.SCE).apply { open = 1 },
            // 睡眠
            SleepAction(activity, ROOM, Device.SCE).apply { open = 2 }
        )
    }

    override fun initTopAction(): Array<ActionBean> {
        return arrayOf()
    }

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