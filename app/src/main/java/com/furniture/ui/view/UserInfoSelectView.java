package com.furniture.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
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
public class UserInfoSelectView extends LinearLayout {

    private TextView textView;
    private String cn = "#FF313131";
    private String cs = "#11B9BB";
    private OnTextSelectListener mOnTextSelectListener;
    private LinearLayout linearLayout;
    private static InverseBindingListener mListener;

    public interface OnTextSelectListener {
        void text(String text);
    }

    public void setOnTextSelectListener(OnTextSelectListener onTextSelectListener) {
        this.mOnTextSelectListener = onTextSelectListener;
    }

    public UserInfoSelectView(@NonNull Context context) {
        this(context, null);
    }

    public UserInfoSelectView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UserInfoSelectView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.UserInfoEditViewAttrs);
        String leftString = a.getString(R.styleable.UserInfoEditViewAttrs_left_text);
        a.recycle();

        setOrientation(HORIZONTAL);
        setPadding(30, 0, 30, 0);
        setGravity(Gravity.CENTER);

        textView = new TextView(context);
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);
        MySelectView selectView = new MySelectView(context);
        selectView.setText("男");
        MySelectView selectView1 = new MySelectView(context);
        selectView1.setText("女");
        linearLayout.addView(selectView);
        linearLayout.addView(selectView1);
        LayoutParams p = (LayoutParams) selectView.getLayoutParams();
        p.width = 0;
        p.weight = 1;
        LayoutParams p1 = (LayoutParams) selectView1.getLayoutParams();
        p1.width = 0;
        p1.weight = 1;
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            int finalI = i;
            linearLayout.getChildAt(i).setOnClickListener(v -> {
                for (int j = 0; j < linearLayout.getChildCount(); j++) {
                    MySelectView sv = (MySelectView) linearLayout.getChildAt(j);
                    sv.setSelect(finalI == j);
                }
                if (mListener != null) {
                    mListener.onChange();
                }
                if (mOnTextSelectListener != null) {
                    mOnTextSelectListener.text(((MySelectView) v).getText());
                }
            });
        }

        int size = 12;
        textView.setTextColor(Color.parseColor(cn));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        textView.setGravity(Gravity.START);

        textView.setText(leftString);

        addView(textView);
        addView(linearLayout);

        LayoutParams params1 = (LayoutParams) textView.getLayoutParams();
        params1.width = XTools.WindowUtil().dip2px(40);
        LinearLayout.LayoutParams params2 = (LayoutParams) linearLayout.getLayoutParams();
        params2.width = XTools.WindowUtil().dip2px(90);
    }

    private void select(String sex) {
        if (!TextUtils.isEmpty(sex)) {
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                MySelectView v = (MySelectView) linearLayout.getChildAt(i);
                v.setSelect(v.getText().equals(sex));
            }
        } else {
            MySelectView v = (MySelectView) linearLayout.getChildAt(0);
            v.setSelect(true);
        }
    }

    private String getSelectText() {
        String s = "";
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            MySelectView v = (MySelectView) linearLayout.getChildAt(i);
            if (v.isSelect()) {
                s = v.getText();
                break;
            }
        }
        return s;
    }


    @BindingAdapter({"select"})
    public static void setSex(UserInfoSelectView view, String sex) {
        view.select(sex);
    }

    @InverseBindingAdapter(attribute = "select", event = "getSexChange")
    public static String getSex(UserInfoSelectView view) {
        return view.getSelectText();
    }

    @BindingAdapter(value = "getSexChange", requireAll = false)
    public static void getSexChange(UserInfoSelectView view, InverseBindingListener listener) {
        mListener = listener;
    }

    private class MySelectView extends LinearLayout {

        private TextView textView;
        private ImageView imageView;
        private boolean isSelect;
        private final
        @DrawableRes
        int[] imgs = new int[]{R.drawable.icon_xuanz_hui, R.drawable.icon_xuanz};

        public MySelectView(@NonNull Context context) {
            super(context);
            init();
        }

        public MySelectView(@NonNull Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public MySelectView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        private void init() {
            setOrientation(HORIZONTAL);
            setGravity(Gravity.CENTER);

            imageView = new ImageView(getContext());
            imageView.setImageResource(imgs[0]);
            textView = new TextView(getContext());
            textView.setTextColor(Color.parseColor(cn));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
            textView.setGravity(Gravity.CENTER);

            addView(imageView);
            addView(textView);

            LayoutParams params1 = (LayoutParams) imageView.getLayoutParams();
            params1.height = params1.width = XTools.WindowUtil().dip2px(20);
        }

        private void setText(String text) {
            textView.setText(text);
        }

        private String getText() {
            return textView.getText().toString().trim();
        }

        private void setSelect(boolean isSelect) {
            this.isSelect = isSelect;
            imageView.setImageResource(isSelect ? imgs[1] : imgs[0]);
        }

        private boolean isSelect() {
            return isSelect;
        }
    }
}
