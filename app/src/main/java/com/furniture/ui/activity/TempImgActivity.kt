package com.furniture.ui.activity

import android.content.Context
import android.content.Intent
import android.databinding.ViewDataBinding
import android.view.View
import com.furniture.R
import com.furniture.base.BaseActivity
import com.furniture.databinding.ActivityTempImgBinding
import com.furniture.injector.components.AppComponent

/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: Administrator
 * Email: Administrator@worktile.com
 * Date: 2019/9/8
 * Time: 10:13
 * Desc:
 */
class TempImgActivity : BaseActivity() {

    private lateinit var binding: ActivityTempImgBinding

    companion object {
        fun start(context: Context, title: String, img: Int) {
            val intent = Intent(context, TempImgActivity::class.java)
            intent.putExtra("title", title)
            intent.putExtra("img", img)
            context.startActivity(intent)
        }
    }

    override fun getDataBinding(binding: ViewDataBinding?) {
        this.binding = binding as ActivityTempImgBinding
    }

    override fun bindComponent(appComponent: AppComponent?) {
    }

    override fun initView(view: View?) {
        binding.tbUserManage.setTitle(intent.getStringExtra("title"))
        binding.tbUserManage.bindActivity(this)
        binding.iv.setImageResource(intent.getIntExtra("img", -1))
    }

    override fun initData() {
    }

    override fun getLayoutID(): Int = R.layout.activity_temp_img
}