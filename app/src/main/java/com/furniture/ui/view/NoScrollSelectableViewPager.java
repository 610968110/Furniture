package com.furniture.ui.view;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.util.AttributeSet;

import lbx.xtoollib.view.NoScrollViewPager;

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
 * @date 2019/3/7.
 */

public class NoScrollSelectableViewPager extends NoScrollViewPager {

    public NoScrollSelectableViewPager(Context context) {
        this(context, null);
    }

    public NoScrollSelectableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @BindingAdapter({"scrollPosition"})
    public static void scrollPosition(NoScrollSelectableViewPager pager, int pos) {
        if (pos >= 0) {
            pager.setCurrentItem(pos);
        }
    }
}
