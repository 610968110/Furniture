package com.furniture.event;

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
 * @date 2018/9/6.
 */

public class SwitchControlEvent {

    /**
     * R1
     */
    private final String room;
    /**
     * 例如 新风
     */
    private final String name;
    //    /**
//     * 例如新风 FAU1FS
//     */
//    private final String deviceName;
    private final boolean isOPen;

    public SwitchControlEvent(String room, String name, boolean isOPen) {
        this.room = room;
        this.name = name;
//        this.deviceName = deviceName;
        this.isOPen = isOPen;
    }

    public String getRoom() {
        return room;
    }

    public String getName() {
        return name;
    }

    public boolean isOPen() {
        return isOPen;
    }

//    public String getDeviceName() {
//        return deviceName;
//    }
}
