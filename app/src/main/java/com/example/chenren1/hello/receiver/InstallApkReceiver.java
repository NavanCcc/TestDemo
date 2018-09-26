package com.example.chenren1.hello.receiver;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 外部相关app链接下载安装器
 * Created by yangjun6 on 2017/6/8.
 */

public class InstallApkReceiver extends BroadcastReceiver {

    private long mLastClickTime;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("cccc","cccc InstallApkReceiver "+intent.getAction());
        if (intent.getAction() == null) {
            return;
        }
        if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
        }
    }

}
