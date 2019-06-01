package com.furniture.ui.view.control;

import android.content.Context;
import android.graphics.Color;
import android.os.Looper;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.furniture.R;
import com.furniture.ui.view.TemperatureTextView;

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
 * 地暖
 */

public class HeatingView extends FrameLayout {

    protected TemperatureTextView textView;
    private CircleTouchView imageView;
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

    private int min = 0;
    private int max = 100;

    public HeatingView(@NonNull Context context) {
        this(context, null);
    }

    public HeatingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeatingView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        imageView = new CircleTouchView(context);

        textView = new TemperatureTextView(context);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, XTools.ResUtil().getDimen(R.dimen.control_center_textSize));
        textView.setTextColor(Color.WHITE);
        textView.setSymbolColor(Color.WHITE);
        textView.setSymbol("℃");
        textView.ignoreSymbolLength();
        textView.setSymbolSize(R.dimen.control_center_symbol_textSize);

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;

        addView(imageView);
        addView(textView, params);
    }

    public void setImg(@DrawableRes int nbg, @DrawableRes int sbg) {
        this.nbg = nbg;
        this.sbg = sbg;
        imageView.setImageResource(nbg);
    }

    public void setImageResource(@DrawableRes int s) {
        imageView.setImageResource(s);
    }

    public void setCenterText(String s) {
        textView.setText(s);
    }

    public void setOpen(boolean isOpen) {
        if ((sbg & nbg) != 0) {
            imageView.setImageResource(isOpen ? sbg : nbg);
        }
    }

    public void setCenterImg(@DrawableRes int s) {
        imageView.setImageResource(s);
    }

    public void setProgress(int progress) {
        Looper.myQueue().addIdleHandler(() -> {
            if (imageView != null) {
                imageView.setProgress(progress);
            }
            return false;
        });
        setCenterText(String.valueOf(progress2Real(max, min, progress)));
    }


    public void setProgressEnable(boolean enable) {
        imageView.setProgressEnable(enable);
    }

    public interface OnSeekBarChangeListener {

        void onProgressChanged(int progress);

        void onStartTrackingTouch();

        void onStopTrackingTouch();
    }

    public void setOnSeekBarProgressListener(OnSeekBarChangeListener listener) {
        imageView.setOnSeekBarChangeListener(new CircleSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(int progress) {
                setCenterText(String.valueOf(progress2Real(max, min, progress)));
                if (listener != null) {
                    listener.onProgressChanged(progress);
                }
            }

            @Override
            public void onStartTrackingTouch() {
                if (listener != null) {
                    listener.onStartTrackingTouch();
                }
            }

            @Override
            public void onStopTrackingTouch() {
                if (listener != null) {
                    listener.onStopTrackingTouch();
                }
            }
        });
    }

    public float getProgressStartAngle() {
        return imageView.getProgressStartAngle();
    }

    protected CircleTouchView getCircleTouchView() {
        return imageView;
    }

    protected CircleSeekBar getSeekBar() {
        return imageView.getSeekBar();
    }

    protected int progress2Real(int max, int min, int progress) {
        return (int) Math.ceil((min + (max - min) * 1.0F * progress / 100));
    }

    protected int real2Progress(int max, int min, int progress) {
        return (int) Math.ceil(((progress - min) * 1.0F / (max - min) * 100));
    }

    public int getMin() {
        return min;
    }

    public void set(int max, int min) {
        this.min = min;
        this.max = max;
    }

    public int getMax() {
        return max;
    }
}
