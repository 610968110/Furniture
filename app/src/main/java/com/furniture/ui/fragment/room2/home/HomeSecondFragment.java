package com.furniture.ui.fragment.room2.home;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.furniture.R;
import com.furniture.base.BaseFragment;
import com.furniture.injector.components.ActivityComponent;
import com.furniture.ui.view.MyProgressBar;

import butterknife.BindView;

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
 * @date 2018/10/16.
 */

public class HomeSecondFragment extends BaseFragment {

    @BindView(R.id.pb_1)
    MyProgressBar mProgressBar1;
    @BindView(R.id.pb_2)
    MyProgressBar mProgressBar2;
    @BindView(R.id.pb_3)
    MyProgressBar mProgressBar3;
    @BindView(R.id.pb_4)
    MyProgressBar mProgressBar4;
    @BindView(R.id.pb_5)
    MyProgressBar mProgressBar5;
    @BindView(R.id.pb_6)
    MyProgressBar mProgressBar6;

    @Override
    public void bindComponent(ActivityComponent activityComponent) {

    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_home_second;
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {

    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {
        mProgressBar1.setProgress(30);
        mProgressBar1.setCenterString("9");
        mProgressBar2.setProgress(20);
        mProgressBar2.setCenterString("24");
        mProgressBar3.setProgress(80);
        mProgressBar3.setCenterString("56");
        mProgressBar4.setProgress(46);
        mProgressBar4.setCenterString("369");
        mProgressBar5.setProgress(34);
        mProgressBar5.setCenterString("40");
        mProgressBar6.setProgress(58);
        mProgressBar6.setCenterString("优");
    }
}
