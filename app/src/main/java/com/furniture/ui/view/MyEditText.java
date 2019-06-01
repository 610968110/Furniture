package com.furniture.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
 * @date 2018/10/24.
 */

public class MyEditText extends android.support.v7.widget.AppCompatEditText {

    private static final int SELECT_COLOR = Color.parseColor("#16BAB9");

    private boolean isEditing;

    public MyEditText(Context context) {
        super(context);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackground(null);
        setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                setBackgroundResource(R.drawable.select_edittext_bg);
                String text = getText().toString();
                if (!TextUtils.isEmpty(text)) {
                    setSelection(text.length());
                }
            } else {
                setBackground(null);
            }
        });
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = s.toString();
                if (!TextUtils.isEmpty(string) && string.length() > 8) {
                    setText(string.substring(0, 8));
                    XTools.UiUtil().showToast("不能超过8个字");
                }
            }
        });
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        //保证光标始终在最后面
        //防止不能多选
        if (selStart == selEnd) {
            setSelection(getText().length());
        }
    }

    public void edit(boolean edit) {
        isEditing = edit;
        if (edit) {
            setFocusable(true);
            setFocusableInTouchMode(true);
            requestFocus();
            requestFocusFromTouch();
            XTools.PhoneUtil().vibrator(getContext(), 300);
            XTools.SoftInputUtil().showSoftInput(this);
        } else {
            setFocusable(false);
            setFocusableInTouchMode(false);
        }
    }

    public boolean isEditing() {
        return isEditing;
    }
}
