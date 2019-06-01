package com.furniture.ui.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
 * @date 2018/8/28.
 */

public class CustomCircleSeekBarDrawable extends Drawable implements Drawable.Callback {

    private Paint mPaint;
    private RectF mBounds;
    private boolean isOpen;
    private int mCircleRadius;
    private static final float START_RADIAN = -210F;
    private static final float RADIAN = 270F;
    private static final float CIRCLE_WIDTH = 7;
    private Bitmap mBitmap;
    private Context mContext;
    private Rect mCenterImgBounds;

    public CustomCircleSeekBarDrawable(Context context) {
        this.mContext = context;
        setCallback(this);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mBounds = new RectF();
        mCenterImgBounds = new Rect();
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        mBounds.set(bounds);
        mCircleRadius = (int) (mBounds.width() / 2 - mBounds.width() / 8);
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.pic_yanse);
        mBitmap = XTools.BitmapUtil().zoomBmp(bitmap, mCircleRadius * 2, mCircleRadius * 2);
        bitmap.recycle();
        int left = (int) (mBounds.width() / 2 - mBitmap.getWidth() / 2);
        int top = (int) (mBounds.height() / 2 - mBitmap.getHeight() / 2);
        mCenterImgBounds.set(left, top, left + mBitmap.getWidth(), top + mBitmap.getHeight());
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (isOpen) {
            drawOpen(canvas);
        } else {
            drawClose(canvas);
        }
    }

    private void drawOpen(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(CIRCLE_WIDTH);
        mPaint.setColor(Color.parseColor("#80D7D9"));
        canvas.drawArc(mBounds, START_RADIAN, RADIAN / 2, false, mPaint);
        mPaint.setColor(Color.parseColor("#F49918"));
        canvas.drawArc(mBounds, START_RADIAN + RADIAN / 2, RADIAN / 2, false, mPaint);
    }

    private void drawClose(Canvas canvas) {
        mPaint.setColor(Color.parseColor("#CCCCCC"));
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(mBounds.centerX(), mBounds.centerY(), mCircleRadius, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(CIRCLE_WIDTH);
        canvas.drawArc(mBounds, START_RADIAN, RADIAN, false, mPaint);
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }

    @Override
    public void invalidateDrawable(@NonNull Drawable who) {

    }

    @Override
    public void scheduleDrawable(@NonNull Drawable who, @NonNull Runnable what, long when) {

    }

    @Override
    public void unscheduleDrawable(@NonNull Drawable who, @NonNull Runnable what) {

    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
        invalidateSelf();
    }

    public Rect getCenterImgBounds() {
        return mCenterImgBounds;
    }
}
