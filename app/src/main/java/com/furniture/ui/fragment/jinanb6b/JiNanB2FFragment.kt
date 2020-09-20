package com.furniture.ui.fragment.jinanb6b

import android.os.Bundle
import android.support.annotation.DrawableRes
import android.view.View
import com.furniture.bean.ActionBean
import com.furniture.bean.action1.SleepAction
import com.furniture.bean.action1.WeekUpAction
import com.furniture.bean.action2.AirConditionerAction
import com.furniture.bean.jinanbean.SupperLight
import com.furniture.constant.Device
import com.furniture.ui.fragment.room.BookRoomFragment

/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: liboxin
 * Email: liboxin@worktile.com
 * Date: 2019/6/11
 * Time: 18:47
 * Desc:
 */
class JiNanB2FFragment : BookRoomFragment() {
    override fun initAllAction(): Array<ActionBean> {
        return arrayOf(
            SupperLight(activity, ROOM, "L1", "主灯"),
            SupperLight(activity, ROOM, "L2", "灯带"),
            SupperLight(activity, ROOM, "L3", "卫生间射灯"),
            SupperLight(activity, ROOM, "L4", "卫生间灯带"),
            SupperLight(activity, ROOM, "L5", "卫生间淋浴灯"),
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
        const val ROOM = "R4"
        @JvmStatic
        fun newInstance(@DrawableRes bg: Int): JiNanB2FFragment {
            val args = Bundle()
            args.putInt("bg", bg)
            val fragment = JiNanB2FFragment()
            fragment.arguments = args
            return fragment
        }
    }
}