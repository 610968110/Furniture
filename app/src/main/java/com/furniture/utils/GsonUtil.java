package com.furniture.utils;

import com.furniture.bean.json.IGson;
import com.google.gson.Gson;
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

public class GsonUtil {

    private static GsonUtil INSTANCE;
    private static Gson gson;

    public static GsonUtil getInstance() {
        if (INSTANCE == null) {
            synchronized (GsonUtil.class) {
                if (INSTANCE == null) {
                    INSTANCE = new GsonUtil();
                }
            }
        }
        return INSTANCE;
    }

    private GsonUtil() {
        gson = new Gson();
    }

    public <T extends IGson> String toJson(T obj) {
        return gson.toJson(obj);
    }

    public <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public Result getResult(String json) {
        return getInstance().fromJson(json, Result.class);
    }

    public static class Result {

        @SerializedName("id")
        private String id = "none";
        @SerializedName("type")
        private String type = "";
        @SerializedName("result")
        private int result;
        @SerializedName("params")
        private Params params = new Params();

        private class Params {
            @SerializedName("devid")
            private String devid = "";

            public String getDevid() {
                return devid;
            }
        }

        public String getId() {
            return id;
        }

        public int getResult() {
            return result;
        }

        public String getDevid() {
            return params.getDevid();
        }

        public String getType() {
            return type;
        }
    }
}
