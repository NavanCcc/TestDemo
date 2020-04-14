package com.example.chenren1.hello.view;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.chenren1.hello.utils.DensityUtil;
import com.example.chenren1.hello.utils.ScreenUtil;

public class ScrollSpeedLinearLayoutManger extends LinearLayoutManager {
    private float MILLISECONDS_PER_INCH = 0.03f;
    private Context mContext;
    private boolean mIsTopicCard;
    private float mScreenScale;

    public ScrollSpeedLinearLayoutManger(Context context) {
        super(context);
        this.mContext = context;
    }

    public ScrollSpeedLinearLayoutManger(Context context, boolean isTopicCard) {
        super(context);
        this.mContext = context;
        this.mIsTopicCard = isTopicCard;
        mScreenScale = ScreenUtil.getScreenHeight(mContext) / ScreenUtil.getScreenWidth(mContext);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {
            @Override
            public PointF computeScrollVectorForPosition(int targetPosition) {
                return ScrollSpeedLinearLayoutManger.this.computeScrollVectorForPosition(targetPosition);
            }

            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                setSpeedSlow();
                //返回滑动一个pixel需要多少毫秒
                return MILLISECONDS_PER_INCH / displayMetrics.density;
            }
        };
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }

    public void setSpeedSlow() {
        MILLISECONDS_PER_INCH = mContext.getResources().getDisplayMetrics().density * 3f;
    }

    public void setSpeedFast() {
        MILLISECONDS_PER_INCH = mContext.getResources().getDisplayMetrics().density * 0.03f;
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        if (mIsTopicCard) {
            handleTopicCard(widthSpec, heightSpec);
        } else {
            super.onMeasure(recycler, state, widthSpec, heightSpec);
        }
    }

    /**
     * 处理topicCard中RecyclerView的高度
     */
    private void handleTopicCard(int widthSpec, int heightSpec) {
        int width = View.MeasureSpec.getSize(widthSpec);
        int height = View.MeasureSpec.getSize(heightSpec);

        int marginBottom = mScreenScale < 1.9 ? DensityUtil.dip2px(40) : DensityUtil.dip2px(45);
        int targetHeight = height - marginBottom;

        if (targetHeight < DensityUtil.dip2px(3 * 44)) {
            setMeasuredDimension(width, DensityUtil.dip2px(2 * 44));
        } else {
            setMeasuredDimension(width, DensityUtil.dip2px(3 * 44));
        }
    }
}
