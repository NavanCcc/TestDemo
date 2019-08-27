package com.example.chenren1.hello.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.chenren1.hello.utils.LogUtils;

public class ConnectivityReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtils.d("ConnectivityReceiver " + intent.getAction());
    }
}
