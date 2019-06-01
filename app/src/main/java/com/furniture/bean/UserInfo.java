package com.furniture.bean;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.DrawableRes;

import com.furniture.R;

import java.io.Serializable;

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
 * @date 2018/8/30.
 */

public class UserInfo extends BaseObservable implements Serializable, Cloneable {

    private String id;
    public final ObservableField<String> name = new ObservableField<>();
    private final ObservableField<String> account = new ObservableField<>();
    private final ObservableField<String> password = new ObservableField<>();
    private String level;
    public final ObservableField<String> height = new ObservableField<>();
    public final ObservableField<String> weight = new ObservableField<>();
    public final ObservableField<String> age = new ObservableField<>();
    public final ObservableField<String> sex = new ObservableField<>();
    public final ObservableField<String> room = new ObservableField<>();
    private byte[] icon;
    private boolean manage;

    public UserInfo(String id) {
        this.id = id;
    }

    public UserInfo(String name, String account, String password, String level, @DrawableRes int icon) {
        this(name, account, password, level, 180, 70, "男", 25, "主卧", icon);
    }

    public UserInfo(String name, String account, String password, String level, int height, int weight, String sex,
                    int age, String room, @DrawableRes int icon) {
        this.id = account;
        this.name.set(name);
        this.account.set(account);
        this.password.set(password);
        this.level = level;
        this.height.set(String.valueOf(height));
        this.weight.set(String.valueOf(weight));
        this.sex.set(sex);
        this.age.set(String.valueOf(age));
        this.room.set(room);
        setIcon(icon);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account.get();
    }

    public void setAccount(String account) {
        this.account.set(account);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Bitmap getIcon() {
        Bitmap bitmap = XTools.BitmapUtil().byte2Bmp(icon);
        return bitmap == null ? getDefaultIcon() : bitmap;
    }

    private Bitmap getDefaultIcon() {
        return BitmapFactory.decodeResource(XTools.getApplicationContext().getResources(), R.drawable.pho_wode_touxiang);
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public void setIcon(@DrawableRes int icon) {
        Bitmap bitmap = BitmapFactory.decodeResource(XTools.getApplicationContext().getResources(), icon);
        this.icon = XTools.BitmapUtil().bmp2byte(bitmap);
        bitmap.recycle();
    }

    public void setIcon(Bitmap bitmap) {
        this.icon = XTools.BitmapUtil().bmp2byte(bitmap);
    }

    public boolean isManage() {
        return manage;
    }

    public void setManage(boolean manage) {
        this.manage = manage;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", name='" + name.get() + '\'' +
                ", account='" + account.get() + '\'' +
                ", password='" + password.get() + '\'' +
                ", level='" + level + '\'' +
                ", icon='" + icon + '\'' +
                ", height='" + height.get() + '\'' +
                ", weight='" + weight.get() + '\'' +
                ", sex='" + sex.get() + '\'' +
                ", age='" + age.get() + '\'' +
                ", room='" + room.get() + '\'' +
                ", manage='" + manage + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return name.get().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof UserInfo) && obj.hashCode() == this.hashCode();
    }
}
