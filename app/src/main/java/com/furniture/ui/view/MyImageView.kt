package com.furniture.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: liboxin
 * Email: liboxin@worktile.com
 * Date: 2019-09-04
 * Time: 20:29
 * Desc:
 */
class MyImageView : ImageView {

    private var h = 0F

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val sw = MeasureSpec.getSize(widthMeasureSpec)
        val w = drawable.intrinsicWidth
        h = drawable.intrinsicHeight * sw * 1.0F / w
        val mode = MeasureSpec.getMode(heightMeasureSpec)
        val newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(h.toInt(), mode)
        setMeasuredDimension(widthMeasureSpec, newHeightMeasureSpec)
    }
}