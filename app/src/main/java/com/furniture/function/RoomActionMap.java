package com.furniture.function;

import com.furniture.bean.ActionBean;

import java.util.LinkedHashMap;
import java.util.List;

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
 * @date 2018/9/27.
 */

public class RoomActionMap extends LinkedHashMap<String, List<ActionBean>> {


    public RoomActionMap putTopList(String room, List<ActionBean> list) {
        put(room + "top", list);
        return this;
    }

    public RoomActionMap putCenterList(String room, List<ActionBean> list) {
        put(room + "center", list);
        return this;
    }

    public RoomActionMap putAllList(String room, List<ActionBean> list) {
        put(room + "all", list);
        return this;
    }

    public List<ActionBean> getTopList(String room) {
        return get(room + "top");
    }

    public List<ActionBean> getCenterList(String room) {
        return get(room + "center");
    }

    public List<ActionBean> getAllList(String room) {
        return get(room + "all");
    }
}
