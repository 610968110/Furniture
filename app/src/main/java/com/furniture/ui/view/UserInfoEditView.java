package com.furniture.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
 * @date 2018/8/31.
 */
//@InverseBindingMethods(@InverseBindingMethod(
//        type = UserInfoEditView.class,
//        attribute = "center_text",
//        event = "centerText",
//        method = "getCenterText"
//))
public class UserInfoEditView extends LinearLayout {

    private TextView textView;
    private EditText editText;
    private TextView textView1;
    private String cn = "#FF313131";
    private String cs = "#11B9BB";

    public String getCenterText() {
        return editText.getText().toString();
    }

    public void setCenterText(String centerText) {
        editText.setText(centerText);
    }

    public UserInfoEditView(@NonNull Context context) {
        this(context, null);
    }

    public UserInfoEditView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UserInfoEditView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.UserInfoEditViewAttrs);
        String leftString = a.getString(R.styleable.UserInfoEditViewAttrs_left_text);
        int inputType = a.getInt(R.styleable.UserInfoEditViewAttrs_input_type, 0);
        String rightString = a.getString(R.styleable.UserInfoEditViewAttrs_right_text);
        a.recycle();

        setOrientation(HORIZONTAL);
        setPadding(30, 0, 30, 0);
        setGravity(Gravity.CENTER_HORIZONTAL);

        textView = new TextView(context);
        editText = new MyEditText(context);
        textView1 = new TextView(context);

        int size = XTools.ResUtil().getDimen(R.dimen.edit_text_size);
        textView.setTextColor(Color.parseColor(cn));
        textView1.setTextColor(Color.parseColor(cn));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        textView1.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        textView.setGravity(Gravity.START);
        editText.setGravity(Gravity.CENTER);
        textView1.setGravity(Gravity.CENTER);
        if (inputType == 1) {
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        }

        textView.setText(leftString);
        textView1.setText(rightString);

        addView(textView);
        addView(editText);
        addView(textView1);

        LinearLayout.LayoutParams params1 = (LayoutParams) textView.getLayoutParams();
        params1.width = XTools.WindowUtil().dip2px(40);
        LinearLayout.LayoutParams params2 = (LayoutParams) editText.getLayoutParams();
        params2.width = XTools.WindowUtil().dip2px(60);
        LinearLayout.LayoutParams params3 = (LayoutParams) textView1.getLayoutParams();
        params3.width = XTools.WindowUtil().dip2px(30);
    }

    private class MyEditText extends android.support.v7.widget.AppCompatEditText {

        private Paint mPaint;
        private RectF mOval;

        public MyEditText(@NonNull Context context) {
            super(context);
            init();
        }

        public MyEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public MyEditText(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        private void init() {
            setBackground(null);
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
            mPaint.setColor(Color.parseColor("#eaeaea"));
            mPaint.setStrokeWidth(XTools.WindowUtil().dip2px(1));
            mOval = new RectF();
            setOnFocusChangeListener((v, f) -> {
                setTextColor(Color.parseColor(f ? cs : cn));
                Editable text = getText();
                MyEditText.this.setSelection(text == null ? 0 : text.toString().length());
            });
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            mOval.set(
                    getPaddingStart(),
                    getMeasuredHeight() - mPaint.getStrokeWidth(),
                    getMeasuredWidth() - getPaddingEnd(),
                    getMeasuredHeight());
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawOval(mOval, mPaint);
        }
    }
}
