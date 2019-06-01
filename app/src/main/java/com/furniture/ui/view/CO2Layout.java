package com.furniture.ui.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
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

public class CO2Layout extends FrameLayout {

    private PMTextView textView;
    private TextView textViewT;

    public CO2Layout(@NonNull Context context) {
        this(context, null);
    }

    public CO2Layout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CO2Layout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = XTools.UiUtil().inflate(R.layout.layout_co2);
        addView(view);
        textView = view.findViewById(R.id.tv_co2);
        textViewT = view.findViewById(R.id.tv_co20);
        textView.setSymbol(" ppm");
    }

    public void setCO2(String pm) {
        textView.setText(pm);
    }

    public void setTextSize(float big, float small) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, big);
        textViewT.setTextSize(TypedValue.COMPLEX_UNIT_PX, small);
    }
}
