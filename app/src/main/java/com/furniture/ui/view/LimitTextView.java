package com.furniture.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.furniture.R;
import com.furniture.bean.json.LimitResult;

import java.util.List;

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
 * @date 2018/8/24.
 * 限行的view
 */

public class LimitTextView extends FrameLayout {


    private TextView textView1;
    private TextView textView2;
    private TextView textView3;

    public LimitTextView(Context context) {
        this(context, null);
    }

    public LimitTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LimitTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LimitTextViewAttrs);
        int color = a.getColor(R.styleable.LimitTextViewAttrs_smallTextColor, Color.BLACK);
        int bigColor = a.getColor(R.styleable.LimitTextViewAttrs_bigTextColor, Color.BLACK);
        float andSize = a.getDimension(R.styleable.LimitTextViewAttrs_smallTextSize, 10);
        float bigSize = a.getDimension(R.styleable.LimitTextViewAttrs_bigTextSize, 10);
        a.recycle();
        View view = XTools.UiUtil().inflate(R.layout.view_limit);
        addView(view);

        textView1 = view.findViewById(R.id.tv_1);
        textView2 = view.findViewById(R.id.tv_2);
        textView3 = view.findViewById(R.id.tv_3);

        textView2.setTextColor(color);
        textView2.setTextSize(TypedValue.COMPLEX_UNIT_PX, andSize);

        textView1.setTextColor(bigColor);
        textView3.setTextColor(bigColor);
        textView1.setTextSize(TypedValue.COMPLEX_UNIT_PX, bigSize);
        textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, bigSize);
    }

    public void setLimit(String s, String s1) {
        textView1.setText(s);
        textView3.setText(s1);
    }

    @BindingAdapter("limit")
    public static void limit(LimitTextView limitTextView, LimitResult limitResult) {
        try {
            List<Integer> xxweihao = limitResult.getResult().getXxweihao();
            limitTextView.setLimit(xxweihao.get(0) + "", xxweihao.get(1) + "");
        } catch (Exception ignored) {
        }
    }

}
