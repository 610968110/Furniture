package com.furniture.ui.view.control;

import android.content.Context;
import android.graphics.Color;
import android.os.Looper;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

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
 */

public class AutoTextProgressBar extends TextProgressBar {

    private int[] colors = new int[]{Color.parseColor("#9A9A9A"), Color.parseColor("#11B9BA")};
    /**
     * 当前选择的text
     */
    private int mCurrentSelect = -1;

    public AutoTextProgressBar(@NonNull Context context) {
        this(context, null);
    }

    public AutoTextProgressBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoTextProgressBar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected View getChildView(String s) {
        TextView normalTextView = getNormalTextView(s);
        normalTextView.setTextColor(colors[0]);
        normalTextView.setPadding(10, 5, 10, 5);
        CardView cardView = new CardView(getContext());
        cardView.addView(normalTextView);
        return cardView;
    }

    @Override
    public void setTexts(String... strings) {
        super.setTexts(strings);
        List<View> topViewList = getTopViewList();
        if (topViewList != null) {
            int size = topViewList.size();
            int[] pos = new int[size];
            Looper.myQueue().addIdleHandler(() -> {
                for (int i = 0; i < size; i++) {
                    View v = topViewList.get(i);
                    pos[i] = v.getLeft();
                }
                //初始化选择的文字变色位置
                if (seekBar.getProgress() > 0) {
                    int posTv = getPosFromX(size, pos, (seekBar.getWidth() * seekBar.getProgress() / 100F));
                    if (posTv != -1) {
                        selectText(topViewList, posTv);
                    }
                }
                return false;
            });
            seekBar.setOnTouchListener((v, event) -> {
                float x = event.getX();
                int posTv = getPosFromX(size, pos, x);
                if (posTv != -1) {
                    selectText(topViewList, posTv);
                }
                return false;
            });
        }
    }

    private int getPosFromX(int size, int[] pos, float x) {
        for (int i = size - 1; i >= 0; i--) {
            if (x >= pos[i]) {
                if (mCurrentSelect != i) {
                    mCurrentSelect = i;
                    return i;
                }
                return -1;
            }
        }
        return 0;
    }

    @Override
    public void setOnSeekBarChangeListener(OnSeekBarChangeListener listener) {
        super.setOnSeekBarChangeListener(listener);
        if (listener instanceof OnSeekBarChangeListenerWithText) {
            mListener = (OnSeekBarChangeListenerWithText) listener;
        }
    }

    private OnSeekBarChangeListenerWithText mListener;

    public interface OnSeekBarChangeListenerWithText extends OnSeekBarChangeListener {
        void onTextPosSelect(int pos);
    }


    private void selectText(List<View> topViewList, int i) {
        for (int j = 0; j < topViewList.size(); j++) {
            ViewGroup viewGroup = (ViewGroup) topViewList.get(j);
            TextView textView = (TextView) viewGroup.getChildAt(0);
            textView.setTextColor(i == j ? colors[1] : colors[0]);
        }
        if (mListener != null) {
            mListener.onTextPosSelect(i);
        }
    }
}
