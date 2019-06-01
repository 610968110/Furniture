package com.furniture.ui.fragment.room2.home;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.furniture.R;
import com.furniture.base.BaseFragment;
import com.furniture.injector.components.ActivityComponent;
import com.furniture.ui.view.BigDescView;

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

public class HomeThirdFragment extends BaseFragment {

    @BindView(R.id.dv_1)
    BigDescView mBigDescView1;
    @BindView(R.id.dv_2)
    BigDescView mBigDescView2;

    @Override
    public void bindComponent(ActivityComponent activityComponent) {

    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_home_third;
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {

    }

    @Override
    public void initView(View view) {
        mBigDescView1.setSymbolText("");
        mBigDescView2.setSymbolText("");
        mBigDescView1.setImg(-1);
        mBigDescView2.setImg(-1);
        mBigDescView1.setName("PH值");
        mBigDescView2.setName("电导率");
        mBigDescView1.setContentText("7.0");
        mBigDescView2.setContentText("0.9");
    }

    @Override
    public void initData() {

    }
}
