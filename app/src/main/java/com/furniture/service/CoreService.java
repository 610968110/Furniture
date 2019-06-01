package com.furniture.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import com.furniture.bean.SocketBean;
import com.furniture.bean.json.IGson;
import com.furniture.manager.SocketManager;
import com.furniture.task.ISocketConnect;
import com.furniture.task.SocketConnectTask;
import com.furniture.task.SocketDisconnectTask;
import com.vilyever.socketclient.SocketClient;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lbx.xtoollib.XIntent;
import lbx.xtoollib.phone.xLogUtil;

import static com.furniture.Config.IP;
import static com.furniture.Config.PORT;

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
 * .    ┃　　　┃
 * .    ┃　　　┃
 * .    ┃　　　┃
 * .    ┃　　　┃
 * .    ┃　　　┃
 * .    ┃　　　┃
 * .    ┃　　　┃
 * .    ┃　　　┃
 * .    ┃　　　┃
 * .    ┃　　　┃
 * .    ┃　　　┃
 * .    ┃　　　┃
 * .    ┃　　　┃
 * .    ┃　　　┃
 * .    ┃　　　┃
 * .    ┃　　　┃
 * .    ┃　　　┃
 * .    ┃　　　┃
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
 * @date 2018/8/29.
 */

public class CoreService extends Service {

    private ISocketConnect mISocketConnect = new SocketDisconnectTask();
    private List<String> msgsList = new ArrayList<>();
    private ExecutorService mPool = Executors.newFixedThreadPool(1);
    private boolean isSending;
    private boolean isConnecting;

    public static void start(Context context) {
        XIntent intent = new XIntent(context, CoreService.class);
        context.startService(intent);
    }

    public static void stop(Context context) {
        XIntent intent = new XIntent(context, CoreService.class);
        context.stopService(intent);
    }

    public static void bind(Context context, ServiceConnection connection) {
        XIntent intent = new XIntent(context, CoreService.class);
        context.bindService(intent, connection, BIND_AUTO_CREATE);
    }

    public static void unBind(Context context, ServiceConnection connection) {
        context.unbindService(connection);
    }

    public boolean isConnect() {
        return mISocketConnect instanceof SocketConnectTask;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        xLogUtil.e("CoreService onBind");
        return new CoreBind(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        xLogUtil.e("CoreService onCreate");
        mPool.execute(() -> {
            while (true) {
                SystemClock.sleep(500);
                if (!SocketManager.getInstance().isConnect()) {
                    isSending = false;
                }
                if (msgsList != null && !msgsList.isEmpty() && !isSending) {
                    if (isConnect()) {
                        xLogUtil.e("已连接，直接发送队列");
                        send();
                    } else {
                        if (!isConnecting) {
                            isConnecting = true;
                            xLogUtil.e("未连接，等待连接");
                            socketConnect(IP, PORT);
                            addOnSocketConnectListener(new OnSocketConnectListener() {
                                @Override
                                public void onSuccess() {
                                    xLogUtil.e("连接成功，发送队列");
                                    isConnecting = false;
                                    send();
                                }

                                @Override
                                public void onFailed(String err) {
                                    isConnecting = false;
                                }

                                @Override
                                public void onDisconnect(SocketClient client, String err) {
                                    xLogUtil.e("onDisconnect");
                                    isConnecting = false;
                                }
                            });
                        }
                    }
                }
            }
        });
    }

    private synchronized void send() {
        if (msgsList != null && !msgsList.isEmpty()) {
            isSending = true;
            String msg = msgsList.get(0);
            xLogUtil.e("从队列发送" + msgsList.size());
            mISocketConnect.onSendMessage(msg);
            msgsList.remove(0);
            xLogUtil.e("队列数量剩余:" + msgsList.size());
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        xLogUtil.e("CoreService onStartCommand");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        xLogUtil.e("CoreService onDestroy");
        SocketManager.getInstance().disconnect();
        mPool.shutdown();
//        list.clear();
        super.onDestroy();
    }

    public class CoreBind extends Binder {
        private final CoreService coreService;

        CoreBind(CoreService coreService) {
            this.coreService = coreService;
        }

        public CoreService getCoreService() {
            return coreService;
        }
    }

    /**
     * 连接
     */
    public void socketConnect(String ip, int port) {
        xLogUtil.e("Method socketConnect:" + ip + "   " + port);
        SocketManager.getInstance().connect(ip, port, mListener);
    }

    public void socketDisconnect() {
        xLogUtil.e("Method socketDisconnect");
        mISocketConnect = new SocketDisconnectTask();
        SocketManager.getInstance().disconnect();
    }

    public void sendMsg(IGson gson) {
        sendMsg(gson.toJson());
    }

    public void sendMsg(String msg) {
        msgsList.add(msg);
        xLogUtil.e("加入消息队列,总数量:" + msgsList.size() + "  " + msg);
    }

    private SocketManager.OnSocketListener mListener = new SocketManager.OnSocketListener() {
        @Override
        public void onConnected(SocketClient client) {
            xLogUtil.e("socket连接成功");
            mISocketConnect = new SocketConnectTask();
//            if (list != null) {
//                for (OnSocketConnectListener l : list) {
//                    l.onSuccess();
//                }
//            }
            if (onSocketConnectListener != null) {
                onSocketConnectListener.onSuccess();
            }
        }

        @Override
        public void onDisconnected(SocketClient client) {
            mISocketConnect = new SocketDisconnectTask();
//            if (list != null) {
//                for (OnSocketConnectListener l : list) {
//                    l.onDisconnect(client, null);
//                }
//            }
            if (onSocketConnectListener != null) {
                onSocketConnectListener.onDisconnect(client, null);
            }
        }

        @Override
        public void onConnectFailed(SocketClient client, Exception e) {
            xLogUtil.e(this, "socket连接失败");
            mISocketConnect = new SocketDisconnectTask();
//            if (list != null) {
//                for (OnSocketConnectListener l : list) {
//                    l.onFailed(e.getMessage());
//                }
//            }
            if (onSocketConnectListener != null) {
                onSocketConnectListener.onFailed(e.toString());
            }
        }

        @Override
        public void onResponse(String msg) {
            isSending = false;
            msg = msg.replace(" ", "").replace("\n", "").replace("\r", "");
            xLogUtil.e("socket 收到 -> " + msg);
            mISocketConnect.cancelTimeOut();
            EventBus.getDefault().post(new SocketBean(msg));
            if (msgsList.isEmpty()) {
                socketDisconnect();
            }
        }
    };

//    private List<OnSocketConnectListener> list = new ArrayList<>();

    public interface OnSocketConnectListener {
        void onSuccess();

        void onFailed(String err);

        void onDisconnect(SocketClient client, String err);
    }

    private OnSocketConnectListener onSocketConnectListener;

    public void addOnSocketConnectListener(OnSocketConnectListener onSocketConnectListener) {
//        list.add(onSocketConnectListener);
        this.onSocketConnectListener = onSocketConnectListener;
    }

    public void removeOnSocketConnectListener(OnSocketConnectListener onSocketConnectListener) {
//        list.remove(onSocketConnectListener);
    }
}
