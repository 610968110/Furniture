package com.furniture.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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
 * @date 2018/10/15.
 */

public class DescView extends FrameLayout {

    private TextView mContentView;
    private TextView mSymbolView;
    private ImageView mImageView;
    private TextView mNameView;

    public DescView(@NonNull Context context) {
        this(context, null);
    }

    public DescView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DescView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = XTools.UiUtil().inflate(R.layout.view_desc);
        addView(view);
        mContentView = view.findViewById(R.id.tv_num);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DescViewAttrs);
        String string = a.getString(R.styleable.DescViewAttrs_DescViewText);
        String symbol = a.getString(R.styleable.DescViewAttrs_DescViewSymbol);
        a.recycle();
        mNameView = view.findViewById(R.id.tv_desc);
        mImageView = view.findViewById(R.id.iv_img);
        mSymbolView = view.findViewById(R.id.tv_symbol);
        mNameView.setText(string);
        if (!TextUtils.isEmpty(symbol)) {
            mSymbolView.setText(symbol);
        }
    }

    public void setContentText(String text) {
        mContentView.setText(text);
    }

    public void setImg(@DrawableRes int img) {
        mImageView.setImageResource(img);
    }

    public void setSymbolText(String text) {
        mSymbolView.setText(text);
    }

    public void setName(String text) {
        mNameView.setText(text);
    }
}
