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
 * @date 2018/9/14.
 */

public class NotifyRoomItem {

    private final int region;
    private final int pos;
    private String room;
    private String name;
    private boolean isOpen;
    private long delayed;

    public NotifyRoomItem(int region, int pos) {
        this.region = region;
        this.pos = pos;
    }

    public NotifyRoomItem(int region) {
        this.region = region;
        this.pos = -1;
    }

    public int getRegion() {
        return region;
    }

    public int getPos() {
        return pos;
    }

    public String getRoom() {
        return room;
    }

    public NotifyRoomItem setRoom(String room) {
        this.room = room;
        return this;
    }

    public String getName() {
        return name;
    }

    public NotifyRoomItem setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public NotifyRoomItem setOpen(boolean open) {
        isOpen = open;
        return this;
    }

    public void setDelayed(long delayed) {
        this.delayed = delayed;
    }

    public long getDelayed() {
        return delayed;
    }
}
