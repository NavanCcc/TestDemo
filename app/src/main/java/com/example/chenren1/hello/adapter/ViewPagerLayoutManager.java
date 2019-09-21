package com.example.chenren1.hello.adapter;


import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.chenren1.hello.utils.LogUtils;

/**
 * RecyclerView的ViewPager效果
 */
public class ViewPagerLayoutManager extends LinearLayoutManager {

    private PagerSnapHelper mPagerSnapHelper;
    private OnViewPagerListener mOnViewPagerListener;
    private int mDrift; //位移，用来判断移动方向

    private int mCurrentScrollPage = 0;

    public ViewPagerLayoutManager(Context context, int orientation) {
        super(context, orientation, false);
        init();
    }

    private void init() {
        mPagerSnapHelper = new PagerSnapHelper();

    }

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        mPagerSnapHelper.attachToRecyclerView(view);
        view.addOnChildAttachStateChangeListener(mChildAttachStateChangeListener);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
    }

    /**
     * 滑动状态的改变
     * 手势拖拽-> SCROLL_STATE_DRAGGING
     * 快速滚动-> SCROLL_STATE_SETTLING
     * 停止状态-> SCROLL_STATE_IDLE
     */
    @Override
    public void onScrollStateChanged(int state) {
        switch (state) {
            case RecyclerView.SCROLL_STATE_IDLE:
                View viewIdle = mPagerSnapHelper.findSnapView(this);
                int positionIdle = getPosition(viewIdle); // 滑动停止下来的位置
                if (mOnViewPagerListener != null && getChildCount() == 1) {
                    mOnViewPagerListener.onPageSelected(positionIdle, positionIdle == getItemCount() - 1);
                }
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING:
                View viewDrag = mPagerSnapHelper.findSnapView(this);
                int positionDrag = getPosition(viewDrag); // 手势拖拽页的位置
                LogUtils.d(" DRAGGING pos:" + positionDrag);
                break;
            case RecyclerView.SCROLL_STATE_SETTLING:
                View viewSettling = mPagerSnapHelper.findSnapView(this);
                int positionSettling = getPosition(viewSettling); // 滑动过程中到的位置
                LogUtils.d(" SETTLING pos:" + positionSettling);
                break;

        }
    }


    /**
     * 监听竖直方向的相对偏移量
     */
    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        this.mDrift = dy;
        return super.scrollVerticallyBy(dy, recycler, state);
    }


    /**
     * 监听水平方向的相对偏移量
     */
    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        this.mDrift = dx;
        return super.scrollHorizontallyBy(dx, recycler, state);
    }

    /**
     * 设置监听
     */
    public void setOnViewPagerListener(OnViewPagerListener listener) {
        this.mOnViewPagerListener = listener;
    }

    private RecyclerView.OnChildAttachStateChangeListener mChildAttachStateChangeListener = new RecyclerView.OnChildAttachStateChangeListener() {
        @Override
        public void onChildViewAttachedToWindow(View view) {
            if (mOnViewPagerListener != null) {
                mOnViewPagerListener.onInitComplete();
            }
        }

        @Override
        public void onChildViewDetachedFromWindow(View view) {
            if (mOnViewPagerListener == null) {
                return;
            }
            if (mDrift >= 0) {
                mOnViewPagerListener.onPageRelease(true, getPosition(view));
            } else {
                mOnViewPagerListener.onPageRelease(false, getPosition(view));
            }

            int childCount = getChildCount();
            if (childCount == 2) {
                View view1 = getChildAt(0);
                View view2 = getChildAt(1);

                // 获取当前展示页面的位置
                int pos = -1;
                if (isDetached(view1)) {
                    pos = getPosition(view2);
                } else if (isDetached(view2)) {
                    pos = getPosition(view1);
                }

                if (pos != -1 && mCurrentScrollPage == pos) {
                    return;
                }
                if (mCurrentScrollPage < pos) {
                    mOnViewPagerListener.onPageScrollNext(pos);
                } else if (mCurrentScrollPage > pos) {
                    mOnViewPagerListener.onPageScrollPrevious(pos);
                }
                mCurrentScrollPage = pos;
            }
        }
    };

    /**
     * 判断 view 是否 Detached
     *
     * @param view
     * @return
     */
    private boolean isDetached(View view) {
        Rect mVisibleRect = new Rect();
        boolean isLocalVisible = view.getLocalVisibleRect(mVisibleRect);
        if (isLocalVisible) {
            if (mVisibleRect.width() == view.getWidth()
                    && mVisibleRect.height() > view.getHeight() * 0.8) {
                return true;
            }
        }
        return false;
    }

    public interface OnViewPagerListener {
        /*初始化完成*/
        void onInitComplete();

        /*释放的监听*/
        void onPageRelease(boolean isNext, int position);

        /*选中的监听以及判断是否滑动到底部*/
        void onPageSelected(int position, boolean isBottom);

        /**
         * 滑动到下一个页面
         */
        void onPageScrollNext(int position);

        /**
         * 滑动到上一个页面
         */
        void onPageScrollPrevious(int position);
    }
}
