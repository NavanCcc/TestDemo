package com.example.chenren1.hello.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.chenren1.hello.utils.LogUtils;

/**
 * Created by wenfei1 on 2017/11/21.
 */

public class AlarmReceiver extends BroadcastReceiver {

    public static final String ALARM_ALER = "com.android.deskclock.ALARM_ALER";
    public static final String ALARM_DONE = "com.android.deskclock.ALARM_DONE";

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtils.d("AlarmReceiver " + intent.getAction());
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
    }
}
