package com.furniture.injector.components;

import android.app.Activity;
import android.content.Context;

import com.furniture.TestActivity;
import com.furniture.bean.UserInfo;
import com.furniture.injector.ContextLifeCycle;
import com.furniture.injector.ForActivity;
import com.furniture.injector.modules.ActivityModule;
import com.furniture.ui.activity.ChangeNickNameActivity;
import com.furniture.ui.activity.RoomConfigActivity;
import com.furniture.ui.activity.AirActivity;
import com.furniture.ui.activity.AirConditionerActivity;
import com.furniture.ui.activity.ChangePasswordActivity;
import com.furniture.ui.activity.CurtainsActivity;
import com.furniture.ui.activity.FamilyManageActivity;
import com.furniture.ui.activity.HeatingActivity;
import com.furniture.ui.activity.LampActivity;
import com.furniture.ui.activity.LoginActivity;
import com.furniture.ui.activity.MainActivity;
import com.furniture.ui.activity.SettingActivity;
import com.furniture.ui.activity.UserInfoActivity;
import com.furniture.ui.activity.UserManageActivity;
import com.furniture.ui.dialog.AirConditionerTempSelectDialogActivity;

import dagger.Component;

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
@ForActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    @ContextLifeCycle()
    Context activityContext();

    @ContextLifeCycle("App")
    Context appContext();

    UserInfo getUserInfo();

    void inject(LoginActivity activity);

    void inject(AirActivity activity);

    void inject(AirConditionerActivity activity);

    void inject(ChangePasswordActivity activity);

    void inject(CurtainsActivity activity);

    void inject(HeatingActivity activity);

    void inject(LampActivity activity);

    void inject(MainActivity activity);

    void inject(SettingActivity activity);

    void inject(FamilyManageActivity activity);

    void inject(UserInfoActivity userInfoActivity);

    void inject(TestActivity testActivity);

    void inject(UserManageActivity userManageActivity);

    void inject(RoomConfigActivity actionConfigActivity);

    void inject(ChangeNickNameActivity changeNickNameActivity);

    void inject(AirConditionerTempSelectDialogActivity activity);
}
