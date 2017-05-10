/*
 *     Copyright (c) 2016 Meituan Inc.
 *
 *     The right to copy, distribute, modify, or otherwise make use
 *     of this software may be licensed only pursuant to the terms
 *     of an applicable Meituan license agreement.
 *
 */

package com.marsthink.recycleviewdemo.list;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.marsthink.recycleviewdemo.R;

import java.util.ArrayList;

public class ListActivity extends Activity {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layout = new LinearLayoutManager(this);
    ArrayList<String> mDatas = new ArrayList<String>();
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initView();
        initData();
    }

    void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.view_recycle);
        //设置布局管理器
        mRecyclerView.setLayoutManager(layout);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
    }

    void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
        adapter = new ListAdapter(this, mDatas);
        mRecyclerView.setAdapter(adapter);
    }
}
