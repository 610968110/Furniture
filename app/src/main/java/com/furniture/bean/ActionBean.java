package com.furniture.bean;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.support.annotation.DrawableRes;

import com.furniture.bean.json.AllState;
import com.furniture.impl.IClickTask;
import com.furniture.task.ActionClick;
import com.furniture.ui.view.BigCardView;

import lbx.xtoollib.XTools;
import lbx.xtoollib.res.SpUtil;

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
 */

public class ActionBean {
    private String name;
    /**
     * 例如 R1SenWet中的SenWet
     */
    private String deviceName;
    private String otherName;
    private
    @DrawableRes
    int icon;
    @DrawableRes
    int iconOpen;
    private ActionClick task;
    private ObservableBoolean isOpen = new ObservableBoolean();
    private BigCardView view;
    private String clickId = "";
    /**
     * room的标签
     */
    protected String room = "";

    /**
     * 配置是否选中开启
     */
    private boolean isSelect;

    public ActionBean(String name, String room, int icon) {
        this(name, room, icon, icon);
    }

    public ActionBean(String name, String room, int icon, int iconOpen) {
        this(name, room, "", icon, iconOpen, null);
    }

    public ActionBean(String name, String room, String deviceName, int icon, int iconOpen, ActionClick task) {
        this.name = name;
        this.icon = icon;
        this.iconOpen = iconOpen;
        this.task = task;
        this.room = room;
        this.deviceName = deviceName;
        otherName = SpUtil.getInstance().getString(room + name, null);
    }
    public ActionBean(String name, String room, String deviceName, int icon, ActionClick task) {
        this(name, room, deviceName, icon, icon, task);
    }

    public void binView(BigCardView view) {
        this.view = view;
    }

    public String getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }

    public IClickTask getTask() {
        return task;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setTask(ActionClick task) {
        this.task = task;
    }

    public int getIconOpen() {
        return iconOpen;
    }

    public void setIconOpen(int iconOpen) {
        this.iconOpen = iconOpen;
    }

    public boolean isOpen() {
        return isOpen.get();
    }

    public void setOpen(boolean open) {
        isOpen.set(open);
        if (view != null) {
            view.setIcon(isOpen() ? getIconOpen() : getIcon());
        }
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void onClick(Context context, boolean isLongClick) {
        boolean open = isOpen();
        XTools.SpUtil().putBoolean(room + getName(), !open);
        setOpen(!open);
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getClickId() {
        return clickId;
    }

    public void setClickId(String clickId) {
        this.clickId = clickId;
    }

    public void onRefresh(AllState.Params.Item.Field field) {

    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        SpUtil.getInstance().putString(room + name, otherName);
        this.otherName = otherName;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @Override
    public String toString() {
        return "ActionBean{" +
                "name='" + name + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", icon=" + icon +
                ", iconOpen=" + iconOpen +
                ", task=" + task +
                ", isOpen=" + isOpen +
                ", view=" + view +
                ", clickId='" + clickId + '\'' +
                ", room='" + room + '\'' +
                ", isSelect=" + isSelect +
                ", otherName=" + otherName +
                '}';
    }
}
