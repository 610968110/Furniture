package com.furniture.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.furniture.R
import com.furniture.bean.SocketBean
import com.furniture.bean.json.AllState
import com.furniture.bean.json.GetAllState
import com.furniture.constant.INet
import com.furniture.ui.activity.MainActivity
import com.furniture.utils.GsonUtil
import lbx.xtoollib.phone.xLogUtil
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit


/**
 * Author: liboxin
 * Date: 2020/5/29
 * Time: 14:59
 * Desc:
 */
class NoticeService : Service() {

    lateinit var mainActivity: MainActivity
    private var service: ScheduledExecutorService = Executors.newScheduledThreadPool(1)
    private var mediaPlayer: MediaPlayer? = null
    private var task: ScheduledFuture<*>? = null

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, NoticeService::class.java)
            context.startService(intent)
        }

        fun bind(context: Context, connect: ServiceConnection) = kotlin.runCatching {
            val intent = Intent(context, NoticeService::class.java)
            context.bindService(intent, connect, Context.BIND_AUTO_CREATE)
        }.exceptionOrNull()?.printStackTrace()

        fun unbind(context: Context, connect: ServiceConnection) = kotlin.runCatching {
            context.unbindService(connect)
        }.exceptionOrNull()?.printStackTrace()
    }

    class CoreBind internal constructor(val noticeService: NoticeService) : Binder()

    override fun onBind(p0: Intent?): IBinder? {
        return CoreBind(this)
    }

    override fun onCreate() {
        super.onCreate()
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
        task = service.scheduleAtFixedRate({
            kotlin.runCatching {
                xLogUtil.e("xys", "scheduleAtFixedRate")
                mainActivity.send(GetAllState(), false, false)
            }
        }, 5, 5, TimeUnit.SECONDS)
    }

    override fun onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
        task?.cancel(true)
        super.onDestroy()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSocketMsg(bean: SocketBean) {
        xLogUtil.e("xys", "~~~bean:${bean.json}")
        val result = GsonUtil.getInstance().getResult(bean.json)
        if (INet.ALL_TYPE == result.type) {
            val allState = GsonUtil.getInstance().fromJson(bean.json, AllState::class.java)
            xLogUtil.e("xys", "~~~allState:${allState.toJson()}")
            xLogUtil.e("xys", "~~~allState.params:${allState.params.toJson()}")
            allState.params.devices.forEach {
                xLogUtil.e("xys", "~~~allState.params.devices:${it.toJson()}")
            }
            val w = allState.params.devices.firstOrNull { it.devid == "RHome" }?.field?.warning
            xLogUtil.e("xys", "~~~w:$w")
            if (w == 1) {
                mainActivity.showWarningDialog()
                playWarning()
            } else {
                // 0
                mainActivity.dismissWarningDialog()
                stopWarning()
            }
        }
    }

    private fun playWarning() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.warning)
            mediaPlayer?.run {
                setOnPreparedListener {
                    it.start()
                    isLooping = true
                }
                kotlin.runCatching {
                    prepare()
                }
                this
            }
        }
    }

    private fun stopWarning() {
        mediaPlayer = mediaPlayer?.run {
            stop()
            reset()
            null
        }
    }
}