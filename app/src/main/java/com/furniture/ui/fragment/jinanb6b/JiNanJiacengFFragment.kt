package com.furniture.ui.fragment.jinanb6b

import android.os.Bundle
import android.support.annotation.DrawableRes
import android.view.View
import com.furniture.bean.ActionBean
import com.furniture.bean.action1.SleepAction
import com.furniture.bean.action1.WeekUpAction
import com.furniture.bean.action2.AirConditionerAction
import com.furniture.bean.action2.CurtainsAction
import com.furniture.bean.action2.ScreenWindowAction
import com.furniture.bean.jinanbean.SupperLight
import com.furniture.constant.Device
import com.furniture.ui.fragment.room.MasterRoomFragment

/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: liboxin
 * Email: liboxin@worktile.com
 * Date: 2019/6/11
 * Time: 18:46
 * Desc:
 */
class JiNanJiacengFFragment : MasterRoomFragment() {
    override fun initAllAction(): Array<ActionBean> {
        return arrayOf(
            SupperLight(activity, ROOM, "L1", "主灯"),
            SupperLight(activity, ROOM, "L2", "筒灯"),
            SupperLight(activity, ROOM, "L3", "灯带"),
            SupperLight(activity, ROOM, "L4", "淋浴灯"),
            SupperLight(activity, ROOM, "L5", "手盆灯"),
            SupperLight(activity, ROOM, "L6", "灯带"),
            CurtainsAction(activity, ROOM, "Curt1", "布帘"),
            ScreenWindowAction(activity, ROOM, "Gau1", "纱帘"),
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
        const val ROOM = "R3"
        @JvmStatic
        fun newInstance(@DrawableRes bg: Int): JiNanJiacengFFragment {
            val args = Bundle()
            args.putInt("bg", bg)
            val fragment = JiNanJiacengFFragment()
            fragment.arguments = args
            return fragment
        }
    }
}