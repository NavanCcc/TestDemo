package com.example.chenren1.hello.receiver;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.chenren1.hello.utils.LogUtils;

/**
 * 外部相关app链接下载安装器
 * Created by yangjun6 on 2017/6/8.
 */

public class InstallApkReceiver extends BroadcastReceiver {

    private long mLastClickTime;

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtils.d("InstallApkReceiver "+intent.getAction());
        if (intent.getAction() == null) {
            return;
        }
        if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
        }
    }

}
