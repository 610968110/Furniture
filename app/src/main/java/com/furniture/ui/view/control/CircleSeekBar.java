package com.furniture.ui.view.control;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

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
 * @date 2018/8/26
 */
public class CircleSeekBar extends android.support.v7.widget.AppCompatImageView {

    private Context mContext = null;

    private Drawable mThumbDrawable = null;
    private int mThumbHeight = 0;
    private int mThumbWidth = 0;
    private int[] mThumbNormal = null;
    private int[] mThumbPressed = null;

    private int mSeekBarMax = 0;
    private Paint mSeekBarBackgroundPaint = null;
    private Paint mSeekbarProgressPaint = null;
    private RectF mArcRectF = null;

    private boolean mIsShowProgressText = false;
    private Paint mProgressTextPaint = null;
    private int mProgressTextSize = 0;

    private int mViewHeight = 0;
    private int mViewWidth = 0;
    private int mSeekBarSize = 0;
    private int mSeekBarRadius = 0;
    private int mSeekBarCenterX = 0;
    private int mSeekBarCenterY = 0;
    private float mThumbLeft = 0;
    private float mThumbTop = 0;

    private float mSeekBarDegree = 0;
    private int mCurrentProgress = 0;

    private OnSeekBarChangeListener mOnSeekBarChangeListener = null;
    private boolean progressEnable;

    private int minProgressAngle = -1;
    private int maxProgressAngle = -1;

    public void setLimit(int minProgressAngle, int maxProgressAngle) {
        this.minProgressAngle = minProgressAngle;
        this.maxProgressAngle = maxProgressAngle;
    }

    public void setProgressEnable(boolean progressEnable) {
        this.progressEnable = progressEnable;
    }

    public interface OnSeekBarChangeListener {

        void onProgressChanged(int progress);

        void onStartTrackingTouch();

        void onStopTrackingTouch();
    }

