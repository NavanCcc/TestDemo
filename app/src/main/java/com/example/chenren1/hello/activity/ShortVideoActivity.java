package com.example.chenren1.hello.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.example.chenren1.hello.R;
import com.example.chenren1.hello.adapter.ShortVideoAdapter;
import com.example.chenren1.hello.adapter.ViewPagerLayoutManager;
import com.example.chenren1.hello.utils.LogUtils;

public class ShortVideoActivity extends Activity {


    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_video);

        mRecyclerView = findViewById(R.id.recycleview);


        ShortVideoAdapter mAdapter = new ShortVideoAdapter();
        mRecyclerView.setAdapter(mAdapter);


        ViewPagerLayoutManager mLayoutManager = new ViewPagerLayoutManager(this, OrientationHelper.VERTICAL);
        mLayoutManager.setOnViewPagerListener(new ViewPagerLayoutManager.OnViewPagerListener() {
            @Override
            public void onInitComplete() {
                LogUtils.d("onInitComplete" );
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                LogUtils.d("onPageRelease  pos:" + position + "  isNext:" + isNext);

            }

            @Override
            public void onPageSelected(int position, boolean isBottom) {
                LogUtils.d("onPageSelected  pos:" + position + "  isBottom:" + isBottom);

            }
            @Override
            public void onPageScrollNext(int position) {
                LogUtils.d("onPageScrollNext  pos:" + position );
            }

            @Override
            public void onPageScrollPrevious(int position) {
                LogUtils.d("onPageScrollPrevious  pos:" + position );
            }
        });
        mRecyclerView.setLayoutManager(mLayoutManager);
    }
}
