package com.example.chenren1.hello.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chenren1.hello.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 视频弹幕 View
 *
 * @author chenren1
 */
public class MultiplexVideoFullScreenView extends LinearLayout {

    private RecyclerView mRecyclerView;
    private VideoMultiplexListAdapter listAdapter;
    private List<String> mDataList = new ArrayList<>();

    private OnItemVideoClickListener listener;

    public interface OnItemVideoClickListener {
        void onItemVideoClick(int positon);
    }

    public MultiplexVideoFullScreenView(Context context) {
        super(context);
        init(context);
    }

    public MultiplexVideoFullScreenView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MultiplexVideoFullScreenView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_video_fullscreen_multiplex, this);
        mRecyclerView = findViewById(R.id.video_multiplex_recycleview);

        ScrollSpeedLinearLayoutManger layoutManager = new ScrollSpeedLinearLayoutManger(context);
        mRecyclerView.setLayoutManager(layoutManager);
        listAdapter = new VideoMultiplexListAdapter();
        mRecyclerView.setAdapter(listAdapter);

        setData();
    }

    private void setData() {
        for (int i = 0; i < 4; i++) {
            mDataList.add("title");
        }
    }

    class VideoMultiplexListAdapter extends RecyclerView.Adapter<FunctionViewListItemHolder> {


        public VideoMultiplexListAdapter() {
        }

        @Override
        public FunctionViewListItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_multiplex_item_view, parent, false);
            return new FunctionViewListItemHolder(view);
        }

        @Override
        public void onBindViewHolder(FunctionViewListItemHolder holder, int position) {
            holder.bind(position, mDataList.get(position));
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }
    }

    public class FunctionViewListItemHolder extends RecyclerView.ViewHolder {

        private View videoContainer;
        private ImageView itemImage;
        private TextView itemName;

        public FunctionViewListItemHolder(View itemView) {
            super(itemView);
            videoContainer = itemView.findViewById(R.id.item_video_container);
            itemImage = itemView.findViewById(R.id.item_video_cover);
            itemName = itemView.findViewById(R.id.item_video_title);
        }

        public void bind(final int position, String data) {
            itemName.setText(position + " - " + data);
            videoContainer.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRecyclerView.smoothScrollToPosition(position);
                    if (listener != null) {
                        listener.onItemVideoClick(position);
                    }
                }
            });
        }
    }
}
