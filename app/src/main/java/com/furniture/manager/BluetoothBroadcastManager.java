package com.furniture.manager;

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
 * @date 2018/9/8.
 */

public class BluetoothBroadcastManager {

    private static BluetoothBroadcastManager INSTANCE;

    public static BluetoothBroadcastManager getInstance() {
        if (INSTANCE == null) {
            synchronized (BluetoothBroadcastManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BluetoothBroadcastManager();
                }
            }
        }
        return INSTANCE;
    }

    private BluetoothBroadcastManager() {
    }
}
