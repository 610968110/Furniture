package com.furniture.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.furniture.R;
import com.furniture.bean.ActionBean;
import com.furniture.bean.UserInfo;

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
 * @date 2018/6/8.
 */

public class BigCardView extends LinearLayout {

    private Drawable mDrawable;
    private String mText;
    private float mImgSize;
    private float mTextSize;
    private ImageView imageView;
    private TextView textView;

    public BigCardView(@NonNull Context context) {
        this(context, null);
    }

    public BigCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BigCardView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BigCardViewAttrs);
        mDrawable = a.getDrawable(R.styleable.BigCardViewAttrs_BigCardViewTopImg);
        mText = a.getString(R.styleable.BigCardViewAttrs_BigCardViewBottomText);
        mImgSize = a.getDimension(R.styleable.BigCardViewAttrs_BigCardViewTopImgSize, XTools.WindowUtil().dip2px(50));
        mTextSize = a.getDimension(R.styleable.BigCardViewAttrs_BigCardViewBottomTextSize, XTools.WindowUtil().dip2px(10));
        a.recycle();
        View view = inflate(context, R.layout.view_big_card_view, null);
        addView(view);
        imageView = findViewById(R.id.iv_card);
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.width = params.height = (int) mImgSize;
        if (mDrawable != null) {
            imageView.setImageDrawable(mDrawable);
        }
        textView = findViewById(R.id.tv_card);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        textView.setTextColor(Color.parseColor("#878787"));
        textView.setSingleLine();
        if (!TextUtils.isEmpty(mText)) {
            textView.setText(mText);
        }
    }

    public  void setImagesize(int size){
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.width = params.height = size;
    }

    public void setIcon(@DrawableRes int icon) {
        if (icon != -1) {
            imageView.setImageResource(icon);
        }
    }

    public void setIcon(Bitmap bitmap) {
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    public void setText(String text) {
        if (text == null) {
            text = "";
        }
        textView.setText(text);
    }

    @BindingAdapter({"bindData"})
    public static void bindData(BigCardView view, ActionBean data) {
        String name = data.getName();
        String otherName = data.getOtherName();
        view.setText(TextUtils.isEmpty(otherName) ? name : otherName);
//        view.setText(name);
        data.binView(view);
        view.setIcon(data.isOpen() ? data.getIconOpen() : data.getIcon());
//        view.setOnClickListener(v -> {
//            xLogUtil.e("baseClick:" + name);
//            IClickTask task = data.getTask();
//            if (task != null) {
//                task.actionClick();
//            }
//        });
    }

    @BindingAdapter({"user"})
    public static void bindUser(BigCardView view, UserInfo userInfo) {
        view.setText(userInfo.getLevel());
        view.setIcon(userInfo.getIcon());
    }
}
