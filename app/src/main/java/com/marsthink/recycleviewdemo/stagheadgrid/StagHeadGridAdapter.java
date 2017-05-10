/*
 *     Copyright (c) 2016 Meituan Inc.
 *
 *     The right to copy, distribute, modify, or otherwise make use
 *     of this software may be licensed only pursuant to the terms
 *     of an applicable Meituan license agreement.
 *
 */

package com.marsthink.recycleviewdemo.stagheadgrid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marsthink.recycleviewdemo.R;

import java.util.ArrayList;

class StagHeadGridAdapter extends RecyclerView.Adapter<StagHeadGridAdapter.HomeViewHolder> {
    private Context mContext;
    private ArrayList<String> mDatas;
    private OnItemClickLitener mOnItemClickLitener = null;
    private ArrayList<Integer> mHeight;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private View mHeaderView;


    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }


    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        this.mOnItemClickLitener = onItemClickLitener;
    }


    public StagHeadGridAdapter(Context context, ArrayList<String> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
        mHeight = new ArrayList<Integer>();
        for (int i = 0; i < mDatas.size()+100; i++) {
            mHeight.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) return new HomeViewHolder(mHeaderView);
        HomeViewHolder holder = new HomeViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.item_stag_head, parent,
                false));
        return holder;
    }


    @Override
    public void onBindViewHolder(final HomeViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;
        holder.tv.setText(mDatas.get(position-1));
        ViewGroup.LayoutParams lp = holder.tv.getLayoutParams();
        lp.height = mHeight.get(position);
        holder.tv.setLayoutParams(lp);

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }

    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }


    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? mDatas.size() : mDatas.size() + 1;
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public HomeViewHolder(View view) {
            super(view);
            if(itemView == mHeaderView) return;
            tv = (TextView) view.findViewById(R.id.id_num);
        }
    }

    public void addData(int position, RecyclerView recyclerView) {
        mHeight.add((int) (100 + Math.random() * 300));
        mDatas.add(position, "Insert One");
        notifyItemInserted(position);
        recyclerView.scrollToPosition(position);//recyclerview滚动到新加item处
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }
}
