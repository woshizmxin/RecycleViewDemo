/*
 *     Copyright (c) 2016 Meituan Inc.
 *
 *     The right to copy, distribute, modify, or otherwise make use
 *     of this software may be licensed only pursuant to the terms
 *     of an applicable Meituan license agreement.
 *
 */

package com.marsthink.recycleviewdemo.staggeredgrid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marsthink.recycleviewdemo.R;

import java.util.ArrayList;

class StaggeredGridAdapter extends RecyclerView.Adapter<StaggeredGridAdapter.HomeViewHolder> {
    private Context mContext;
    private ArrayList<String> mDatas;
    private OnItemClickLitener mOnItemClickLitener = null;
    private ArrayList<Integer> mWidth;


    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        this.mOnItemClickLitener = onItemClickLitener;
    }


    public StaggeredGridAdapter(Context context, ArrayList<String> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
        mWidth = new ArrayList<Integer>();
        for (int i = 0; i < mDatas.size(); i++) {
            mWidth.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HomeViewHolder holder = new HomeViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.item_stag, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final HomeViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));
        ViewGroup.LayoutParams lp = holder.tv.getLayoutParams();
        lp.height = mWidth.get(position);
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

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public HomeViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
        }
    }

    public void addData(int position,RecyclerView recyclerView) {
        mWidth.add((int) (100 + Math.random() * 300));
        mDatas.add(position, "Insert One");
        notifyItemInserted(position);
        recyclerView.scrollToPosition(position);//recyclerview滚动到新加item处
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }
}
