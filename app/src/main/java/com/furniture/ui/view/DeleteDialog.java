package com.furniture.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
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
 *         #11B9BB
 * @date 2018/8/31
 */
public class DeleteDialog extends Dialog {

    private String text;
    private String lightText;

    private DeleteDialog(@NonNull Context context, Builder builder) {
        super(context, R.style.NoTitleDialogStyle);
        View view = XTools.UiUtil().inflate(R.layout.dialog_normal);
        setContentView(view);
        setCancelable(true);
        TextView textView = view.findViewById(R.id.tv_content);
        view.findViewById(R.id.btn_cancel).setOnClickListener(v -> {
            if (mOnButtonClickListener != null) {
                mOnButtonClickListener.onClick(this, false);
            }
        });
        view.findViewById(R.id.btn_sure).setOnClickListener(v -> {
            if (mOnButtonClickListener != null) {
                mOnButtonClickListener.onClick(this, true);
            }
        });

        text = builder.text;
        lightText = builder.lightText;
        mOnButtonClickListener = builder.mOnButtonClickListener;

        SpannableStringBuilder style = new SpannableStringBuilder(text);
        if (!TextUtils.isEmpty(lightText) && !TextUtils.isEmpty(text) && text.contains(lightText)) {
            int start = text.indexOf(lightText);
            style.setSpan(new ForegroundColorSpan(Color.parseColor("#11B9BB")), start, start + lightText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setText(style);
    }

    private OnButtonClickListener mOnButtonClickListener;

    public interface OnButtonClickListener {
        void onClick(Dialog dialog, boolean sure);
    }

    public static final class Builder {
        private String text;
        private String lightText;
        private OnButtonClickListener mOnButtonClickListener;

        public Builder() {
        }

        public Builder text(String val) {
            text = val;
            return this;
        }

        public Builder lightText(String val) {
            lightText = val;
            return this;
        }

        public Builder onButtonClickListener(OnButtonClickListener val) {
            mOnButtonClickListener = val;
            return this;
        }

        public DeleteDialog build(Context context) {
            return new DeleteDialog(context, this);
        }
    }
}