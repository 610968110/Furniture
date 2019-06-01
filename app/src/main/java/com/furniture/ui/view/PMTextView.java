package com.furniture.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;

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
 * @date 2018/8/24.
 */

public class PMTextView extends android.support.v7.widget.AppCompatTextView {

    private String mText;
    private String pm = " ppm";
    private Paint mPaint;
    private float mPMSize;
    private float mPMMeasure;
    private Rect mTextBounds;
    private Rect mBounds;

    public PMTextView(Context context) {
        this(context, null);
    }

    public PMTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PMTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TemperatureTextViewAttrs);
        int color = a.getColor(R.styleable.TemperatureTextViewAttrs_smallTextColor, Color.BLACK);
        mPMSize = a.getDimension(R.styleable.TemperatureTextViewAttrs_smallTextSize, 10);
        a.recycle();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setTextSize(mPMSize);
        mPaint.setColor(color);
        mTextBounds = new Rect();
        mBounds = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mText = getText().toString();
        mPMMeasure = mPaint.measureText(pm, 0, pm.length());
        setMeasuredDimension((int) (getMeasuredWidth() + mPMMeasure), getMeasuredHeight());
        mBounds.set(getPaddingStart(),
                getPaddingTop(),
                getMeasuredWidth() - getPaddingEnd(),
                getMeasuredHeight() - getPaddingBottom());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        TextPaint paint = getPaint();
        float measureText = paint.measureText(mText, 0, mText.length());
        paint.getTextBounds(mText, 0, mText.length(), mTextBounds);
        int save = canvas.save();
        canvas.translate(measureText, mBounds.height() - mPMSize / 2 - 5);
        canvas.drawText(pm, 0, pm.length(), mPaint);
        canvas.restoreToCount(save);
    }

    public void setSymbol(String s) {
        pm = s;
        invalidate();
    }
}
