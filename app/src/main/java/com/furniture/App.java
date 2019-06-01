package com.furniture;

import android.app.Application;

import com.furniture.injector.components.AppComponent;
import com.furniture.injector.components.DaggerAppComponent;
import com.furniture.injector.modules.AppModule;

import lbx.xtoollib.XTools;

import static lbx.xtoollib.phone.xLogUtil.LEVEL_VERBOSE;

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

public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        boolean debug = XTools.ApkUtil().isApkInDebug(this);
        XTools xTools = new XTools.Builder()
                .log(/*是否打印log*/true)
                .logTag(/*设置log的tag*/"xys")
                .logLevel(/*设置显示log的级别*/ LEVEL_VERBOSE)
//                .logLevel(/*设置显示log的级别*/debug ? LEVEL_VERBOSE : LEVEL_NONE)
                .errLogFilePath(/*设置crashLog的文件存储路径*/"xTools")
                .errLogFileName(/*设置crashLog的文件存储名*/"ERR")
                .errLogFile(
                        /*是否打印到文件*/true,
//                        /*是否打印到log*/debug)
                        /*是否打印到log*/true)
                .logPrintFile(
                        /*是否打印log到文件*/true,
                        /*打印log文件在sd卡下的路径*/"xTools/log",
                        /*打印到file的log是否加密(des+base64对称加密), null为不加密*/ null)
                .build(this);
        //初始化
        xTools.init();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        mAppComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
