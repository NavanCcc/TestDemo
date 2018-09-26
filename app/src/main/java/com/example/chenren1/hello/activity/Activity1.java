package com.example.chenren1.hello.activity;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.example.chenren1.hello.R;
import com.example.chenren1.hello.receiver.ConnectivityReceiver;
import com.example.chenren1.hello.receiver.InstallApkReceiver;
import com.example.chenren1.hello.receiver.ScreenReceiver;

import static android.app.DownloadManager.ACTION_NOTIFICATION_CLICKED;

/**
 * Created by chenren1 on 2018/5/21.
 */

public class Activity1 extends Activity {

    DownLoadCompleteReceiver downReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDownLoad("http://jenkins/view/SinaNews/job/Android_News_gradle3/2266/artifact/SinaNews/build/outputs/apk/dev/release/AndResGuard_SinaNews-dev-armeabi-v7a-release/SinaNews-dev-armeabi-v7a-release_aligned_signed.apk");
            }
        });
        registerReceivers();
    }

    private void startDownLoad(String url) {
        DownloadManager downloadManager = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        try {
            final DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            request.setDestinationInExternalFilesDir(this, "download", "sinanews.apk")
                    .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
                    .setTitle("zzzz正在下载")
                    .setDescription("zzzzDescription") //防止系统显示剩余下载时间，不能为空
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                    .setMimeType("application/vnd.android.package-archive");
            long downloadId = downloadManager.enqueue(request);
            Log.e("cccc", "cccc downloadId " + downloadId);
        } catch (Exception e) {
            Log.e("cccc", "cccc Exception");
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterReceivers();
    }


    private void registerReceivers() {
        IntentFilter filter = new IntentFilter(ACTION_NOTIFICATION_CLICKED);
        downReceiver = new DownLoadCompleteReceiver();
        this.registerReceiver(downReceiver, filter);
    }

    private void unRegisterReceivers() {
        this.unregisterReceiver(downReceiver);
    }

    public class DownLoadCompleteReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("cccc", "cccc DownLoadCompleteReceiver " + intent.getAction());
        }
    }
}
