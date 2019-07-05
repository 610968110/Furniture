package com.furniture.ui.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

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

public class EnvironmentView extends FrameLayout {

    private TempLayout tv;
    private HLayout hv;
    private PmLayout pv;
    private CO2Layout cv;
    private HCHOLayout hcv;
    private int viewCount = 5;
    private View view;

    public EnvironmentView(@NonNull Context context) {
        this(context, null);
    }

    public EnvironmentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EnvironmentView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = XTools.UiUtil().inflate(R.layout.layout_environment);
        addView(view);
        tv = view.findViewById(R.id.tl_temp);
        hv = view.findViewById(R.id.tl_h);
        pv = view.findViewById(R.id.pl_tvoc);
        cv = view.findViewById(R.id.pl_co2);
        hcv = view.findViewById(R.id.pl_hcho);

        hcv.setVisibility(GONE);
        float big = XTools.ResUtil().getDimen(R.dimen.environment_big_text);
        float small = XTools.ResUtil().getDimen(R.dimen.environment_small_text);
        tv.setTextSize(big, small);
        hv.setTextSize(big, small);
        pv.setTextSize(big, small);
        cv.setTextSize(big, small);
        hcv.setTextSize(big, small);
    }

    public int getDimin() {
        int d = 0;
        ViewGroup view = (ViewGroup) this.view;
        for (int i = 0; i < view.getChildCount(); i++) {
            View childAt = view.getChildAt(i);
            if (childAt.getVisibility() == View.VISIBLE) {
                d += childAt.getMeasuredHeight();
            }
        }
        return d;
    }

    public void setTemp(String temp) {
        tv.setTemp(temp);
    }

    public void setH(String h) {
        hv.setH(h);
    }

    public void setPM(String pm) {
        pv.setPM(pm);
    }

    public void setCO2(String co2) {
        cv.setCO2(co2);
    }

    public void setHchoViewShow() {
        view.findViewById(R.id.line_hcho).setVisibility(VISIBLE);
        hcv.setVisibility(VISIBLE);
    }

    public void setHcho(String s) {
        hcv.setHCHO(s);
    }
}
