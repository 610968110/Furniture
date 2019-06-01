package com.furniture.ui.view.control;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.furniture.R;

import lbx.xtoollib.XTools;

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
 * @date 2018/8/25.
 * 窗帘
 */

public class MainControlView extends FrameLayout {

    private ImageView imageView;
    private ImageView imageViewCenter;
    /**
     * 关闭
     */
    private
    @DrawableRes
    int nbg;
    /**
     * 开启
     */
    private
    @DrawableRes
    int sbg;
    /**
     * 中间
     */
    private
    @DrawableRes
    int src;

    public MainControlView(@NonNull Context context) {
        this(context, null);
    }

    public MainControlView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainControlView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        imageView = new ImageView(context);
        imageViewCenter = new ImageView(context);

        int dimen = XTools.ResUtil().getDimen(R.dimen.control_center_img);
        LayoutParams params = new LayoutParams(dimen, dimen);
        params.gravity = Gravity.CENTER;

        addView(imageView);
        addView(imageViewCenter, params);
    }

    public void setImg(@DrawableRes int nbg, @DrawableRes int sbg, @DrawableRes int src) {
        this.nbg = nbg;
        this.sbg = sbg;
        this.src = src;
        imageView.setImageResource(nbg);
        imageViewCenter.setImageResource(src);
    }

    public void setOpen(boolean isOpen) {
        imageView.setImageResource(isOpen ? sbg : nbg);
    }

}
