package com.furniture.utils;

import com.furniture.bean.ActionBean;

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
 * @date 2018/9/11.
 */

public class ListUtil {

    public static int getPosFromClass(List list, Class clazz) {
        int i = -1;
        if (list != null) {
            for (Object o : list) {
                i++;
                if (o.getClass().getName().equals(clazz.getName())) {
                    return i;
                }
            }
        }
        return i;
    }

    public static int getPosFromName(List<ActionBean> list, String name) {
        int i = -1;
        if (list != null) {
            for (ActionBean o : list) {
                i++;
                if (o.getName().equals(name)) {
                    return i;
                }
            }
        }
        return i;
    }
}
