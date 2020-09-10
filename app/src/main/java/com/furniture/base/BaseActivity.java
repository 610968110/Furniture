package com.furniture.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.furniture.App;
import com.furniture.Config;
import com.furniture.injector.components.ActivityComponent;
import com.furniture.injector.components.AppComponent;

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

public abstract class BaseActivity extends lbx.xtoollib.base.BaseActivity {

    public ActivityComponent mActivityComponent;
    protected String id = "none";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bindComponent(getApp().getAppComponent());
        if (Config.SCREEN_ORIENTATION == Config.SCREEN_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onCreate(savedInstanceState);
    }

    /**
     * 设置注入
     *
     * @param appComponent 应用的Component
     */
    public abstract void bindComponent(AppComponent appComponent);

    private App getApp() {
        return (App) getApplication();
    }
    @NonNull
    protected String makeMsgId() {
        return id = String.valueOf(System.currentTimeMillis() / 1000);
    }
}
