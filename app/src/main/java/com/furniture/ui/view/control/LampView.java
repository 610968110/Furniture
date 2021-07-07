package com.furniture.ui.view.control;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Looper;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;

import com.furniture.R;
import com.furniture.ui.drawable.CustomCircleSeekBarDrawable;
import com.furniture.ui.view.ObtainColorImageView;

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
 * @date 2018/8/27.
 * 客厅射灯
 */

public class LampView extends HeatingView {

    private CustomCircleSeekBarDrawable drawable;
    private boolean isOpen;
    private ObtainColorImageView mObtainColorImageView;

    public LampView(@NonNull Context context) {
        this(context, null);
    }

    public LampView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LampView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        textView.setVisibility(GONE);
        CircleSeekBar seekBar = getSeekBar();
        if (seekBar != null) {
            seekBar.setLimit(60, 330);
        }
    }

    int x = 0;
    int y = 0;

    public void setOnSelectColorListener(OnColorSelectListener listener) {
        setOnSelectColorListener(R.drawable.pic_yanse, listener);
    }

    public void setOnSelectColorListener(@DrawableRes int res, OnColorSelectListener listener) {
        this.drawable = new CustomCircleSeekBarDrawable(getContext());
        getCircleTouchView().setImageDrawable(drawable);
        if (mObtainColorImageView == null) {
            mObtainColorImageView = new ObtainColorImageView(getContext());
            mObtainColorImageView.setBackgroundResource(res);
            mObtainColorImageView.setVisibility(GONE);
        }
        Looper.myQueue().addIdleHandler(() -> {
            Rect bounds = drawable.getCenterImgBounds();
            LayoutParams params = new LayoutParams(bounds.width(), bounds.height());
            params.gravity = Gravity.CENTER;
            addView(mObtainColorImageView, params);
            mObtainColorImageView.setOnTouchListener((v, event) -> {
                if (isOpen) {
                    Point centerPoint = mObtainColorImageView.getCenterPoint();
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                        case MotionEvent.ACTION_MOVE:

                            x = (int) event.getX();
                            y = (int) event.getY();

                            if (x < 0) {
                                x = 0;
                            }
                            if (x > mObtainColorImageView.getMeasuredWidth()) {
                                x = mObtainColorImageView.getMeasuredWidth();
                            }
                            if (y < 0) {
                                y = 0;
                            }
                            if (y > mObtainColorImageView.getMeasuredHeight()) {
                                y = mObtainColorImageView.getMeasuredHeight();
                            }

                            int distanceX = Math.abs(centerPoint.x - x);
                            int distanceY = Math.abs(centerPoint.y - y);
                            int distanceZ = (int) Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
                            if (distanceZ > mObtainColorImageView.getMeasuredWidth() / 2) {
                                return true;
                            }

                            mObtainColorImageView.selectPoint(x, y);
                            if (listener != null) {
                                int color = mObtainColorImageView.getColor(x, y);
                                listener.onColorSelect(color, x, y);
                            }
                            break;
                        case MotionEvent.ACTION_UP:
                            if (listener != null) {
                                int color = mObtainColorImageView.getColor(x, y);
                                listener.onColorUpSelect(color, x, y);
                            }
                            break;
                        default:
                            break;
                    }
                }
                return true;
            });
            return false;
        });
    }

    @Override
    public void setProgress(int progress) {
        float min = 60F / 360 * 100;
        if (progress < min) {
            progress = (int) min;
        }
        super.setProgress(progress);
    }

    @Override
    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
        drawable.setOpen(isOpen);
        mObtainColorImageView.setVisibility(isOpen ? VISIBLE : GONE);
    }

    public void selectPos(int x, int y) {
        mObtainColorImageView.selectPoint(x, y);
    }

    public interface OnColorSelectListener {
        void onColorSelect(int color, int x, int y);

        void onColorUpSelect(int color, int x, int y);
    }
}
