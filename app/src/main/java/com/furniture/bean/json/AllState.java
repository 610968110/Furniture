package com.furniture.bean.json;

import com.furniture.constant.Device;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * @date 2018/9/11.
 */

public class AllState extends IGson {

    @SerializedName("service")
    public String service = "";
    @SerializedName("type")
    public String type = "";
    @SerializedName("uuid")
    public String uuid = "";
    @SerializedName("timestamp")
    public long timestamp;
    @SerializedName("params")
    public Params params = new Params();

    public static class Params extends IGson {

        @SerializedName("devices")
        public List<Item> devices = new ArrayList<>();

        public static class Item extends IGson{
            @SerializedName("devid")
            public String devid = "";
            @SerializedName("online")
            public String online;
            @SerializedName("procode")
            public String procode;
            @SerializedName("field")
            public Field field = new Field();

            @Override
            public String toString() {
                return "Item{" +
                        "devid='" + devid + '\'' +
                        ", online='" + online + '\'' +
                        ", procode='" + procode + '\'' +
                        ", field=" + field +
                        '}';
            }

            public static class Field extends IGson {

                public boolean isCtrlOpen() {
                    return Ctrl == Device.OPEN;
                }

                public boolean isStasOpen() {
                    return Stas == Device.OPEN;
                }

                public boolean isCodeOpen() {
                    return cold == Device.OPEN;
                }

                public boolean isHotOpen() {
                    return hot == Device.OPEN;
                }

                public boolean isDryOpen() {
                    return dry == Device.OPEN;
                }

                public boolean isWetOpen() {
                    return wet == Device.OPEN;
                }

                public boolean isStuffyOpen() {
                    return stuffy == Device.OPEN;
                }


                @SerializedName("Warning")
                public int warning;

                //------------------ 冷热干湿闷 start ------------------
                @SerializedName("Cold")
                public float cold;
                @SerializedName("Dry")
                public float dry;
                @SerializedName("Hot")
                public float hot;
                @SerializedName("Stuffy")
                public float stuffy;
                @SerializedName("Wet")
                public float wet;
                //------------------ 空调 start ------------------
                @SerializedName("CFS")
                public String CFS;
                @SerializedName("CM")
                public String CM;
                @SerializedName("Ctrl")
                public float Ctrl;
                @SerializedName("Err")
                public String Err;
                @SerializedName("FS")
                public String FS;
                @SerializedName("Mode")
                public String Mode;
                @SerializedName("Temp")
                public float Temp;
                @SerializedName("Stas")
                public float Stas;
                @SerializedName("SetT")
                public float SetT;
                @SerializedName("ST")
                public float ST;
                //------------------ 新风 start ------------------
//                @SerializedName("Ctrl")
//                public float Ctrl;
//                @SerializedName("FS")
//                public float FS;
                @SerializedName("F")
                public float F;
                @SerializedName("SF")
                public float SF;
                @SerializedName("SFS")
                public float SFS;
                //------------------ 窗帘 start ------------------
//                @SerializedName("Ctrl")
//                public float Ctrl;
                @SerializedName("FB")
                public float FB;
                //------------------ 纱窗 start ------------------
//                @SerializedName("Ctrl")
//                public float Ctrl;
//                @SerializedName("FB")
//                public float FB;
                //------------------ 各种灯 start ------------------
//                @SerializedName("Stas")
//                public float Stas;
                @SerializedName("Switch")
                public float Switch;
                @SerializedName("Brtn")
                public String Brtn;
                //------------------ 灯光模式 start ------------------
                @SerializedName("LM")
                public int LM;
                //------------------ 其他 start ------------------
                @SerializedName("Dining[1]")
                public int Dining1;
                @SerializedName("Dining[2]")
                public int Dining2;
                @SerializedName("Dining[3]")
                public int Dining3;
                @SerializedName("Dining[4]")
                public int Dining4;
                @SerializedName("Dining[5]")
                public int Dining5;
                @SerializedName("Dining[6]")
                public int Dining6;
                @SerializedName("Dining[7]")
                public int Dining7;
                @SerializedName("Dining[8]")
                public int Dining8;
                @SerializedName("Dining[9]")
                public int Dining9;
                @SerializedName("Dining[10]")
                public int Dining10;
                @SerializedName("Dining[11]")
                public int Dining11;
                @SerializedName("Dining[12]")
                public int Dining12;
                @SerializedName("Dining[13]")
                public int Dining13;
                @SerializedName("Dining[14]")
                public int Dining14;
                @SerializedName("Dining[15]")
                public int Dining15;

