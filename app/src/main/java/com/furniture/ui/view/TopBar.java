package com.furniture.ui.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

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
 */

public class TopBar extends FrameLayout {

    private TextView textView, rightTextView;
    private ImageView imageView;

    public TopBar(@NonNull Context context) {
        this(context, null);
    }

    public TopBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopBar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = XTools.UiUtil().inflate(R.layout.layout_top_bar);
        addView(view);
        textView = view.findViewById(R.id.tv_bar);
        imageView = view.findViewById(R.id.iv_bar);
        rightTextView = view.findViewById(R.id.tv_right);
        view.setBackgroundColor(Color.WHITE);
    }

    public void bindActivity(Activity activity) {
        imageView.setVisibility(VISIBLE);
        imageView.setOnClickListener(v -> activity.finish());
    }

    public void setTitle(String s) {
        textView.setText(s);
    }

    public void setRightTextViewAction(String text, OnClickListener listener) {
        setRightTextViewAction(text, R.color.Black, listener);
    }

    public void setRightTextViewAction(String text, @ColorRes int textColor, OnClickListener listener) {
        rightTextView.setVisibility(VISIBLE);
        rightTextView.setText(text);
        rightTextView.setTextColor(XTools.ResUtil().getColor(textColor));
        rightTextView.setOnClickListener(listener);
    }
}
