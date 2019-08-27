package com.example.chenren1.hello.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.chenren1.hello.utils.LogUtils;

/**
 * Created by wenfei1 on 2017/11/21.
 */

public class ScreenReceiver extends BroadcastReceiver {

    public static final String USER_PRESENT_ACTION = "android.intent.action.USER_PRESENT";

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtils.d("ScreenReceiver " + intent.getAction());
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
    }
}
