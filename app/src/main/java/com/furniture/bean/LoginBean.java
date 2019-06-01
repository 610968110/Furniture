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
 * @date 2018/8/29.
 */

public class LoginBean {

    private ObservableField<String> account = new ObservableField<>();
    private ObservableField<String> password = new ObservableField<>();
    private ObservableField<String> ip = new ObservableField<>();

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

    public String getIp() {
        return ip.get();
    }

    public void setIp(String ip) {
        this.ip.set(ip);
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "account=" + account.get() +
                ", password=" + password.get() +
                ", ip=" + ip.get() +
                '}';
    }
}
