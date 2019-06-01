package com.furniture.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.Gravity;

import com.furniture.R;
import com.furniture.utils.DrawableUtil;

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
 * @date 2018/8/29.
 */

public class ImgEditText extends android.support.v7.widget.AppCompatEditText {

    private Bitmap mBitmap;
    private int mOffset;
    private Paint mPaint;

    public ImgEditText(Context context) {
        super(context);
        init(context, null);
    }

    public ImgEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ImgEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ImgEditTextAttrs);
        boolean hasValue = a.hasValue(R.styleable.ImgEditTextAttrs_left_icon);
        Bitmap bitmap = hasValue ?
                DrawableUtil.drawableToBitmap(a.getDrawable(R.styleable.ImgEditTextAttrs_left_icon)) :
                BitmapFactory.decodeResource(getContext().getResources(), R.drawable.icon_shoujihao);
        a.recycle();
        init(bitmap);
    }

    private void init(Bitmap bitmap) {
        float sc = XTools.WindowUtil().dip2px(15) * 1.0F / bitmap.getWidth();
        mOffset = XTools.WindowUtil().dip2px(14);
        Matrix matrix = new Matrix();
        matrix.setScale(sc, sc);
        mBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        bitmap.recycle();
        setBackgroundColor(Color.WHITE);
        setFocusable(false);
        setFocusableInTouchMode(false);
        setMaxLines(1);
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        setOnClickListener(v -> XTools.SoftInputUtil().showSoftInput(this));
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(mOffset + 20 + mBitmap.getWidth(), 0, 0, 0);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setColor(Color.parseColor("#cccccc"));
        mPaint.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int offsetY = getMeasuredHeight() / 2 - mBitmap.getWidth() / 2;
        canvas.drawBitmap(mBitmap, mOffset, offsetY, null);
        canvas.drawLine(20, getMeasuredHeight() - 2, getMeasuredWidth() - 20, getMeasuredHeight() - 2, mPaint);
        super.onDraw(canvas);
    }
}
