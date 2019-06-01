package com.furniture.bean;

import java.util.ArrayList;
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

public class RoomConfigListBean {
    private List<ActionBean> mTopList;
    private List<ActionBean> mBottomList;

    public RoomConfigListBean(List<ActionBean> list) {
        mTopList = new ArrayList<>();
        mBottomList = new ArrayList<>();
        for (ActionBean bean : list) {
            if (bean.isSelect()) {
                mTopList.add(bean);
            } else {
                mBottomList.add(bean);
            }
        }
    }

    public List<ActionBean> getTopList() {
        return mTopList;
    }

    public List<ActionBean> getBottomList() {
        return mBottomList;
    }
}
