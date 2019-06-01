package com.furniture.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.DimenRes;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;

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
 * @date 2018/8/24.
 */

public class TemperatureTextView extends android.support.v7.widget.AppCompatTextView {

    private String mText;
    private String temp = " ℃";
    private Paint mPaint;
    private float mTempSize;
    private float mTempMeasure;
    private Rect mTextBounds;
    private Rect mBounds;
    private boolean ignoreSymbolLength;

    public TemperatureTextView(Context context) {
        this(context, null);
    }

    public TemperatureTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TemperatureTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TemperatureTextViewAttrs);
        int color = a.getColor(R.styleable.TemperatureTextViewAttrs_smallTextColor, Color.BLACK);
        mTempSize = a.getDimension(R.styleable.TemperatureTextViewAttrs_smallTextSize, 10);
        a.recycle();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setColor(color);
        mTextBounds = new Rect();
        mBounds = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mText = getText().toString();
        mPaint.setTextSize(mTempSize);
        mTempMeasure = mPaint.measureText(temp, 0, temp.length());
        setMeasuredDimension((int) (getMeasuredWidth() + mTempMeasure), getMeasuredHeight());
        mBounds.set(getPaddingStart(), getPaddingTop(), getMeasuredWidth() - getPaddingEnd(), getMeasuredHeight() - getPaddingBottom());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        TextPaint paint = getPaint();
        float measureText = paint.measureText(mText, 0, mText.length());
        paint.getTextBounds(mText, 0, mText.length(), mTextBounds);
        int save = canvas.save();
        mPaint.setTextSize(mTempSize);
        mTempMeasure = mPaint.measureText(temp, 0, temp.length());
        canvas.translate(measureText, mTempSize + (mBounds.height() - mTextBounds.height()) / 2 - 5);
        canvas.drawText(temp, 0, temp.length(), mPaint);
        canvas.restoreToCount(save);
        if (ignoreSymbolLength) {
            setTranslationX(mTempMeasure / 2);
        }
    }

    public void setSymbol(String s) {
        temp = s;
        invalidate();
    }

    public void setSymbolColor(@ColorInt int c) {
        mPaint.setColor(c);
        invalidate();
    }

    public void setSymbolSize(float s) {
        mTempSize = s;
        invalidate();
    }

    public void setSymbolSize(@DimenRes int s) {
        mTempSize = XTools.ResUtil().getDimen(s);
        invalidate();
    }

    public void ignoreSymbolLength() {
        ignoreSymbolLength = true;
    }
}
