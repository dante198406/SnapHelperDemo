package com.czm.snaphelperdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.czm.snaphelperdemo.LayoutManager.CenterScrollListener;
import com.czm.snaphelperdemo.LayoutManager.CircleLayoutManager;
import com.czm.snaphelperdemo.LayoutManager.CircleScaleLayoutManager;
import com.czm.snaphelperdemo.LayoutManager.ElevateScaleLayoutManager;
import com.czm.snaphelperdemo.LayoutManager.GalleryLayoutManager;
import com.czm.snaphelperdemo.LayoutManager.RotateLayoutManager;
import com.czm.snaphelperdemo.LayoutManager.ScaleLayoutManager;

import java.util.ArrayList;

public class SnapHelperActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    //CustomRecycleView mRecyclerView;
    ArrayList<String> mData;
    LinearLayoutManager mLayoutManager;
    GallerySnapHelper mGallerySnapHelper;
    SnapHelperAdapter mSnapHelperAdapter;
    private static final int FOCUS_AFTER_DESCENDANTS = 1;//0,1,2
    private final static int CIRCLE = 0;
    private final static int SCROLL_SCALE = 1;
    private final static int CIRCLE_SCALE = 2;
    private final static int GALLERY = 3;
    private final static int ElE_SCALE = 4;
    private final static int ROTATE = 5;
    private int mode = -1;
    //private RecyclerView recyclerView;
    private CircleLayoutManager circleLayoutManager;
    private CircleScaleLayoutManager circleScaleLayoutManager;
    private ScaleLayoutManager scaleLayoutManager;
    private GalleryLayoutManager galleryLayoutManager;
    private ElevateScaleLayoutManager elevateScaleLayoutManager;
    private RotateLayoutManager rotateLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snap_helper);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //mRecyclerView.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);//zhangzhaolei add 焦点优先级是 父亲在后代后面  不加这行会出现焦点有时丢失的问题
        //mRecyclerView.setClipToPadding(false);//zhangzhaolei add
        //mRecyclerView.setClipChildren(false);//zhangzhaolei add
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        elevateScaleLayoutManager = new ElevateScaleLayoutManager(Dp2px(-10));
        galleryLayoutManager = new GalleryLayoutManager(Dp2px(10));
        scaleLayoutManager = new ScaleLayoutManager(Dp2px(10));
        circleLayoutManager = new CircleLayoutManager();
        circleScaleLayoutManager = new CircleScaleLayoutManager();
        mRecyclerView.addOnScrollListener(new CenterScrollListener());
        mRecyclerView.setLayoutManager(elevateScaleLayoutManager);
        circleLayoutManager.setEnableEndlessScroll(true);
        elevateScaleLayoutManager.setEnableEndlessScroll(false);
        initData();
        //mRecyclerView.setAdapter(new SnapHelperAdapter(this, mData));
        mSnapHelperAdapter = new SnapHelperAdapter(this, mData);
        mSnapHelperAdapter.setOnItemClickLitener(new SnapHelperAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("====", "==============position=" + position);
            }
        });
        mSnapHelperAdapter.setOnItemSelectedListener(new SnapHelperAdapter.OnItemSelectedListener() {
            @Override
            public void OnItemSelected(View view, int position) {
                Log.e("====", "============setOnItemSelectedListener==position=" + position);
                //mRecyclerView.smoothToCenter(position);
                //mRecyclerView.getLayoutManager().getChildAt(position).setSelected(true);
                //mRecyclerView.scrollToPosition(position);
                mRecyclerView.smoothScrollToPosition(position);
            }
        });
        mRecyclerView.setAdapter(mSnapHelperAdapter);
        mGallerySnapHelper = new GallerySnapHelper();
        mGallerySnapHelper.attachToRecyclerView(mRecyclerView);
    }

    private int Dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            mData.add("i=" + i);
        }
    }
}
