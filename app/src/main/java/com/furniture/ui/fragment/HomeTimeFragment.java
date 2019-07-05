package com.furniture.ui.fragment;

import android.databinding.ViewDataBinding;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.furniture.Config;
import com.furniture.R;
import com.furniture.base.BaseFragment;
import com.furniture.bean.SocketBean;
import com.furniture.bean.json.AllState;
import com.furniture.bean.json.LimitResult;
import com.furniture.bean.json.PM25Result;
import com.furniture.bean.json.WeatherResult;
import com.furniture.bean.json.control.DeviceHome;
import com.furniture.constant.INet;
import com.furniture.databinding.FragmentHomeTimeBinding;
import com.furniture.injector.components.ActivityComponent;
import com.furniture.injector.components.DaggerFragmentComponent;
import com.furniture.injector.modules.FragmentModule;
import com.furniture.ui.activity.MainActivity;
import com.furniture.ui.view.LimitTextView;
import com.furniture.ui.view.PmLayout;
import com.furniture.ui.view.TempLayout;
import com.furniture.utils.GsonUtil;
import com.furniture.utils.HttpUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import lbx.xtoollib.bean.DateEntity;
import lbx.xtoollib.listener.OnHttpObservableCallBack;
import lbx.xtoollib.phone.xLogUtil;

import static com.furniture.constant.Device.HOME;

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

public class HomeTimeFragment extends BaseFragment {

    public static final String NAME = "R";

    @BindView(R.id.tv_limit)
    LimitTextView mLimitTextView;
    @BindView(R.id.tv_weather)
    TextView mWeatherTextView;
    @BindView(R.id.tv_time)
    TextView mTimeTextView;
    @BindView(R.id.tv_date)
    TextView mDateTextView;
    @BindView(R.id.pl_pm)
    PmLayout mPmLayout;
    @BindView(R.id.tl_temp)
    TempLayout mTempLayout;
    private Disposable mDisposable;
    private MainActivity mMainActivity;
    private FragmentHomeTimeBinding mBinding;

    @Override
    public int getLayoutID() {
        EventBus.getDefault().register(this);
        return R.layout.fragment_home_time;
    }

    @Override
    public void bindComponent(ActivityComponent activityComponent) {
        mFragmentComponent = DaggerFragmentComponent.builder()
                .activityComponent(activityComponent)
                .fragmentModule(new FragmentModule())
                .build();
        mFragmentComponent.inject(this);
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {
        mBinding = (FragmentHomeTimeBinding) binding;
    }

    @Override
    public void initView(View view) {
        mTempLayout.setTemp("-");
        mLimitTextView.setLimit("-", "-");
        mPmLayout.setPM("-");
        if (Config.APP_TYPE == Config.TYPE_DEMO_JINAN) {
            View limit = findView(R.id.limit);
            limit.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void initData() {
        FragmentActivity activity = getActivity();
        if (activity instanceof MainActivity) {
            mMainActivity = (MainActivity) activity;
        }
        Observable<Long> observable = Observable.interval(0, 1, TimeUnit.SECONDS);
        Observable<String[]> observe = observable.map(aLong -> new DateEntity(System.currentTimeMillis(), "HH:mm"))
                .map(dateEntity -> {
                    String time = dateEntity.timeFormat;
                    String date = dateEntity.month + "月" + dateEntity.day + "日" + dateEntity.weekString;
                    return new String[]{time, date};
                })
                .observeOn(AndroidSchedulers.mainThread());
        mDisposable = observe.subscribe(strings -> {
            mTimeTextView.setText(strings[0]);
            mDateTextView.setText(strings[1]);
        });
        getDataFromHttp();
    }

    private void getDataFromHttp() {
        HttpUtil.getInstance().getWeather(new OnHttpObservableCallBack<WeatherResult>() {
            @Override
            public void onSuccess(WeatherResult weatherResult) {
                mBinding.setWeather(weatherResult);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        HttpUtil.getInstance().getPM25(new OnHttpObservableCallBack<PM25Result>() {
            @Override
            public void onSuccess(PM25Result pm25Result) {
                mBinding.setPm25(pm25Result);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        HttpUtil.getInstance().getLimit(new OnHttpObservableCallBack<LimitResult>() {
            @Override
            public void onSuccess(LimitResult limitResult) {
                mBinding.setLimit(limitResult);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @OnClick(R.id.iv_goHome)
    public void goHome() {
        xLogUtil.e(this, "inHome");
        if (mMainActivity != null) {
            mMainActivity.send(new DeviceHome(NAME, HOME, "", true), true);
        }
    }

    @OnClick(R.id.iv_outHome)
    public void outHome() {
        xLogUtil.e(this, "outHome");
        if (mMainActivity != null) {
            mMainActivity.send(new DeviceHome(NAME, HOME, "", false), true);
        }
    }

    @Override
    public void onDestroy() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void onSocketMsg(SocketBean bean) {
        try {
            String json = bean.getJson();
            GsonUtil.Result result = GsonUtil.getInstance().getResult(json);
            if (INet.ALL_TYPE.equals(result.getType())) {
                AllState allState = GsonUtil.getInstance().fromJson(json, AllState.class);
                xLogUtil.e(this, "天气 带完善");
            }
        } catch (Exception ignored) {
        }
    }
}
