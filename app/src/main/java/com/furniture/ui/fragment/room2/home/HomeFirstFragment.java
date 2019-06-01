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

public class HomeFirstFragment extends BaseFragment {

    @BindView(R.id.dv_1)
    BigDescView mBigDescView1;
    @BindView(R.id.dv_2)
    BigDescView mBigDescView2;

    @Override
    public void bindComponent(ActivityComponent activityComponent) {

    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_home_first;
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {

    }

    @Override
    public void initView(View view) {
        mBigDescView1.setSymbolText("Lux");
        mBigDescView2.setSymbolText("Lux");
        mBigDescView1.setImg(R.drawable.sun);
        mBigDescView2.setImg(R.drawable.sun);
        mBigDescView1.setName("环境亮度");
        mBigDescView2.setName("桌面亮度");
        mBigDescView1.setContentText("446");
        mBigDescView2.setContentText("198");
    }

    @Override
    public void initData() {

    }
}
