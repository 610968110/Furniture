package com.furniture.ui.view.control;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

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
 * @date 2018/8/26.
 */

public class CircleTouchView extends FrameLayout {

    private ImageView imageView;
    private CircleSeekBar seekBar;

    public CircleTouchView(@NonNull Context context) {
        this(context, null);
    }

    public CircleTouchView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleTouchView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        int thumb = R.drawable.yanse_anniu;

        imageView = new ImageView(context);
        imageView.setImageResource(R.drawable.pic_hui);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), thumb);
        int p = bitmap.getWidth() / 2 - 5;
        imageView.setPadding(p, p, p, p);

        seekBar = new CircleSeekBar(context);
        seekBar.setProgressThumb(thumb);
        seekBar.setProgressTextColor(Color.TRANSPARENT);
        setProgressStartAngle(90);

        addView(imageView);
        addView(seekBar);
    }

    public void setImageResource(@DrawableRes int s) {
        imageView.setImageResource(s);
    }

    public void setImageDrawable(Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }

    public void setProgressStartAngle(int angle) {
        seekBar.setRotation(angle);
    }

    public float getProgressStartAngle() {
        return seekBar.getRotation();
    }

    public void setProgress(int progress) {
        seekBar.setProgress(progress);
    }

    public void setProgressEnable(boolean enable) {
        seekBar.setProgressEnable(enable);
    }

    public void setOnSeekBarChangeListener(CircleSeekBar.OnSeekBarChangeListener listener) {
        seekBar.setOnSeekBarChangeListener(listener);
    }

    public CircleSeekBar getSeekBar() {
        return seekBar;
    }
}
