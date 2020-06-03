package com.example.chenren1.hello.activity;

import android.app.Activity;
import android.app.PictureInPictureParams;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Rational;
import android.view.View;

import com.example.chenren1.hello.R;

/**
 * PIP
 * Created by chenren1 on 2018/5/21.
 */

public class Activity4 extends Activity {

    private String TAG = "cccc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        findViewById(R.id.btn_pip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterPIP();
            }
        });
    }

    private void enterPIP() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 画中画横纵比例
            Rational aspectRatio = new Rational(16, 9);
            PictureInPictureParams.Builder builder = new PictureInPictureParams.Builder()
                    .setAspectRatio(aspectRatio);
            enterPictureInPictureMode(builder.build());
        }
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        Log.e(TAG, "onPictureInPictureModeChanged ------------------- isInPictureInPictureMode = " + isInPictureInPictureMode);
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
    }

    @Override
    protected void onStart() {
        Log.e(TAG, "onStart ------- isInPictureInPictureMode = " + this.isInPictureInPictureMode());
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.e(TAG, "onResume ------- isInPictureInPictureMode = " + this.isInPictureInPictureMode());
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e(TAG, "onPause ------- isInPictureInPictureMode = " + this.isInPictureInPictureMode());
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.e(TAG, "onStop ------- isInPictureInPictureMode = " + this.isInPictureInPictureMode());
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy ------- isInPictureInPictureMode = " + this.isInPictureInPictureMode());
        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.e(TAG, "onNewIntent ------- isInPictureInPictureMode = " + this.isInPictureInPictureMode());
        super.onNewIntent(intent);
    }
}
