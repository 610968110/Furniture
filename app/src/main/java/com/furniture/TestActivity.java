package com.furniture;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.furniture.base.BaseActivity;
import com.furniture.bean.json.SendUUID;
import com.furniture.injector.components.AppComponent;
import com.furniture.injector.components.DaggerActivityComponent;
import com.furniture.injector.modules.ActivityModule;
import com.furniture.utils.UIUtil;
import com.vilyever.socketclient.SocketClient;
import com.vilyever.socketclient.helper.SocketClientAddress;
import com.vilyever.socketclient.helper.SocketClientDelegate;
import com.vilyever.socketclient.helper.SocketResponsePacket;

import lbx.xtoollib.XTools;

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

public class TestActivity extends BaseActivity {
    private EditText editText;
    private TextView textView;
    private ScrollView scrollView;
    private SocketClient mSocketClient;

    @Override
    public void bindComponent(AppComponent appComponent) {
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(appComponent)
                .build();
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.aaaaa;
    }

    @Override
    public void getDataBinding(ViewDataBinding binding) {

    }

    @Override
    public void initView(View view) {
        editText = findView(R.id.et_test);
        textView = findView(R.id.tv_test);
        scrollView = findView(R.id.sv_test);
        editText.setText("172.16.3.195");
    }

    @Override
    public void initData() {

    }

    public void test1(View view) {
        if (mSocketClient != null) {
            UIUtil.toast(mSocketClient.isConnected() + "");
            view.setOnLongClickListener(v -> {
                mSocketClient.disconnect();
                return false;
            });
        }
    }

    public void test(View view) {
        if (mSocketClient == null) {
            mSocketClient = new SocketClient();
            String remoteIP = editText.getText().toString();
            SocketClientAddress address = mSocketClient.getAddress();
            address.setRemoteIP(remoteIP);
            address.setRemotePort(9696);
            mSocketClient.connect();
            append("开始连接:" + address.getRemoteIP() + ":" + address.getRemotePort());
            mSocketClient.registerSocketClientDelegate(mDelegate);
        }
    }

    int i = 0;
    private SocketClientDelegate mDelegate = new SocketClientDelegate() {
        @Override
        public void onConnected(SocketClient client) {
            append("连接成功，准备发消息");
            SendUUID sendUUID = new SendUUID();
            sendUUID.setTest(i++ + "");
            String s = sendUUID.toJson();
            append("发送:" + s);
            mSocketClient.sendData(s.getBytes());
            append("等待接收消息");
        }

        @Override
        public void onDisconnected(SocketClient client) {
            mSocketClient.removeSocketClientDelegate(this);
            mSocketClient = null;
            append("断开连接");
//            test(null);
        }

        @Override
        public void onResponse(SocketClient client, @NonNull SocketResponsePacket responsePacket) {
            append("收到消息:" + responsePacket.getMessage());
            mSocketClient.disconnect();
        }
    };

    private void append(final String s) {
        XTools.UiUtil().runOnUIThread(() -> {
            textView.append(s + "\n");
            scrollView.fullScroll(View.FOCUS_DOWN);
        });
    }
}