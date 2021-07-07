package com.furniture.ui.view;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.furniture.R;
import com.furniture.databinding.ItemConfigSelectBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lbx.xtoollib.XTools;
import lbx.xtoollib.base.BaseDataAdapter;

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
 * @date 2018/9/26.
 */

public class SelectTitleView extends FrameLayout {

    private TextView mSelectView;
    private TextView mSelectTitleView;
    private ImageView mSelectImageView;
    private String[] mSelect;
    private int tag = -1;

    public SelectTitleView(@NonNull Context context) {
        this(context, null);
    }

    public SelectTitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectTitleView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = XTools.UiUtil().inflate(R.layout.view_select_title);
        addView(view);
        mSelectView = view.findViewById(R.id.tv_select);
        mSelectTitleView = view.findViewById(R.id.tv_select_title);
        mSelectImageView = view.findViewById(R.id.iv_select);
        View selectLayout = view.findViewById(R.id.ll_select);
        selectLayout.setOnClickListener(v -> {
            if (mSelect == null || mSelect.length <= 1 || true) {
                return;
            }
            View view1 = XTools.UiUtil().inflate(R.layout.window_select);
            PopupWindow window = new PopupWindow(view1, XTools.WindowUtil().dip2px(70), ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setOutsideTouchable(true);
            window.setFocusable(true);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            RecyclerView recyclerView = view1.findViewById(R.id.rv_window);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            List<String> l = new ArrayList<>();
            Collections.addAll(l, mSelect);
            WindowSelectAdapter adapter = new WindowSelectAdapter(getContext(), l);
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new BaseDataAdapter.OnItemClickListener<String>() {
                @Override
                public void onItemClick(RecyclerView recyclerView, int id, int position, String entity) {
                    setSelectText(entity);
                    window.dismiss();
                    if (mOnSelectItem != null) {
                        mOnSelectItem.onSelect(tag, position, entity);
                    }
                }

                @Override
                public void onItemLongClick(RecyclerView recyclerView, int id, int position, String entity) {

                }
            });
            window.showAsDropDown(selectLayout, 0, 0);
        });
    }


    public void setSelectText(String s) {
        mSelectView.setText(s);
    }

    public void setSelectText(String... s) {
        mSelect = s;
//        if (mSelect == null || mSelect.length <= 1) {
//            mSelectImageView.setVisibility(INVISIBLE);
//        } else {
//            mSelectImageView.setVisibility(VISIBLE);
//        }
        if (mSelect != null && mSelect.length > 0) {
            setSelectText(mSelect[0]);
        }
    }

    public void setSelectTitleText(String s) {
        mSelectTitleView.setText(s);
    }

    private class WindowSelectAdapter extends BaseDataAdapter<String, ItemConfigSelectBinding, BaseDataAdapter.BaseHolder> {

        private List<String> list;

        private WindowSelectAdapter(Context context, List<String> list) {
            super(context, list);
            this.list = list;
        }

        @Override
        public BaseDataAdapter.BaseHolder getHolder(View view, ViewDataBinding binding) {
            return new BaseHolder(view, binding);
        }

        @Override
        public int itemLayout() {
            return R.layout.item_config_select;
        }

        @Override
        public void dataBinding(ItemConfigSelectBinding binding, int position, String entity, BaseHolder baseHolder) {
            binding.setS(entity);
            binding.setIsShowLine(isEndItem(position));
            binding.getRoot().getLayoutParams().height = XTools.WindowUtil().dip2px(R.dimen.select_item_height);
        }

        private boolean isEndItem(int position) {
            return list.size() == position + 1;
        }

        @Override
        public boolean itemClickEnable() {
            return true;
        }

        @Override
        public boolean itemLongClickEnable() {
            return false;
        }
    }

    private OnSelectItem mOnSelectItem;

    public interface OnSelectItem {
        void onSelect(int tag, int pos, String name);
    }

    public void setOnSelectItem(int tag, OnSelectItem onSelectItem) {
        this.tag = tag;
        this.mOnSelectItem = onSelectItem;
    }
}
