package com.furniture.manager;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.vilyever.socketclient.SocketClient;
import com.vilyever.socketclient.helper.SocketClientDelegate;
import com.vilyever.socketclient.helper.SocketResponsePacket;
import com.vilyever.socketclient.util.CharsetUtil;

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
 * @date 2018/9/6.
 */

public class SocketManager {

    private static SocketManager INSTANCE;
    private static SocketClient mManager;
    private static String mIp;
    private static int mPort;
    private SocketClientDelegate mDelegate;

    public static SocketManager getInstance() {
        if (INSTANCE == null) {
            synchronized (SocketManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SocketManager();
                }
            }
        }
        return INSTANCE;
    }

    private SocketManager() {
    }

    public void connect(String ip, int port, OnSocketListener listener) {
        if (TextUtils.isEmpty(ip)) {
            return;
        }
        if (!ip.equals(mIp) || port != mPort) {
            clear();
        }
        mIp = ip;
        mPort = port;
        if (mManager == null) {
            mManager = new SocketClient();
            mManager.getAddress().setRemoteIP(mIp);
            mManager.getAddress().setRemotePort(mPort);
            mManager.getAddress().setConnectionTimeout(8 * 1000);
            mManager.setCharsetName(CharsetUtil.UTF_8);
        }
        registerReceiver(listener);
        try {
            mManager.connect();
        } catch (Exception e) {
            if (mOnSocketListener != null) {
                mOnSocketListener.onConnectFailed(mManager, e);
            }
        }
    }

    public void disconnect() {
        if (mManager != null && mManager.isConnected()) {
            mManager.disconnect();
        }
    }

    private void clear() {
        if (mManager != null) {
            if (mManager.isConnected()) {
                mManager.disconnect();
            }
            mManager = null;
        }
    }

    public void send(String msg) {
        if (mManager != null) {
            xLogUtil.e("send msg -> " + mManager.isConnected() + "   " + msg);
            mManager.sendData(msg.getBytes());
        }
    }

    private void registerReceiver(OnSocketListener listener) {
        mOnSocketListener = listener;
        unRegisterReceiver();
        if (mManager != null) {
            mManager.registerSocketClientDelegate(mDelegate = new SocketClientDelegate() {
                @Override
                public void onConnected(SocketClient client) {
                    if (mOnSocketListener != null) {
                        mOnSocketListener.onConnected(client);
                    }
                }

                @Override
                public void onDisconnected(SocketClient client) {
                    if (mOnSocketListener != null) {
                        mOnSocketListener.onDisconnected(client);
                    }
                }

                @Override
                public void onResponse(SocketClient client, @NonNull SocketResponsePacket responsePacket) {
                    if (mOnSocketListener != null) {
                        mOnSocketListener.onResponse(responsePacket.getMessage());
                    }
                }
            });
        }
    }

    public void unRegisterReceiver() {
        if (mManager != null && mDelegate != null) {
            mManager.removeSocketClientDelegate(mDelegate);
            mDelegate = null;
        }
    }

    public boolean isConnect() {
        return mManager != null && mManager.isConnected();
    }

    private OnSocketListener mOnSocketListener;

    public interface OnSocketListener {
        void onConnected(SocketClient client);

        void onDisconnected(SocketClient client);

        void onConnectFailed(SocketClient client, Exception e);

        void onResponse(String msg);
    }
}
