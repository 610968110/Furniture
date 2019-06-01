package com.furniture.task;

import android.os.Handler;
import android.os.Message;

import com.furniture.manager.SocketManager;

import lbx.xtoollib.XTools;
import lbx.xtoollib.phone.xLogUtil;

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
 * @date 2018/9/10.
 */

public class SocketConnectTask implements ISocketConnect {

    private static String MSG;
    private static Handler HANDLER = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    xLogUtil.e("服务器无返回 超时 断开连接");
                    HANDLER.removeCallbacksAndMessages(null);
                    SocketManager.getInstance().disconnect();
                    XTools.UiUtil().closeProgressDialog();
                    XTools.UiUtil().showToast("请求超时");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onSendMessage(String msg) {
        MSG = msg;
        SocketManager.getInstance().send(msg);
        cancelTimeOut();
        HANDLER.sendEmptyMessageDelayed(0, 3000);
    }

    @Override
    public void cancelTimeOut() {
        HANDLER.removeCallbacksAndMessages(null);
    }
}
