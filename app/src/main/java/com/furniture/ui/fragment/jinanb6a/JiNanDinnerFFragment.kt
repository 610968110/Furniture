package com.furniture.ui.fragment.jinanb6a

import android.os.Bundle
import android.support.annotation.DrawableRes
import android.view.View
import com.furniture.bean.ActionBean
import com.furniture.bean.action1.GoHomeAction
import com.furniture.bean.action1.LeisureAction
import com.furniture.bean.action1.MeetingGuestsAction
import com.furniture.bean.action1.OutHomeAction
import com.furniture.bean.action2.AirAction
import com.furniture.bean.action2.AirConditionerAction
import com.furniture.bean.jinanbean.SupperLight
import com.furniture.constant.Device
import com.furniture.ui.fragment.room.KeTingRoomFragment

/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: liboxin
 * Email: liboxin@worktile.com
 * Date: 2019/6/11
 * Time: 18:44
 * Desc:
 */
class JiNanDinnerFFragment : KeTingRoomFragment() {
    override fun initAllAction(): Array<ActionBean> {
        return arrayOf(
            SupperLight(activity, ROOM, "L1", "主灯"),
            SupperLight(activity, ROOM, "L2", "灯带"),
            SupperLight(activity, ROOM, "L3", "走廊灯"),
            SupperLight(activity, ROOM, "L4", "阳台灯"),
            //空调
            AirConditionerAction(activity, ROOM, "AHU1"),
            //新风
            AirAction(activity, ROOM, "FAU1"))
    }

    override fun initCenterAction(): Array<ActionBean> {
        return arrayOf(
            // 回家
            GoHomeAction(activity, ROOM, Device.SCE).apply { open = 1 },
            // 离家
            OutHomeAction(activity, ROOM, Device.SCE).apply { open = 2 },
            // 休闲
            LeisureAction(activity, ROOM, Device.SCE).apply { open = 3 },
            // 会客
            MeetingGuestsAction(activity, ROOM, Device.SCE).apply { open = 4 }
        )
    }

    override fun initTopAction(): Array<ActionBean> {
        return arrayOf()
    }

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