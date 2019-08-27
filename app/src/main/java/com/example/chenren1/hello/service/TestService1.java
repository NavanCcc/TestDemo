package com.example.chenren1.hello.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.example.chenren1.hello.utils.LogUtils;

/**
 * Created by chenren1 on 2018/5/10.
 */

public class TestService1 extends Service {
    private int num = 0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            LogUtils.d("TestService1 " + (++num));
            if (num == 150) {
                return;
            }
            if (num == 100) {
               startForegroundService();
               return;
            }
//            startServices();
            mHandler.sendEmptyMessageDelayed(1, 1000);
        }
    };

    private void startServices() {
        LogUtils.d("startServices2  ----------------  1 ");
        Intent intent = new Intent(this, TestService2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startService(intent);
    }

    private void startForegroundService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LogUtils.d("startForegroundService2  ----------------  1 ");
            Intent intent = new Intent(this, TestService2.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startForegroundService(intent);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mHandler.sendEmptyMessage(1);
        return super.onStartCommand(intent, flags, startId);
    }

}
