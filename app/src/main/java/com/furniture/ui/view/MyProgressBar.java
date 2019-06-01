package com.furniture.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

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
 * @date 2018/10/16.
 */

public class MyProgressBar extends View {

    private Paint mPaint;
    private Paint mTextPaint;
    private RectF mRectF;
    private Rect mRect;
    private float mProgress;
    private static final int WIDTH = 15;
    private String mTopString = "";
    private String mBottomString = "";
    private String mCenterString = "";

    public MyProgressBar(@NonNull Context context) {
        this(context, null);
    }

    public MyProgressBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyProgressBar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRectF = new RectF();
        mRect = new Rect();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStrokeWidth(WIDTH);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mTextPaint.setColor(Color.WHITE);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyProgressBarAttrs);
        mTopString = a.getString(R.styleable.MyProgressBarAttrs_MyProgressBarTopText);
        mBottomString = a.getString(R.styleable.MyProgressBarAttrs_MyProgressBarBottomText);
        a.recycle();
        if (TextUtils.isEmpty(mTopString)) {
            mTopString = "";
        }
        if (TextUtils.isEmpty(mBottomString)) {
            mBottomString = "";
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mRectF.set(
                getPaddingStart() + WIDTH,
                getPaddingTop() + WIDTH,
                getMeasuredWidth() - getPaddingEnd() - WIDTH,
                getMeasuredHeight() - getPaddingBottom() - WIDTH);
        mRect.set(
                getPaddingStart(),
                getPaddingTop(),
                getMeasuredWidth() - getPaddingEnd(),
                getMeasuredHeight() - getPaddingBottom());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.parseColor("#acB1B6B9"));
        canvas.drawArc(mRectF, -90, 360, false, mPaint);
        mPaint.setColor(Color.parseColor("#51CBA6"));
        canvas.drawArc(mRectF, -90, mProgress, false, mPaint);
        mTextPaint.setTextSize(14);
        Point pointT = XTools.UiUtil().makeCanversTextCenterPoint(mTopString, mTextPaint, mRect);
        canvas.drawText(mTopString, pointT.x, getPaddingTop() + WIDTH  + mTextPaint.getTextSize() + 20, mTextPaint);
        Point pointB = XTools.UiUtil().makeCanversTextCenterPoint(mBottomString, mTextPaint, mRect);
        canvas.drawText(mBottomString, pointB.x, getMeasuredHeight() - getPaddingBottom() - WIDTH - mTextPaint.getTextSize() - 5, mTextPaint);
        mTextPaint.setTextSize(30);
        Point point = XTools.UiUtil().makeCanversTextCenterPoint(mCenterString, mTextPaint, mRect);
        canvas.drawText(mCenterString, point.x, point.y, mTextPaint);
    }

    public void setProgress(float progress) {
        this.mProgress = progress * 3.6F;
        invalidate();
    }

    public void setCenterString(String centerString) {
        this.mCenterString = centerString;
        invalidate();
    }
}
