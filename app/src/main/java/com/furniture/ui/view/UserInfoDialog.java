package com.furniture.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.furniture.R;
import com.furniture.bean.UserInfo;
import com.furniture.databinding.DialogUserEditBinding;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import lbx.xtoollib.XTools;
import lbx.xtoollib.phone.xLogUtil;

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
public class UserInfoDialog extends Dialog {

    public static final int ADD = 0x010;
    public static final int EDIT = 0x011;
    private
    @UserInfoDialogStyle
    int style = ADD;
    private UserInfo mUserInfo;
    private List<UserInfo> mUserList;

    @IntDef({ADD, EDIT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface UserInfoDialogStyle {
    }

    private UserInfoDialog(@NonNull Context context, Builder builder) {
        super(context, R.style.NoTitleDialogStyle);
        mInverseBindingListener.clear();

        this.style = builder.style;
        this.mOnUserInfoListener = builder.mOnUserInfoListener;
        String id = String.valueOf(System.currentTimeMillis());
        this.mUserInfo = builder.mUserInfo == null ? builder.mUserInfo = new UserInfo(id) : builder.mUserInfo;
        this.mUserList = builder.mUserList;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DialogUserEditBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_user_edit, null, false);
        binding.setInfo(mUserInfo);
        xLogUtil.e("mUserInfo:" + mUserInfo);
        View view = binding.getRoot();
        setContentView(view);
        setCancelable(true);
        Button sure = view.findViewById(R.id.btn_sure);
        sure.setOnClickListener(v -> {
            if (mInverseBindingListener != null) {
                for (InverseBindingListener l : mInverseBindingListener) {
                    if (l != null) {
                        l.onChange();
                    }
                }
            }
            if (TextUtils.isEmpty(mUserInfo.name.get())) {
                XTools.UiUtil().showToast("请输入用户名");
                return;
            }
            mUserInfo.setId(mUserInfo.getAccount());
            if (mOnUserInfoListener != null) {
                mOnUserInfoListener.result(this, mUserInfo, true);
            }
        });
        view.findViewById(R.id.btn_cancel).setOnClickListener(v -> {
            if (mOnUserInfoListener != null) {
                mOnUserInfoListener.result(this, mUserInfo, false);
            }
        });
        if (this.style == EDIT) {
            sure.setText("修改");
        }
    }


    private OnUserInfoListener mOnUserInfoListener;

    public interface OnUserInfoListener {
        void result(Dialog dialog, UserInfo user, boolean isSure);
    }

    public static final class Builder {

        private
        @UserInfoDialogStyle
        int style;
        private OnUserInfoListener mOnUserInfoListener;
        private UserInfo mUserInfo;
        private List<UserInfo> mUserList;

        public Builder() {
        }

        public Builder style(@UserInfoDialogStyle int val) {
            style = val;
            return this;
        }

        public Builder onUserInfoListener(OnUserInfoListener val) {
            mOnUserInfoListener = val;
            return this;
        }

        public Builder bindUser(UserInfo userInfo) {
            this.mUserInfo = userInfo;
            return this;
        }

        public Builder userList(List<UserInfo> userList) {
            this.mUserList = userList;
            return this;
        }

        public UserInfoDialog build(Context context) {
            return new UserInfoDialog(context, this);
        }
    }

    @BindingAdapter(value = "center_text", requireAll = false)
    public static void setCenterText(UserInfoEditView view, String text) {
        view.setCenterText(text);
    }

    @InverseBindingAdapter(attribute = "center_text", event = "centerTextAttrChange")
    public static String getCenterText(UserInfoEditView view) {
        return view.getCenterText();
    }

    private static List<InverseBindingListener> mInverseBindingListener = new ArrayList<>();

    @BindingAdapter(value = {"centerTextAttrChange"}, requireAll = false)
    public static void centerTextAttrChange(UserInfoEditView view, InverseBindingListener inverseBindingListener) {
        if (inverseBindingListener != null) {
            mInverseBindingListener.add(inverseBindingListener);
        }
    }
}