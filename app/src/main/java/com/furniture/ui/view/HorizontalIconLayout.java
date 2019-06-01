package com.furniture.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.furniture.R;

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
 * @date 2018/12/17.
 */

public class HorizontalIconLayout extends LinearLayout {

    private ImageView mImageView;
    private TextView mTextView;

    public HorizontalIconLayout(@NonNull Context context) {
        this(context, null);
    }

    public HorizontalIconLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalIconLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HorizontalIconLayoutAttrs);
        Drawable drawable = a.getDrawable(R.styleable
                .HorizontalIconLayoutAttrs_HorizontalIconLayoutImg);
        String text = a.getString(R.styleable.HorizontalIconLayoutAttrs_HorizontalIconLayoutText);
        a.recycle();

        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setBackgroundColor(Color.WHITE);

        mImageView = new ImageView(context);
        mTextView = new TextView(context);
        mTextView.setTextColor(Color.BLACK);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        if (!TextUtils.isEmpty(text)) {
            mTextView.setText(text);
        }

        if (drawable != null) {
            mImageView.setImageDrawable(drawable);
        } else {
            mImageView.setVisibility(GONE);
        }

        addView(mImageView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mTextView);
        MarginLayoutParams params = (MarginLayoutParams) mImageView.getLayoutParams();
        params.leftMargin = params.rightMargin = 50;
    }
}
