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
public class VideoDanMuView extends LinearLayout {

    private VideoDanMuRecyclerView mRecyclerView;
    private VideoDanMuListAdapter listAdapter;
    private List<String> mDataList = new ArrayList<>();


    public VideoDanMuView(Context context) {
        super(context);
        init(context);
    }

    public VideoDanMuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public VideoDanMuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_video_danmu, this);
        mRecyclerView = findViewById(R.id.video_danmu_recycleview);

        ScrollSpeedLinearLayoutManger layoutManager = new ScrollSpeedLinearLayoutManger(context);
        mRecyclerView.setLayoutManager(layoutManager);
        listAdapter = new VideoDanMuListAdapter();
        mRecyclerView.setAdapter(listAdapter);

        setData();
        listAdapter.setData(mDataList);
        mRecyclerView.setIndex(5);
        mRecyclerView.start();
    }

    private void setData() {
        mDataList.add("0  dqwweifewifub");
        mDataList.add("1  dqwweifewifub");
        mDataList.add("2  dqwweifewifubdqwweifewifubdqwweifewifubdqwweifewifub");
        mDataList.add("3  dqwweifewifub");
        mDataList.add("4  dqwweifewifubdqwweifewifubdqwweifewifubdqwweifewifub");
        mDataList.add("5  dqwweifewifubdqwweifewifubdqwweifewifubdqwweifewifub");
        mDataList.add("6  dqwweifewifub");
        mDataList.add("7  dqwweifewifubdqwweifewifubdqwweifewifubdqwweifewifub");
        mDataList.add("8  dqwweifewifub");
        mDataList.add("9  dqwweifewifub");
        mDataList.add("10 dqwweifewifubdqwweifewifubdqwweifewifubdqwweifewifub");
        mDataList.add("11 dqwweifewifub");
        mDataList.add("12 dqwweifewifubdqwweifewifubdqwweifewifubdqwweifewifub");
        mDataList.add("13 dqwweifewifub");
        mDataList.add("14 dqwweifewifubdqwweifewifubdqwweifewifubdqwweifewifub");
        mDataList.add("15 dqwweifewifub");
    }


    public void addDanmu(String str) {
        mDataList.add(str);
        listAdapter.notifyDataSetChanged();
    }

    class VideoDanMuListAdapter extends RecyclerView.Adapter<FunctionViewListItemHolder> {


        public VideoDanMuListAdapter() {
        }

        @Override
        public FunctionViewListItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_danmu_item_view, parent, false);
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

        public void setData(List<String> mDataList) {
        }
    }

    public class FunctionViewListItemHolder extends RecyclerView.ViewHolder {

        private ImageView itemImage;
        private TextView itemName;

        public FunctionViewListItemHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_horizontal_bg);
            itemName = itemView.findViewById(R.id.item_horizontal_text);
        }

        public void bind(final int position, String data) {
            itemName.setText(position + " - " + data);
        }
    }
}
