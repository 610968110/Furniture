package com.furniture.adapter;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;

import lbx.xtoollib.base.BaseDataAdapter;
import lbx.xtoollib.view.CircleImageView;

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
 * @date 2018/8/30.
 */

public class BindingAttrsAdapter {

    @BindingAdapter({"bindAdapter"})
    public static void bindAdapter(RecyclerView recyclerView, BaseDataAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"icon"})
    public static void bind(CircleImageView circleImageView, Bitmap bitmap) {
        circleImageView.setImageBitmap(bitmap);
    }
}
