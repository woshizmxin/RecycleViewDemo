/*
 *     Copyright (c) 2016 Meituan Inc.
 *
 *     The right to copy, distribute, modify, or otherwise make use
 *     of this software may be licensed only pursuant to the terms
 *     of an applicable Meituan license agreement.
 *
 */

package com.marsthink.recycleviewdemo.staggeredgrid;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.marsthink.recycleviewdemo.R;

import java.util.ArrayList;

public class StaggeredGridActivity extends Activity {
    RecyclerView mRecyclerView;
    //    RecyclerView.LayoutManager layout = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
    RecyclerView.LayoutManager layout = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);
    ArrayList<String> mDatas = new ArrayList<String>();
    StaggeredGridAdapter adapter;

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
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
        adapter = new StaggeredGridAdapter(this, mDatas);
        adapter.setOnItemClickLitener(new StaggeredGridAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(StaggeredGridActivity.this, "position" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(StaggeredGridActivity.this, "position" + position, Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_action_add:
                adapter.addData(1, mRecyclerView);
                break;
            case R.id.id_action_delete:
                adapter.removeData(1);
                break;
        }
        return true;
    }
}
