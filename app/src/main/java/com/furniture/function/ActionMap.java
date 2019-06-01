package com.furniture.function;

import com.furniture.bean.json.AllState;

import java.io.Serializable;
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
 * @date 2018/8/30.
 */

public class ActionMap extends LinkedHashMap<String, AllState.Params.Item> implements Serializable {

    public ActionMap put(AllState.Params.Item... items) {
        clear();
        if (items != null) {
            for (AllState.Params.Item item : items) {
                put(item.devid, item);
            }
        }
        return this;
    }

    public ActionMap put(List<AllState.Params.Item> list) {
        clear();
        if (list != null) {
            for (AllState.Params.Item item : list) {
                put(item.devid, item);
            }
        }
        return this;
    }
}