                @SerializedName("Meeting[1]")
                public int Meeting1;
                @SerializedName("Meeting[2]")
                public int Meeting2;
                @SerializedName("Meeting[3]")
                public int Meeting3;
                @SerializedName("Meeting[4]")
                public int Meeting4;
                @SerializedName("Meeting[5]")
                public int Meeting5;
                @SerializedName("Meeting[6]")
                public int Meeting6;
                @SerializedName("Meeting[7]")
                public int Meeting7;
                @SerializedName("Meeting[8]")
                public int Meeting8;
                @SerializedName("Meeting[9]")
                public int Meeting9;
                @SerializedName("Meeting[10]")
                public int Meeting10;
                @SerializedName("Meeting[11]")
                public int Meeting11;
                @SerializedName("Meeting[12]")
                public int Meeting12;
                @SerializedName("Meeting[13]")
                public int Meeting13;
                @SerializedName("Meeting[14]")
                public int Meeting14;
                @SerializedName("Meeting[15]")
                public int Meeting15;

                @SerializedName("Viewing[1]")
                public int Viewing1;
                @SerializedName("Viewing[2]")
                public int Viewing2;
                @SerializedName("Viewing[3]")
                public int Viewing3;
                @SerializedName("Viewing[4]")
                public int Viewing4;
                @SerializedName("Viewing[5]")
                public int Viewing5;
                @SerializedName("Viewing[6]")
                public int Viewing6;
                @SerializedName("Viewing[7]")
                public int Viewing7;
                @SerializedName("Viewing[8]")
                public int Viewing8;
                @SerializedName("Viewing[9]")
                public int Viewing9;
                @SerializedName("Viewing[10]")
                public int Viewing10;
                @SerializedName("Viewing[11]")
                public int Viewing11;
                @SerializedName("Viewing[12]")
                public int Viewing12;
                @SerializedName("Viewing[13]")
                public int Viewing13;
                @SerializedName("Viewing[14]")
                public int Viewing14;
                @SerializedName("Viewing[15]")
                public int Viewing15;

                @SerializedName("Reading[1]")
                public int Reading1;
                @SerializedName("Reading[2]")
                public int Reading2;
                @SerializedName("Reading[3]")
                public int Reading3;
                @SerializedName("Reading[4]")
                public int Reading4;
                @SerializedName("Reading[5]")
                public int Reading5;
                @SerializedName("Reading[6]")
                public int Reading6;
                @SerializedName("Reading[7]")
                public int Reading7;
                @SerializedName("Reading[8]")
                public int Reading8;
                @SerializedName("Reading[9]")
                public int Reading9;
                @SerializedName("Reading[10]")
                public int Reading10;
                @SerializedName("Reading[11]")
                public int Reading11;
                @SerializedName("Reading[12]")
                public int Reading12;
                @SerializedName("Reading[13]")
                public int Reading13;
                @SerializedName("Reading[14]")
                public int Reading14;
                @SerializedName("Reading[15]")
                public int Reading15;

                @SerializedName("Mode[1]")
                public int Mode1;
                @SerializedName("Mode[2]")
                public int Mode2;
                @SerializedName("Mode[3]")
                public int Mode3;
                @SerializedName("Mode[4]")
                public int Mode4;
                @SerializedName("Mode[5]")
                public int Mode5;
                @SerializedName("Mode[6]")
                public int Mode6;
                @SerializedName("Mode[7]")
                public int Mode7;
                @SerializedName("Mode[8]")
                public int Mode8;
                @SerializedName("Mode[9]")
                public int Mode9;
                @SerializedName("Mode[10]")
                public int Mode10;
                @SerializedName("Mode[11]")
                public int Mode11;
                @SerializedName("Mode[12]")
                public int Mode12;
                @SerializedName("Mode[13]")
                public int Mode13;
                @SerializedName("Mode[14]")
                public int Mode14;
                @SerializedName("Mode[15]")
                public int Mode15;

                private Map<String, Integer> map = new HashMap<>();

