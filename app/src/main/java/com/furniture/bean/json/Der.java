package com.furniture.bean.json;

import com.furniture.constant.Device;
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
 * 环境
 */

public class Der extends IGson {

    @SerializedName("devid")
    private String devid;
    @SerializedName("field")
    private Field field;

    public static class Field {

//        @SerializedName(value = Device.PM25, alternate = {
//                KeTingRoomFragment.ROOM + Device.DER + Device.PM25,
//                DinnerRoomFragment.ROOM + Device.DER + Device.PM25,
//                MaserRoomFragment.ROOM + Device.DER + Device.PM25,
//                BookRoomFragment.ROOM + Device.DER + Device.PM25})
//        private String PM25 = "";
//        @SerializedName(value = Device.TEMP, alternate = {
//                KeTingRoomFragment.ROOM + Device.DER + Device.TEMP,
//                DinnerRoomFragment.ROOM + Device.DER + Device.TEMP,
//                MaserRoomFragment.ROOM + Device.DER + Device.TEMP,
//                BookRoomFragment.ROOM + Device.DER + Device.TEMP})
//        private String temp = "";
//        @SerializedName(value = Device.CO2, alternate = {
//                KeTingRoomFragment.ROOM + Device.DER + Device.CO2,
//                DinnerRoomFragment.ROOM + Device.DER + Device.CO2,
//                MaserRoomFragment.ROOM + Device.DER + Device.CO2,
//                BookRoomFragment.ROOM + Device.DER + Device.CO2})
//        private String CO2 = "";
//        @SerializedName(value = Device.HUMI, alternate = {
//                KeTingRoomFragment.ROOM + Device.DER + Device.HUMI,
//                DinnerRoomFragment.ROOM + Device.DER + Device.HUMI,
//                MaserRoomFragment.ROOM + Device.DER + Device.HUMI,
//                BookRoomFragment.ROOM + Device.DER + Device.HUMI})

        @SerializedName(value = Device.PM25)
        private String PM25 = "0";
        @SerializedName(value = Device.TEMP)
        private String temp = "0";
        @SerializedName(value = Device.CO2)
        private String CO2 = "0";
        @SerializedName(value = Device.HUMI)
        private String humi = "0";

        public String getPM25() {
            return PM25;
        }

        public String getTemp() {
            return temp;
        }

        public String getCO2() {
            return CO2;
        }

        public String getHumi() {
            return humi;
        }

        @Override
        public String toString() {
            return "Field{" +
                    "PM25='" + PM25 + '\'' +
                    ", temp='" + temp + '\'' +
                    ", CO2='" + CO2 + '\'' +
                    ", humi='" + humi + '\'' +
                    '}';
        }
    }

    public Field getField() {
        return field;
    }

//    @SerializedName("id")
//    private String id;
//    @SerializedName("params")
//    private Params params;
//    @SerializedName("service")
//    private String service;
//    @SerializedName("type")
//    private String type;
//    @SerializedName("uuid")
//    private String uuid;
//    @SerializedName("timestamp")
//    private long timestamp;
//
//    @SerializedName("result")
//    private int result;
//
//    public Der(String id, String room, String devid, String procode) {
//        this.id = id;
//        params = new Params(room + devid, procode);
//        service = INet.DEVICE_INQUIRE;
//        type = INet.SEND;
//        uuid = Config.UUID;
//        timestamp = System.currentTimeMillis() / 1000;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public Params getParams() {
//        return params;
//    }
//
//    public String getService() {
//        return service;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public int getResult() {
//        return result;
//    }
//
//    public class Params {
//
//        @SerializedName("devid")
//        private String devid;
//        @SerializedName("procode")
//        private String procode;
//        @SerializedName("field")
//        private Field field;
//
//        public Params(String devid, String procode) {
//            this.devid = devid;
//            this.procode = procode;
//            field = new Field();
//        }
//
//        public String getDevid() {
//            return devid;
//        }
//
//        public Field getField() {
//            return field;
//        }
//
//        public class Field {
//
//            @SerializedName(value = Device.PM25, alternate = {
//                    KeTingRoomFragment.ROOM + Device.DER + Device.PM25,
//                    DinnerRoomFragment.ROOM + Device.DER + Device.PM25,
//                    MaserRoomFragment.ROOM + Device.DER + Device.PM25,
//                    BookRoomFragment.ROOM + Device.DER + Device.PM25})
//            private String PM25 = "";
//            @SerializedName(value = Device.TEMP, alternate = {
//                    KeTingRoomFragment.ROOM + Device.DER + Device.TEMP,
//                    DinnerRoomFragment.ROOM + Device.DER + Device.TEMP,
//                    MaserRoomFragment.ROOM + Device.DER + Device.TEMP,
//                    BookRoomFragment.ROOM + Device.DER + Device.TEMP})
//            private String temp = "";
//            @SerializedName(value = Device.CO2, alternate = {
//                    KeTingRoomFragment.ROOM + Device.DER + Device.CO2,
//                    DinnerRoomFragment.ROOM + Device.DER + Device.CO2,
//                    MaserRoomFragment.ROOM + Device.DER + Device.CO2,
//                    BookRoomFragment.ROOM + Device.DER + Device.CO2})
//            private String CO2 = "";
//            @SerializedName(value = Device.HUMI, alternate = {
//                    KeTingRoomFragment.ROOM + Device.DER + Device.HUMI,
//                    DinnerRoomFragment.ROOM + Device.DER + Device.HUMI,
//                    MaserRoomFragment.ROOM + Device.DER + Device.HUMI,
//                    BookRoomFragment.ROOM + Device.DER + Device.HUMI})
//            private String humi = "";
//
//            public String getPM25() {
//                return PM25;
//            }
//
//            public String getTemp() {
//                return temp;
//            }
//
//            public String getCO2() {
//                return CO2;
//            }
//
//            public String getHumi() {
//                return humi;
//            }
//
//            @Override
//            public String toString() {
//                return "Field{" +
//                        "PM25='" + PM25 + '\'' +
//                        ", temp='" + temp + '\'' +
//                        ", CO2='" + CO2 + '\'' +
//                        ", humi='" + humi + '\'' +
//                        '}';
//            }
//        }
//
//        @Override
//        public String toString() {
//            return "Params{" +
//                    "devid='" + devid + '\'' +
//                    ", procode='" + procode + '\'' +
//                    ", field=" + field +
//                    '}';
//        }
//    }
//
//    @Override
//    public String toString() {
//        return "Der{" +
//                "id='" + id + '\'' +
//                ", params=" + params +
//                ", service='" + service + '\'' +
//                ", type='" + type + '\'' +
//                ", uuid='" + uuid + '\'' +
//                ", timestamp=" + timestamp +
//                ", result=" + result +
//                '}';
//    }
}
