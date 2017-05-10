/*
 *     Copyright (c) 2016 Meituan Inc.
 *
 *     The right to copy, distribute, modify, or otherwise make use
 *     of this software may be licensed only pursuant to the terms
 *     of an applicable Meituan license agreement.
 *
 */

package com.marsthink.recycleviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.marsthink.recycleviewdemo.grid.GridActivity;
import com.marsthink.recycleviewdemo.list.ListActivity;
import com.marsthink.recycleviewdemo.staggeredgrid.StaggeredGridActivity;
import com.marsthink.recycleviewdemo.stagheadgrid.StagHeadGridActivity;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

   void initView() {
        findViewById(R.id.btn_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
       findViewById(R.id.btn_grid).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, GridActivity.class);
               startActivity(intent);
           }
       });
       findViewById(R.id.btn_stag).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, StaggeredGridActivity.class);
               startActivity(intent);
           }
       });

       findViewById(R.id.btn_stag_head).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, StagHeadGridActivity.class);
               startActivity(intent);
           }
       });
    }

   void initData() {

    }

}
