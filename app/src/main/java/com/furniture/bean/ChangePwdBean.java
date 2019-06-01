package com.furniture.bean;

import android.databinding.ObservableField;

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

public class ChangePwdBean {

    private ObservableField<String> oldPwd = new ObservableField<>();
    private ObservableField<String> newPwd = new ObservableField<>();
    private ObservableField<String> surePwd = new ObservableField<>();

    public ChangePwdBean() {
        oldPwd.set("");
        newPwd.set("");
        surePwd.set("");
    }

    public String getOldPwd() {
        return oldPwd.get();
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd.set(oldPwd);
    }

    public String getNewPwd() {
        return newPwd.get();
    }

    public void setNewPwd(String newPwd) {
        this.newPwd.set(newPwd);
    }

    public String getSurePwd() {
        return surePwd.get();
    }

    public void setSurePwd(String surePwd) {
        this.surePwd.set(surePwd);
    }

    @Override
    public String toString() {
        return "ChangePwdBean{" +
                "oldPwd=" + oldPwd.get() +
                ", newPwd=" + newPwd.get() +
                ", surePwd=" + surePwd.get() +
                '}';
    }
}
