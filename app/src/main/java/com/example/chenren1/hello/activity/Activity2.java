package com.example.chenren1.hello.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.chenren1.hello.R;

import static android.app.DownloadManager.ACTION_NOTIFICATION_CLICKED;

/**
 * Dialog
 * Created by chenren1 on 2018/5/21.
 */

public class Activity2 extends Activity {

    private Handler mHandler;
    private Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        mHandler = new Handler();
        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finishMe();
                    }
                }, 3000);
            }
        });
    }

    public void showDialog() {
        //创建dialog构造器
        AlertDialog.Builder normalDialog = new AlertDialog.Builder(this);
        //设置title
        normalDialog.setTitle("我的标题");
        //设置icon
        normalDialog.setIcon(R.mipmap.ic_launcher);
        //设置内容
        normalDialog.setMessage("我的内容");
        //设置按钮
        normalDialog.setPositiveButton("我的按钮"
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Activity2.this, "点击我的按钮"
                                , Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
        //创建并显示
        mDialog = normalDialog.create();
        mDialog.show();

    }


    public void finishMe() {
        // activity.getWindowManager().removeView(videoDanMuView);  要写在finish前，写在onDestroy中，会窗体泄漏
        finish();
        mDialog.dismiss();
        mDialog = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
