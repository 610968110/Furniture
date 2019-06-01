package com.furniture.ui.view;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.furniture.R;
import com.furniture.bean.json.WeatherResult;

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

public class TempLayout extends FrameLayout {

    private TemperatureTextView textView;
    private TextView textViewT;

    public TempLayout(@NonNull Context context) {
        this(context, null);
    }

    public TempLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TempLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = XTools.UiUtil().inflate(R.layout.layout_temp);
        addView(view);
        textView = view.findViewById(R.id.tv_temp);
        textViewT = view.findViewById(R.id.tv_temp_t);
    }

    public void setTemp(String temp) {
        textView.setText(temp);
    }

    public void setTextSize(float big, float small) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, big);
        textViewT.setTextSize(TypedValue.COMPLEX_UNIT_PX, small);
    }

    @BindingAdapter("weather")
    public static void weather(TempLayout tempLayout, WeatherResult weatherResult) {
        try {
            tempLayout.setTemp(weatherResult.getNowTemp());
        } catch (Exception ignored) {
        }
    }
}
