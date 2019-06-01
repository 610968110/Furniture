package com.furniture.function;

import com.furniture.bean.UserInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

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

public class UserMap extends LinkedHashMap<String, UserInfo> implements Serializable {

    private static final String SP_DEFAULT_USER_INFO = "SP_DEFAULT_USER_INFO";
    private ArrayList<UserInfo> mList;

    public UserMap put(UserInfo... infos) {
        if (infos != null) {
            for (UserInfo u : infos) {
                put(u.getId(), u);
                if (mList != null) {
                    if (!mList.contains(u)) {
                        mList.add(u);
                    }
                }
            }
        }
        return this;
    }

    public UserMap replace(UserInfo info) {
        if (info != null) {
            remove(info.getId());
            put(info);
            if (mList != null) {
                if (mList.contains(info)) {
                    mList.remove(info);
                    mList.add(info);
                }
            }
        }
        return this;
    }

    public UserMap remove(UserInfo info) {
        if (info != null) {
            remove(info.getId());
            if (mList != null) {
                if (mList.contains(info)) {
                    mList.remove(info);
                }
            }
        }
        return this;
    }

    public UserInfo get(UserInfo userInfo) {
        return get(userInfo.getId());
    }

    public ArrayList<UserInfo> toList() {
        if (mList == null) {
            mList = new ArrayList<>();
        } else {
            mList.clear();
        }
        for (String s : keySet()) {
            mList.add(get(s));
        }
        return mList;
    }

    public static UserMap getFromLocal() {
        return (UserMap) XTools.ObjUtil().getObj(SP_DEFAULT_USER_INFO);
    }

    public UserMap save() {
        XTools.ObjUtil().saveObj(SP_DEFAULT_USER_INFO, this);
        return this;
    }
}
