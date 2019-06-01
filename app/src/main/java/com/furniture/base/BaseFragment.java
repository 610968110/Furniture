package com.furniture.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.furniture.injector.components.ActivityComponent;
import com.furniture.injector.components.FragmentComponent;

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
 * @date 2018/8/24.
 */

public abstract class BaseFragment extends lbx.xtoollib.base.BaseFragment {

    public FragmentComponent mFragmentComponent;
    private long time;
    private static final int INTERVAL = 60 * 1000;
    protected String id = "none";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BaseActivity activity = (BaseActivity) getActivity();
        bindComponent(activity.mActivityComponent);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public abstract void bindComponent(ActivityComponent activityComponent);

    protected boolean isArrivalTime() {
        long timeMillis = System.currentTimeMillis();
        if (timeMillis - time >= INTERVAL) {
            time = timeMillis;
            return true;
        }
        return false;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @NonNull
    protected String makeMsgId() {
        return id = String.valueOf(System.currentTimeMillis() / 1000);
    }
}
