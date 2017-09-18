package com.czm.snaphelperdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class SnapHelperActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ArrayList<String> mData;
    LinearLayoutManager mLayoutManager;
    GallerySnapHelper mGallerySnapHelper;
    SnapHelperAdapter mSnapHelperAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snap_helper);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        initData();
        //mRecyclerView.setAdapter(new SnapHelperAdapter(this, mData));
        mSnapHelperAdapter = new SnapHelperAdapter(this, mData);
        mSnapHelperAdapter.setOnItemClickLitener(new SnapHelperAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("====", "==============position=" + position);
            }
        });
        mRecyclerView.setAdapter(mSnapHelperAdapter);
        mGallerySnapHelper = new GallerySnapHelper();
        mGallerySnapHelper.attachToRecyclerView(mRecyclerView);
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            mData.add("i=" + i);
        }
    }
}