    public CircleSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        initViewAttrs(attrs);
        mArcRectF = new RectF();
    }

    public CircleSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initViewAttrs(attrs);
        mArcRectF = new RectF();
    }

    public CircleSeekBar(Context context) {
        super(context);
        mContext = context;
        initViewDefault();
        mArcRectF = new RectF();
    }

    private void initViewAttrs(AttributeSet attrs) {
        TypedArray localTypedArray = mContext.obtainStyledAttributes(attrs, R.styleable.CircleSeekBar);

        //thumb的属性是使用android:thumb属性进行设置的
        //返回的Drawable为一个StateListDrawable类型，即可以实现选中效果的drawable list
        //mThumbNormal和mThumbPressed则是用于设置不同状态的效果，当点击thumb时设置mThumbPressed，否则设置mThumbNormal
        mThumbDrawable = localTypedArray.getDrawable(R.styleable.CircleSeekBar_android_thumb);
        mThumbWidth = mThumbDrawable.getIntrinsicWidth();
        mThumbHeight = mThumbDrawable.getIntrinsicHeight();

        mThumbNormal = new int[]{-android.R.attr.state_focused, -android.R.attr.state_pressed,
                -android.R.attr.state_selected, -android.R.attr.state_checked};
        mThumbPressed = new int[]{android.R.attr.state_focused, android.R.attr.state_pressed,
                android.R.attr.state_selected, android.R.attr.state_checked};

        float progressWidth = localTypedArray.getDimension(R.styleable.CircleSeekBar_progress_width, 5);
        int progressBackgroundColor = localTypedArray.getColor(R.styleable.CircleSeekBar_progress_background, Color.TRANSPARENT);
        int progressFrontColor = localTypedArray.getColor(R.styleable.CircleSeekBar_progress_front, Color.TRANSPARENT);
        mSeekBarMax = localTypedArray.getInteger(R.styleable.CircleSeekBar_progress_max, 100);

        mSeekbarProgressPaint = new Paint();
        mSeekBarBackgroundPaint = new Paint();

        mSeekbarProgressPaint.setColor(progressFrontColor);
        mSeekBarBackgroundPaint.setColor(progressBackgroundColor);

        mSeekbarProgressPaint.setAntiAlias(true);
        mSeekBarBackgroundPaint.setAntiAlias(true);

        mSeekbarProgressPaint.setStyle(Paint.Style.STROKE);
        mSeekBarBackgroundPaint.setStyle(Paint.Style.STROKE);

        mSeekbarProgressPaint.setStrokeWidth(progressWidth);
        mSeekBarBackgroundPaint.setStrokeWidth(progressWidth);

        mIsShowProgressText = localTypedArray.getBoolean(R.styleable.CircleSeekBar_show_progress_text, false);
        int progressTextStroke = (int) localTypedArray.getDimension(R.styleable.CircleSeekBar_progress_text_stroke_width, 5);
        int progressTextColor = localTypedArray.getColor(R.styleable.CircleSeekBar_progress_text_color, Color.GREEN);
        mProgressTextSize = (int) localTypedArray.getDimension(R.styleable.CircleSeekBar_progress_text_size, 50);

        mProgressTextPaint = new Paint();
        mProgressTextPaint.setColor(progressTextColor);
        mProgressTextPaint.setAntiAlias(true);
        mProgressTextPaint.setStrokeWidth(progressTextStroke);
        mProgressTextPaint.setTextSize(mProgressTextSize);

        localTypedArray.recycle();
    }

    private void initViewDefault() {
        mThumbDrawable = null;
        mThumbWidth = 0;
        mThumbHeight = 0;

        mThumbNormal = new int[]{-android.R.attr.state_focused, -android.R.attr.state_pressed,
                -android.R.attr.state_selected, -android.R.attr.state_checked};
        mThumbPressed = new int[]{android.R.attr.state_focused, android.R.attr.state_pressed,
                android.R.attr.state_selected, android.R.attr.state_checked};

        float progressWidth = 5;
        int progressBackgroundColor = Color.TRANSPARENT;
        int progressFrontColor = Color.TRANSPARENT;
        mSeekBarMax = 100;

        mSeekbarProgressPaint = new Paint();
        mSeekBarBackgroundPaint = new Paint();

        mSeekbarProgressPaint.setColor(progressFrontColor);
        mSeekBarBackgroundPaint.setColor(progressBackgroundColor);

        mSeekbarProgressPaint.setAntiAlias(true);
        mSeekBarBackgroundPaint.setAntiAlias(true);

        mSeekbarProgressPaint.setStyle(Paint.Style.STROKE);
        mSeekBarBackgroundPaint.setStyle(Paint.Style.STROKE);

        mSeekbarProgressPaint.setStrokeWidth(progressWidth);
        mSeekBarBackgroundPaint.setStrokeWidth(progressWidth);

        mIsShowProgressText = false;
        int progressTextStroke = 5;
        int progressTextColor = Color.GREEN;
        mProgressTextSize = 50;

        mProgressTextPaint = new Paint();
        mProgressTextPaint.setColor(progressTextColor);
        mProgressTextPaint.setAntiAlias(true);
        mProgressTextPaint.setStrokeWidth(progressTextStroke);
        mProgressTextPaint.setTextSize(mProgressTextSize);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setPadding(0,0,0,0);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = getWidth();
        mViewHeight = getHeight();

        mSeekBarSize = mViewWidth > mViewHeight ? mViewHeight : mViewWidth;

        mSeekBarCenterX = mViewWidth / 2;
        mSeekBarCenterY = mViewHeight / 2;

        mSeekBarRadius = mSeekBarSize / 2 - mThumbWidth / 2;

        int left = mSeekBarCenterX - mSeekBarRadius;
        int right = mSeekBarCenterX + mSeekBarRadius;
        int top = mSeekBarCenterY - mSeekBarRadius;
        int bottom = mSeekBarCenterY + mSeekBarRadius;
        mArcRectF.set(left, top, right, bottom);

        // 起始位置，三点钟方向
        setThumbPosition(Math.toRadians(mSeekBarDegree));
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mSeekBarCenterX, mSeekBarCenterY, mSeekBarRadius,
                mSeekBarBackgroundPaint);
        canvas.drawArc(this.mArcRectF, 0.0F, mSeekBarDegree, false, mSeekbarProgressPaint);
        int save = canvas.save();
        drawThumbBitmap(canvas);
        drawProgressText(canvas);
    }

    private void drawThumbBitmap(Canvas canvas) {
        if (null != mThumbDrawable) {
            mThumbDrawable.setBounds((int) mThumbLeft, (int) mThumbTop,
                    (int) (mThumbLeft + mThumbWidth), (int) (mThumbTop + mThumbHeight));
            mThumbDrawable.draw(canvas);
        }
    }

    private void drawProgressText(Canvas canvas) {
        if (mIsShowProgressText) {
            float textWidth = mProgressTextPaint.measureText("" + mCurrentProgress);
            canvas.drawText("" + mCurrentProgress, mSeekBarCenterX - textWidth / 2, mSeekBarCenterY
                    + mProgressTextSize / 2, mProgressTextPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!progressEnable) {
            return true;
        }
        float eventX = event.getX();
        float eventY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (null != mOnSeekBarChangeListener) {
                    mOnSeekBarChangeListener.onStartTrackingTouch();
                }
                seekTo(eventX, eventY, false);
                break;

            case MotionEvent.ACTION_MOVE:
                seekTo(eventX, eventY, false);
                break;

            case MotionEvent.ACTION_UP:
                if (null != mOnSeekBarChangeListener) {
                    mOnSeekBarChangeListener.onStopTrackingTouch();
                }
                seekTo(eventX, eventY, true);
                break;
            default:
                break;
        }
        return true;
    }

    private void seekTo(float eventX, float eventY, boolean isUp) {
        if (isPointOnThumb(eventX, eventY) && !isUp) {
            if (null != mThumbDrawable) {
                mThumbDrawable.setState(mThumbPressed);
            }
            //弧度
            double radian = Math.atan2(eventY - mSeekBarCenterY, eventX - mSeekBarCenterX);

            /*
             * 由于atan2返回的值为[-pi,pi]
             * 因此需要将弧度值转换一下，使得区间为[0,2*pi]
             */
            if (radian < 0) {
                radian = radian + 2 * Math.PI;
            }
            //角度
            double angle = Math.abs(radian * 180 / Math.PI);
            if (angle < minProgressAngle || angle > maxProgressAngle && minProgressAngle != -1 && maxProgressAngle != -1) {
                return;
            }

            setThumbPosition(radian);

            mSeekBarDegree = (float) Math.round(Math.toDegrees(radian));
            mCurrentProgress = (int) (mSeekBarMax * mSeekBarDegree / 360);
            if (null != mOnSeekBarChangeListener) {
                mOnSeekBarChangeListener.onProgressChanged(mCurrentProgress);
            }
            invalidate();
        } else {
            if (null != mThumbDrawable) {
                mThumbDrawable.setState(mThumbNormal);
            }
            invalidate();
        }
    }

    private boolean isPointOnThumb(float eventX, float eventY) {
        boolean result = false;
        double distance = Math.sqrt(Math.pow(eventX - mSeekBarCenterX, 2)
                + Math.pow(eventY - mSeekBarCenterY, 2));
        if (distance < mSeekBarSize && distance > (mSeekBarSize / 2 - mThumbWidth)) {
            result = true;
        }
        return result;
    }

    private void setThumbPosition(double radian) {
        double x = mSeekBarCenterX + mSeekBarRadius * Math.cos(radian);
        double y = mSeekBarCenterY + mSeekBarRadius * Math.sin(radian);
        mThumbLeft = (float) (x - mThumbWidth / 2);
        mThumbTop = (float) (y - mThumbHeight / 2);
    }

    /*
     * 增加set方法，用于在java代码中调用
     */
    public synchronized void setProgress(int progress) {
        if (progress > mSeekBarMax) {
            progress = mSeekBarMax;
        }
        if (progress < 0) {
            progress = 0;
        }
        mCurrentProgress = progress;
        mSeekBarDegree = (progress * 360 / mSeekBarMax);
        setThumbPosition(Math.toRadians(mSeekBarDegree));

        invalidate();
    }

    public synchronized int getProgress() {
        return mCurrentProgress;
    }

    public synchronized void setProgressMax(int max) {
        mSeekBarMax = max;
    }

    public synchronized int getProgressMax() {
        return mSeekBarMax;
    }

    public void setProgressThumb(int thumbId) {
        mThumbDrawable = mContext.getResources().getDrawable(thumbId);
        mThumbWidth = mThumbDrawable.getIntrinsicWidth();
        mThumbHeight = mThumbDrawable.getIntrinsicHeight();
    }

    public void setProgressWidth(int width) {
        mSeekbarProgressPaint.setStrokeWidth(width);
        mSeekBarBackgroundPaint.setStrokeWidth(width);
    }

    public void setProgressBackgroundColor(int color) {
        mSeekBarBackgroundPaint.setColor(color);
    }

    public void setProgressFrontColor(int color) {
        mSeekbarProgressPaint.setColor(color);
    }

    public void setProgressTextColor(int color) {
        mProgressTextPaint.setColor(color);
    }

    public void setProgressTextSize(int size) {
        mProgressTextPaint.setTextSize(size);
    }

    public void setProgressTextStrokeWidth(int width) {
        mProgressTextPaint.setStrokeWidth(width);
    }

    public void setIsShowProgressText(boolean isShow) {
        mIsShowProgressText = isShow;
    }
    
    /*
     * 增加SeekBar change的监听
     */

    public void setOnSeekBarChangeListener(OnSeekBarChangeListener onSeekBarChangeListener) {
        mOnSeekBarChangeListener = onSeekBarChangeListener;
    }
}