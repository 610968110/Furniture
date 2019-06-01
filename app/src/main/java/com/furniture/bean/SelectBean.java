package com.furniture.bean;

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
 * @date 2018/8/27.
 */

public class SelectBean {

    private final String name;
    private final String type;
    private boolean isSelect;

    public SelectBean(String name, String type) {
        this(name, type, false);
    }

    public SelectBean(String name, String type, boolean isSelect) {
        this.name = name;
        this.type = type;
        this.isSelect = isSelect;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof SelectBean) && ((SelectBean) obj).getType().hashCode() == getType().hashCode();
    }

    @Override
    public String toString() {
        return "SelectBean{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", isSelect='" + isSelect + '\'' +
                '}';
    }
}
