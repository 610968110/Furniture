package com.furniture.ui.dialog;

import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.widget.Button;

import com.furniture.R;
import com.furniture.base.BaseActivity;
import com.furniture.databinding.DialogTempSelectBinding;
import com.furniture.event.AirConditionerTempBean;
import com.furniture.injector.components.AppComponent;
import com.furniture.injector.components.DaggerActivityComponent;
import com.furniture.injector.modules.ActivityModule;
import com.furniture.ui.view.WheelView;

import org.greenrobot.eventbus.EventBus;

import lbx.xtoollib.XIntent;
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
 * @date 2018/12/20.
 */

public class AirConditionerTempSelectDialogActivity extends BaseActivity {

    private int i = 1, i1 = 6;
    private WheelView wheelView1;
    private WheelView wheelView2;
    private Button sure;
    private DialogTempSelectBinding mBinding;
    private int mSelectTemp;


    public static XIntent getIntent(Context context, int selectTemp) {
        XIntent xIntent = new XIntent(context, AirConditionerTempSelectDialogActivity.class);
        xIntent.putExtra("selectTemp", selectTemp);
        return xIntent;
    }

    @Override
    public void bindComponent(AppComponent appComponent) {
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(appComponent)
                .build();
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.dialog_temp_select;
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {
        mBinding = (DialogTempSelectBinding) binding;
    }

    @Override
    public void initIntent(Intent intent) {
        mSelectTemp = intent.getIntExtra("selectTemp", 16);
        if (mSelectTemp < 16) {
            mSelectTemp = 16;
        }
        if (mSelectTemp > 30) {
            mSelectTemp = 30;
        }
    }

    @Override
    public void initView(View view) {
        wheelView1 = mBinding.wv1;
        wheelView2 = mBinding.wv2;
        sure = mBinding.btnSure;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        super.initListener();
        sure.setOnClickListener(v -> {
            xLogUtil.e("选择温度1:" + i + i1);
            xLogUtil.e("选择温度2:" + i1);
            int t =  i * 10 + i1;
            xLogUtil.e("选择温度end:" +t);
            EventBus.getDefault().post(new AirConditionerTempBean(t));
            finish();
        });
        if (mSelectTemp >= 16 && mSelectTemp < 20) {
            wheelView1.setItems("1", "2", "3");
            String[] strings = {"6", "7", "8", "9"};
            wheelView2.setItems(strings);
            wheelView2.setSeletion(mSelectTemp % 10 - 6);
            i = 1;
            i1 = Integer.valueOf(strings[wheelView2.getSeletedIndex()]);
        } else if (mSelectTemp >= 20 && mSelectTemp < 30) {
            wheelView1.setItems("1", "2", "3");
            String[] strings = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
            wheelView2.setItems(strings);
            wheelView1.setSeletion(1);
            wheelView2.setSeletion(mSelectTemp % 10);
            i = 2;
            i1 = Integer.valueOf(strings[wheelView2.getSeletedIndex()]);
        } else {
            String[] strings = {"1", "2", "3"};
            wheelView1.setItems(strings);
            wheelView2.setItems("0");
            wheelView1.setSeletion(2);
            i = 3;
            i1 = 0;
        }
        wheelView1.setOnWheelViewListener((selectedIndex, item) -> {
            if ("3".equals(item)) {
                wheelView2.setItems("0");
                i1 = 0;
            } else if ("1".equals(item)) {
                wheelView2.setItems("6", "7", "8", "9");
                i1 = 6;
            } else {
                wheelView2.setItems("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
                i1 = 0;
            }
            i = Integer.valueOf(item);
            xLogUtil.e("item1:" + item);
        });
        wheelView2.setOnWheelViewListener((selectedIndex, item) -> {
            i1 = Integer.valueOf(item);
            xLogUtil.e("item2:" + item);
        });
    }

    public void finishActivity(View view) {
        finish();
    }
}
