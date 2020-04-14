package com.example.chenren1.hello.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.chenren1.hello.R;
import com.example.chenren1.hello.view.VideoDanMuView;

/**
 * HorizontalRecycleView
 * Created by chenren1 on 2018/5/21.
 */

public class Activity3 extends Activity {

    VideoDanMuView videoDanMuView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        videoDanMuView = findViewById(R.id.video_danmu);
        findViewById(R.id.video_danmu_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoDanMuView.addDanmu("1  add ");
            }
        });


    }
}