                public int getModeState(boolean isHome, String mode, int index) {
                    if (map.isEmpty() || map.get(Device.HOME) == null) {
                        Class clazz = this.getClass();
                        java.lang.reflect.Field[] fields = clazz.getFields();
                        for (java.lang.reflect.Field f : fields) {
                            f.setAccessible(true);
                            try {
                                String name = f.getName();
                                Integer value = (Integer) f.get(this);
//                                if (value == 1 || (isHome && name.contains(Device.HOME))) {
//                                    map.put(name, value);
//                                }
                                if (isHome) {
                                    map.put(Device.HOME, 2);
                                    if (name.contains(Device.HOME)) {
                                        map.put(name, value);
                                    }
                                } else {
                                    if (!name.contains(Device.HOME)) {
                                        map.put(name, value);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    Integer integer = map.get(mode + index);
                    return integer == null ? 0 : integer;
                }

                @Override
                public String toString() {
                    return "Field{" +
                            "cold=" + cold +
                            ", dry=" + dry +
                            ", hot=" + hot +
                            ", stuffy=" + stuffy +
                            ", wet=" + wet +
                            ", CFS='" + CFS + '\'' +
                            ", CM='" + CM + '\'' +
                            ", Ctrl=" + Ctrl +
                            ", Err='" + Err + '\'' +
                            ", FS='" + FS + '\'' +
                            ", Mode='" + Mode + '\'' +
                            ", Temp=" + Temp +
                            ", Stas=" + Stas +
                            ", SetT=" + SetT +
                            ", ST=" + ST +
                            ", F=" + F +
                            ", SF=" + SF +
                            ", SFS=" + SFS +
                            ", FB=" + FB +
                            ", Switch=" + Switch +
                            ", Brtn='" + Brtn + '\'' +
                            ", LM=" + LM +
                            ", Dining1=" + Dining1 +
                            ", Dining2=" + Dining2 +
                            ", Dining3=" + Dining3 +
                            ", Dining4=" + Dining4 +
                            ", Dining5=" + Dining5 +
                            ", Dining6=" + Dining6 +
                            ", Dining7=" + Dining7 +
                            ", Dining8=" + Dining8 +
                            ", Dining9=" + Dining9 +
                            ", Dining10=" + Dining10 +
                            ", Dining11=" + Dining11 +
                            ", Dining12=" + Dining12 +
                            ", Dining13=" + Dining13 +
                            ", Dining14=" + Dining14 +
                            ", Dining15=" + Dining15 +
                            ", Meeting1=" + Meeting1 +
                            ", Meeting2=" + Meeting2 +
                            ", Meeting3=" + Meeting3 +
                            ", Meeting4=" + Meeting4 +
                            ", Meeting5=" + Meeting5 +
                            ", Meeting6=" + Meeting6 +
                            ", Meeting7=" + Meeting7 +
                            ", Meeting8=" + Meeting8 +
                            ", Meeting9=" + Meeting9 +
                            ", Meeting10=" + Meeting10 +
                            ", Meeting11=" + Meeting11 +
                            ", Meeting12=" + Meeting12 +
                            ", Meeting13=" + Meeting13 +
                            ", Meeting14=" + Meeting14 +
                            ", Meeting15=" + Meeting15 +
                            ", Viewing1=" + Viewing1 +
                            ", Viewing2=" + Viewing2 +
                            ", Viewing3=" + Viewing3 +
                            ", Viewing4=" + Viewing4 +
                            ", Viewing5=" + Viewing5 +
                            ", Viewing6=" + Viewing6 +
                            ", Viewing7=" + Viewing7 +
                            ", Viewing8=" + Viewing8 +
                            ", Viewing9=" + Viewing9 +
                            ", Viewing10=" + Viewing10 +
                            ", Viewing11=" + Viewing11 +
                            ", Viewing12=" + Viewing12 +
                            ", Viewing13=" + Viewing13 +
                            ", Viewing14=" + Viewing14 +
                            ", Viewing15=" + Viewing15 +
                            ", Reading1=" + Reading1 +
                            ", Reading2=" + Reading2 +
                            ", Reading3=" + Reading3 +
                            ", Reading4=" + Reading4 +
                            ", Reading5=" + Reading5 +
                            ", Reading6=" + Reading6 +
                            ", Reading7=" + Reading7 +
                            ", Reading8=" + Reading8 +
                            ", Reading9=" + Reading9 +
                            ", Reading10=" + Reading10 +
                            ", Reading11=" + Reading11 +
                            ", Reading12=" + Reading12 +
                            ", Reading13=" + Reading13 +
                            ", Reading14=" + Reading14 +
                            ", Reading15=" + Reading15 +
                            ", Mode1=" + Mode1 +
                            ", Mode2=" + Mode2 +
                            ", Mode3=" + Mode3 +
                            ", Mode4=" + Mode4 +
                            ", Mode5=" + Mode5 +
                            ", Mode6=" + Mode6 +
                            ", Mode7=" + Mode7 +
                            ", Mode8=" + Mode8 +
                            ", Mode9=" + Mode9 +
                            ", Mode10=" + Mode10 +
                            ", Mode11=" + Mode11 +
                            ", Mode12=" + Mode12 +
                            ", Mode13=" + Mode13 +
                            ", Mode14=" + Mode14 +
                            ", Mode15=" + Mode15 +
                            '}';
                }
            }
        }
    }
}
