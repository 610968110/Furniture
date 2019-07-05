package com.furniture.ui.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.databinding.ViewDataBinding;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.furniture.Config;
import com.furniture.R;
import com.furniture.adapter.RoomConfigPagerAdapter;
import com.furniture.base.BaseActivity;
import com.furniture.base.BaseRoomConfigFragment;
import com.furniture.bean.SocketBean;
import com.furniture.bean.json.AllState;
import com.furniture.bean.json.GetAllState;
import com.furniture.constant.INet;
import com.furniture.databinding.ActivityRoomConfigBinding;
import com.furniture.injector.components.AppComponent;
import com.furniture.injector.components.DaggerActivityComponent;
import com.furniture.injector.modules.ActivityModule;
import com.furniture.service.CoreService;
import com.furniture.type.IRoomConfig;
import com.furniture.type.Room2ConfigPager;
import com.furniture.type.RoomConfigPager;
import com.furniture.type.RoomJinanConfigPager;
import com.furniture.utils.GsonUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lbx.xtoollib.XIntent;
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
 * @date 2018/9/25.
 */

public class RoomConfigActivity extends BaseActivity {

    private CoreService mCoreService;

    public static XIntent getIntent(Context context) {
        return new XIntent(context, RoomConfigActivity.class);
    }

    private ActivityRoomConfigBinding mBinding;
    private List<IRoomConfig> mList;

    @Override
    public void bindComponent(AppComponent appComponent) {
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(new ActivityModule(this))
                .build();
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutID() {
        EventBus.getDefault().register(this);
        return R.layout.activity_room_config;
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {
        mBinding = (ActivityRoomConfigBinding) binding;
    }

    @Override
    public void initView(View view) {
        mBinding.tbConfig.bindActivity(this);
        mBinding.tbConfig.setTitle("场景配置");
        mBinding.tbConfig.setRightTextViewAction("保存", R.color.colorPrimary, v -> {
            int pos = mBinding.vpConfig.getCurrentItem();
            BaseRoomConfigFragment fragment = mList.get(pos).getFragment();
            fragment.save();
        });
    }

    @Override
    public void initData() {
        mList = new ArrayList<>();
        CoreService.bind(this, mConnection);
    }

    @Override
    protected void onDestroy() {
        CoreService.unBind(this, mConnection);
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mCoreService = ((CoreService.CoreBind) service).getCoreService();
            XTools.UiUtil().showProgressDialog(RoomConfigActivity.this, !Config.DEBUG);
            mCoreService.sendMsg(new GetAllState());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public void send(String s) {
        if (mCoreService != null) {
            XTools.UiUtil().showProgressDialog(this, !Config.DEBUG);
            mCoreService.sendMsg(s);
        }
    }

    @Subscribe
    public void onSocketMsg(SocketBean bean) {
        XTools.UiUtil().closeProgressDialog();
        try {
            if (mBinding.vpConfig.getAdapter() == null) {
                String json = bean.getJson();
                GsonUtil.Result result = GsonUtil.getInstance().getResult(json);
                if (INet.ALL_TYPE.equals(result.getType())) {
                    Config.AllState = GsonUtil.getInstance().fromJson(json, AllState.class);
                    if (mBinding.vpConfig.getAdapter() == null) {
                        if (Config.APP_TYPE == Config.TYPE_NORMAL) {
                            Collections.addAll(mList, RoomConfigPager.values());
                        } else if (Config.APP_TYPE == Config.TYPE_ONE) {
                            Collections.addAll(mList, Room2ConfigPager.values());
                        } else if (Config.APP_TYPE == Config.TYPE_DEMO_JINAN) {
                            Collections.addAll(mList, RoomJinanConfigPager.values());
                        }
                        mBinding.vpConfig.setAdapter(new RoomConfigPagerAdapter(mList, getSupportFragmentManager()));
                        mBinding.vpConfig.setOffscreenPageLimit(Integer.MAX_VALUE);
                        mBinding.tlConfig.setupWithViewPager(mBinding.vpConfig);
                    }
                }
            }
        } catch (Exception e) {
            Log.e("xys", "e:" + e);
        }
    }
}
