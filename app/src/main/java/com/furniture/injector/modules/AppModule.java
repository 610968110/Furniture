package com.furniture.injector.modules;

import android.content.Context;

import com.furniture.App;
import com.furniture.Config;
import com.furniture.bean.UserInfo;
import com.furniture.injector.ContextLifeCycle;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
 * @date 2018/5/31.
 */
@Module
public class AppModule {

    private final App mApp;

    public AppModule(App mApp) {
        this.mApp = mApp;
        Config.init();
    }

    @Provides
    @Singleton
    App getApp() {
        return mApp;
    }

    @Provides
    @Singleton
    @ContextLifeCycle("App")
    Context getActivityContext() {
        return mApp.getApplicationContext();
    }

    @Provides
    @Singleton
    public UserInfo getUserInfo() {
        return Config.loginInfo;
    }
}
