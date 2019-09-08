package com.furniture.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import lbx.xtoollib.XTools

/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: Administrator
 * Email: Administrator@worktile.com
 * Date: 2019/9/8
 * Time: 9:07
 * Desc:
 */
class ShequView : LinearLayout {

    private var contentView: RecyclerView
    private var titleView: TextView
    private val paint by lazy {
        val p = Paint(Paint.FILTER_BITMAP_FLAG)
        p.flags = Paint.ANTI_ALIAS_FLAG
        p.color = Color.parseColor("#39928C")
        p
    }
    private val rect: Rect

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        orientation = VERTICAL
        contentView = NoScrollRecycleView(context)
        titleView = TextView(context)
        titleView.textSize = XTools.WindowUtil().dip2px(18.0F).toFloat()
        titleView.setTextColor(Color.BLACK)
        contentView.layoutManager = GridLayoutManager(context, 5)
        val mp = MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        mp.marginStart = 15
        addView(titleView, mp)
        addView(contentView)
        setBackgroundColor(0xfff)
        setWillNotDraw(false)

        rect = Rect(paddingStart, 0, paddingStart + 5, titleView.textSize.toInt() * 3 / 2)
    }

    fun init(title: String, adapter: RecyclerView.Adapter<*>) {
        titleView.text = title
        contentView.adapter = adapter
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRect(rect, paint)
    }
}