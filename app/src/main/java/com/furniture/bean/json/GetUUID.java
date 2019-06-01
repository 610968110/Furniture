package com.furniture.bean.json;

import com.google.gson.annotations.SerializedName;

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
 * @date 2018/9/10.
 */

public class GetUUID extends IGson {
    @SerializedName("type")
    private String type;
    @SerializedName("mac")
    private String mac;
    @SerializedName("uuid")
    private String uuid;

    public String getType() {
        return type;
    }

    public String getMac() {
        return mac;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "GetUUID{" +
                "type='" + type + '\'' +
                ", mac='" + mac + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
