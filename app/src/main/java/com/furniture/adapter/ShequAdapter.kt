package com.furniture.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.furniture.bean.ActionBean
import com.furniture.databinding.ItemRoomActionBinding
import lbx.xtoollib.base.BaseDataAdapter

/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: Administrator
 * Email: Administrator@worktile.com
 * Date: 2019/9/8
 * Time: 0:22
 * Desc:
 */
class ShequAdapter(context: Context, list: List<ActionBean>, private val itemHeight: Int)
    : RoomAdapter(context, list, itemHeight + 80), BaseDataAdapter.OnItemClickListener<ActionBean> {
    override fun onItemLongClick(recyclerView: RecyclerView?, id: Int, position: Int, entity: ActionBean?) {
        entity?.task?.actionClick(true)
    }

    override fun onItemClick(recyclerView: RecyclerView?, id: Int, position: Int, entity: ActionBean?) {
        entity?.task?.actionClick(false)
    }

    init {
        onItemClickListener = this
    }

    override fun dataBinding(binding: ItemRoomActionBinding?, position: Int, entity: ActionBean?, baseHolder: BaseHolder?) {
        super.dataBinding(binding, position, entity, baseHolder)
        binding?.bcv?.setImagesize(itemHeight)
    }
}