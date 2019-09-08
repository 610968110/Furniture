package com.furniture.ui.fragment.main

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.furniture.R
import com.furniture.adapter.ShequAdapter
import com.furniture.base.BaseFragment
import com.furniture.bean.ActionBean
import com.furniture.databinding.FragmentCommunityBinding
import com.furniture.injector.components.ActivityComponent
import com.furniture.task.ActionClick
import com.furniture.ui.activity.TempImgActivity
import lbx.xtoollib.XTools

/**
 * .  ┏┓　　　┏┓
 * .┏┛┻━━━┛┻┓
 * .┃　　　　　　　┃
 * .┃　　　━　　　┃
 * .┃　┳┛　┗┳　┃
 * .┃　　　　　　　┃
 * .┃　　　┻　　　┃
 * .┃　　　　　　　┃
 * .┗━┓　　　┏━┛
 * .    ┃　　　┃        神兽保佑
 * .    ┃　　　┃          代码无BUG!
 * .    ┃　　　┗━━━┓
 * .    ┃　　　　　　　┣┓
 * .    ┃　　　　　　　┏┛
 * .    ┗┓┓┏━┳┓┏┛
 * .      ┃┫┫　┃┫┫
 * .      ┗┻┛　┗┻┛
 *
 * @author lbx
 * @date 2018/8/24.
 * 主页三个tab_2 社区
 */
class CommunityFragment : BaseFragment() {

    private lateinit var binding: FragmentCommunityBinding

    companion object {
        fun newInstance(): CommunityFragment {
            val args = Bundle()
            val fragment = CommunityFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_community
    }

    override fun bindComponent(activityComponent: ActivityComponent) {
//        mFragmentComponent = DaggerFragmentComponent.builder()
//                .activityComponent(activityComponent)
//                .fragmentModule(FragmentModule())
//                .build()
//        mFragmentComponent.inject(this)
    }

    override fun getDataBinding(binding: ViewDataBinding) {
        this.binding = binding as FragmentCommunityBinding
    }

    override fun initView(view: View) {
        binding.rv1.layoutManager = GridLayoutManager(context, 5)
    }

    override fun initData() {
        val dimen = XTools.WindowUtil().px2dip(140).toInt()
        binding.rv1.adapter = ShequAdapter(context, getData1(), dimen)
        binding.rv2.init("管家服务", ShequAdapter(context, getData2(), dimen))
        binding.rv3.init("生活服务", ShequAdapter(context, getData3(), dimen))
        binding.rv4.init("健康服务", ShequAdapter(context, getData4(), dimen))
        binding.rv5.init("社区健康", ShequAdapter(context, getData5(), dimen))
        binding.rv6.init("邻里之间", ShequAdapter(context, getData6(), dimen))
    }


    private fun getData1(): List<ActionBean> {
        return listOf(
                ActionBean("智能开门", "", "", R.drawable.tu_zhinengkaimen, object : ActionClick() {
                    override fun actionClick(isLongClick: Boolean) {
                        super.actionClick(isLongClick)
                        TempImgActivity.start(context, "智能开门", R.drawable.img_zhinengkaimen)
                    }
                }),
                ActionBean("呼叫物业", "", "", R.drawable.tu_hujiaowuye, object : ActionClick() {
                    override fun actionClick(isLongClick: Boolean) {
                        super.actionClick(isLongClick)
                        TempImgActivity.start(context, "呼叫物业", R.drawable.img_hujiaowuye)
                    }
                }),
                ActionBean("物业公告", "", "", R.drawable.tu_wuyegonggao, object : ActionClick() {
                    override fun actionClick(isLongClick: Boolean) {
                        super.actionClick(isLongClick)
                        TempImgActivity.start(context, "物业公告", R.drawable.img_wuyegonggao)
                    }
                }),
                ActionBean("缴费", "", "", R.drawable.tu_jiaofei, object : ActionClick() {
                    override fun actionClick(isLongClick: Boolean) {
                        super.actionClick(isLongClick)
                        TempImgActivity.start(context, "缴费", R.drawable.img_jiaofei)
                    }
                }),
                ActionBean("报修", "", "", R.drawable.tu_baoxiu, object : ActionClick() {
                    override fun actionClick(isLongClick: Boolean) {
                        super.actionClick(isLongClick)
                        TempImgActivity.start(context, "报修", R.drawable.img_baoxiu)
                    }
                })
        )
    }

    private fun getData2(): List<ActionBean> {
        return listOf(
                ActionBean("收房验房", "", "", R.drawable.icon_yanfang, null),
                ActionBean("呼叫物业", "", "", R.drawable.icon_callwy, null),
                ActionBean("物业公告", "", "", R.drawable.icon_rectanglecopy_, null),
                ActionBean("缴费", "", "", R.drawable.icon_jiaofei, null),
                ActionBean("报修", "", "", R.drawable.icon_baoxiu, null),
                ActionBean("投诉", "", "", R.drawable.icon_tousu, null),
                ActionBean("智能开门", "", "", R.drawable.icon_zhinengkaimen, null),
                ActionBean("访客", "", "", R.drawable.icon_fangke, null),
                ActionBean("停车服务", "", "", R.drawable.icon_tingche, object : ActionClick() {
                    override fun actionClick(isLongClick: Boolean) {
                        super.actionClick(isLongClick)
                        TempImgActivity.start(context, "停车服务", R.drawable.img_tingchefuwu)
                    }
                }),
                ActionBean("实时监控", "", "", R.drawable.icon_jiankong, null)
        )
    }

    private fun getData3(): List<ActionBean> {
        return listOf(
                ActionBean("房屋租售", "", "", R.drawable.icon_fangwuzushou, object : ActionClick() {
                    override fun actionClick(isLongClick: Boolean) {
                        super .actionClick(isLongClick)
                        TempImgActivity.start(context, "房屋租售", R.drawable.img_fangwuzushou)
                    }
                }),
                ActionBean("二手市场", "", "", R.drawable.icon_ershaoshichang, null),
                ActionBean("便民查询", "", "", R.drawable.icon_bianminchaxun, null),
                ActionBean("生活缴费", "", "", R.drawable.icon_shenghuojiaofei, null),
                ActionBean("家政保洁", "", "", R.drawable.icon_jiazhengfuwu, null)
        )
    }

    private fun getData4(): List<ActionBean> {
        return listOf(
                ActionBean("健康管家", "", "", R.drawable.icon_jiankangguanjia, null),
                ActionBean("送药上门", "", "", R.drawable.icon_songyaoshangmen, null),
                ActionBean("体验套餐", "", "", R.drawable.icon_tijiantaocan, null),
                ActionBean("健康小屋", "", "", R.drawable.icon_jiankangxiaowu, null)
        )
    }

    private fun getData5(): List<ActionBean> {
        return listOf(
                ActionBean("社区酒店", "", "", R.drawable.icon_shequshangdian, null),
                ActionBean("社区拼团", "", "", R.drawable.icon_shequpintuan, null)
        )
    }

    private fun getData6(): List<ActionBean> {
        return listOf(
                ActionBean("社交圈", "", "", R.drawable.icon_shejiaoquan, null)
        )
    }
}
