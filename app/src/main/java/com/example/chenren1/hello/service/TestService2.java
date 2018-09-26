package com.example.chenren1.hello.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

/**
 * Created by chenren1 on 2018/5/10.
 */

public class TestService2 extends Service {
    private int num = 0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.e("cccc", "cccc TestService2 " + (++num));
            if (num == 20) {
                return;
            }

            mHandler.sendEmptyMessageDelayed(1, 1000);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("cccc", "cccc TestService2  ----------------  2");
        mHandler.sendEmptyMessage(1);
        return super.onStartCommand(intent, flags, startId);
    }

}
