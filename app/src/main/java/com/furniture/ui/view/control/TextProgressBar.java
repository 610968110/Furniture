package com.furniture.ui.view.control;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.furniture.R;
import com.jakewharton.rxbinding2.widget.RxSeekBar;
import com.jakewharton.rxbinding2.widget.SeekBarStopChangeEvent;

import java.util.ArrayList;
import java.util.List;

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

public class TextProgressBar extends FrameLayout {

    private LinearLayout linearLayout;
    protected SeekBar seekBar;
    private List<View> mTextViewList;

    public TextProgressBar(@NonNull Context context) {
        this(context, null);
    }

    public TextProgressBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextProgressBar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTextViewList = new ArrayList<>();
        View view = XTools.UiUtil().inflate(R.layout.view_text_pb);
        addView(view);
        linearLayout = view.findViewById(R.id.ll_control);
        seekBar = view.findViewById(R.id.sb_control);
        setSeekBarButton(R.drawable.yanse_anniu);
    }

    public void setSeekBarButton(@DrawableRes int res) {
        Drawable drawable = XTools.ResUtil().getDrawable(res);
        seekBar.setThumb(drawable);
        seekBar.setThumbOffset(drawable.getIntrinsicWidth() / 2);
    }

    public void setOnSeekBarChangeListener(OnSeekBarChangeListener listener) {
        RxSeekBar.changeEvents(seekBar)
                .subscribe(seekBarChangeEvent -> {
                    if (seekBarChangeEvent instanceof SeekBarStopChangeEvent) {
                        if (listener != null) {
                            listener.onProgressChanged(seekBar.getProgress());
                        }
                    }
                });
//        RxSeekBar.userChanges(seekBar)
//                .debounce(500, TimeUnit.MILLISECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(integer -> {
//                    if (listener != null) {
//                        listener.onProgressChanged(integer);
//                    }
//                });
    }

    public void setThumb(@DrawableRes int res) {
        seekBar.setThumb(XTools.ResUtil().getDrawable(res));
    }

    public int getMax() {
        return seekBar.getMax();
    }

    public int getItemCount() {
        return linearLayout.getChildCount();
    }

    public void setProgress(int progress) {
        if (progress < 0) {
            progress = 0;
        }
        seekBar.setProgress(progress);
    }

    public void setMax(int progress) {
        seekBar.setMax(progress);
    }

    public void setTexts(String... strings) {
        mTextViewList.clear();
        if (linearLayout.getChildCount() > 0) {
            linearLayout.removeAllViews();
        }
        if (strings != null) {
            int length = strings.length;
            for (int i = 0; i < length; i++) {
                View textView = getChildView(strings[i]);
                mTextViewList.add(textView);
                linearLayout.addView(textView, new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                if (i != length - 1) {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, 1);
                    params.weight = 1;
                    linearLayout.addView(new View(getContext()), params);
                }
            }
        }
    }

    protected View getChildView(String s) {
        return getNormalTextView(s);
    }

    @NonNull
    protected TextView getNormalTextView(String s) {
        TextView textView = new TextView(getContext());
        textView.setText(s);
        textView.setTextColor(Color.parseColor("#545454"));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    public interface OnSeekBarChangeListener {
        void onProgressChanged(int progress);
    }

    public List<View> getTopViewList() {
        return mTextViewList;
    }
}
