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
import com.furniture.bean.json.PM25Result;

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

public class PmLayout extends FrameLayout {

    protected PMTextView textView;
    protected TextView textViewT;

    public PmLayout(@NonNull Context context) {
        this(context, null);
    }

    public PmLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PmLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = XTools.UiUtil().inflate(R.layout.layout_pm);
        addView(view);
        textView = view.findViewById(R.id.tv_pm);
        textViewT = view.findViewById(R.id.tv_pm0);
    }

    public void setPM(String pm) {
        textView.setText(pm);
    }

    public void setTextSize(float big, float small) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, big);
        textViewT.setTextSize(TypedValue.COMPLEX_UNIT_PX, small);
    }

    @BindingAdapter("pm")
    public static void pm(PmLayout pmLayout, PM25Result pm25Result) {
        try {
            pmLayout.setPM(pm25Result.getResult().get(0).get_$PM25130());
        } catch (Exception ignored) {
        }
    }
}
