package com.example.chenren1.hello.view;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 视频弹幕滚动 RecyclerView
 *
 * @author chenren1
 */
public class VideoDanMuRecyclerView extends RecyclerView {
    private long timeAutoPoll = 2000;
    private int index = 0;
    private int lastY = 0;
    private CountDownTimer mCountDownTimer;

    public VideoDanMuRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置起始位置
     *
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
        scrollToPosition(index);
    }

    public void start() {
        stop();
        if (mCountDownTimer == null) {
            mCountDownTimer = new CountDownTimer(Integer.MAX_VALUE, timeAutoPoll) {
                @Override
                public void onTick(long l) {
                    if (!canScrollNext()) {
                        return;
                    }
                    index++;
                    smoothScrollToPosition(index);
                }

                @Override
                public void onFinish() {
                }
            };
        }
        mCountDownTimer.start();
    }

    public void stop() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    /**
     * 是否可以滚到下一个
     *
     * @return
     */
    private boolean canScrollNext() {
        return (index + 1 < getAdapter().getItemCount());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastY = (int) ev.getRawY();
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE:
                int nowY = (int) ev.getRawY();
                if (nowY != lastY) {
                    return true;
                }
                return super.dispatchTouchEvent(ev);
            case MotionEvent.ACTION_MOVE:
                return true;
        }
        return super.dispatchTouchEvent(ev);
    }
}