package com.furniture.bean.json.control;

import com.furniture.Config;
import com.furniture.bean.json.IGson;
import com.furniture.constant.INet;
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
 * @date 2018/9/9.
 * 回家  离家
 */

public class DeviceHome extends IGson {

    @SerializedName("id")
    private String id;
    @SerializedName("params")
    private Params params;
    @SerializedName("service")
    private String service;
    @SerializedName("type")
    private String type;
    @SerializedName("uuid")
    private String uuid;
    @SerializedName("timestamp")
    private long timestamp;

    @SerializedName("result")
    private int result;

    /**
     * room + devid 为控制的完整字段
     */
    public DeviceHome(String id, String room, String devid, String procode, boolean open) {
        this.id = id;
        params = new Params(room + devid, procode, open);
        service = INet.DEVICE_CONTROL;
        type = INet.SEND;
        uuid = Config.UUID;
        timestamp = System.currentTimeMillis() / 1000;
    }

    public DeviceHome(String room, String devid, String procode, boolean open) {
        this("", room, devid, procode, open);
        this.id = this.getClass().getName();
    }

    public String getId() {
        return id;
    }

    public Params getParams() {
        return params;
    }

    public String getService() {
        return service;
    }

    public String getType() {
        return type;
    }

    public int getResult() {
        return result;
    }

    public class Params {

        @SerializedName("devid")
        private String devid;
        @SerializedName("procode")
        private String procode;
        @SerializedName("field")
        private Field field;

        public Params(String devid, String procode, boolean open) {
            this.devid = devid;
            this.procode = procode;
            field = new Field(open);
        }

        public String getDevid() {
            return devid;
        }

        public Field getField() {
            return field;
        }

        public class Field {
            @SerializedName(value = "In")
            private int in;
            @SerializedName(value = "Out")
            private int out;

            public Field(boolean open) {
                in = open ? 1 : 0;
                out = open ? 0 : 1;
            }

            public boolean isIn() {
                return in == 1;
            }

            public boolean isOut() {
                return out == 1;
            }

            @Override
            public String toString() {
                return "Field{" +
                        "in='" + in + '\'' +
                        "out='" + out + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "Params{" +
                    "devid='" + devid + '\'' +
                    ", procode='" + procode + '\'' +
                    ", field=" + field +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Der{" +
                "id='" + id + '\'' +
                ", params=" + params +
                ", service='" + service + '\'' +
                ", type='" + type + '\'' +
                ", uuid='" + uuid + '\'' +
                ", timestamp=" + timestamp +
                ", result=" + result +
                '}';
    }
}
