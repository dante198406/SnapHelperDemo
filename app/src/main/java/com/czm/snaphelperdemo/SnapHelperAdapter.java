package com.czm.snaphelperdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by chenzhimao on 17-7-6.
 */

public class SnapHelperAdapter extends RecyclerView.Adapter<SnapHelperAdapter.GalleryViewHoler> {
    LayoutInflater mInflater;
    ArrayList<String> mData;
    int[] imgs = new int[]{R.drawable.jdzz, R.drawable.ccdzz, R.drawable.dfh, R.drawable.dlzs, R.drawable.sgkptt, R.drawable.ttxss, R.drawable.zmq, R.drawable.zzhx};


    public SnapHelperAdapter(Context context, ArrayList<String> data) {
        mInflater = LayoutInflater.from(context);
        mData = data;

    }

    @Override
    public GalleryViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.gallery_item_layout, parent, false);

        return new GalleryViewHoler(view);
    }

    @Override
    public void onBindViewHolder(final GalleryViewHoler holder, final int position) {
        holder.mImageView.setImageResource(imgs[position % 8]);
        holder.mTextView.setText(mData.get(position));
        holder.itemView.setFocusable(true);//zhangzhaolei +
        holder.itemView.setTag(position);//zhangzhaolei +
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(holder.itemView, position);
                }
            });
        }

        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                Log.e("====", "hasfocus:" + position + "--" + hasFocus);
                if (hasFocus) {
                    /*if (mOnItemSelectedListener != null) {
                        mOnItemSelectedListener.OnItemSelected(holder.itemView, position);
                    }*/
                    int currentPosition = (int) holder.itemView.getTag();
                    mOnItemSelectedListener.OnItemSelected(holder.itemView, currentPosition);
                    Log.e("====", "============currentPosition=" + currentPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnItemSelectedListener {
        void OnItemSelected(View view, int position);
    }

    private OnItemSelectedListener mOnItemSelectedListener;

    public void setOnItemSelectedListener(OnItemSelectedListener mOnItemSelectedListener) {
        this.mOnItemSelectedListener = mOnItemSelectedListener;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    class GalleryViewHoler extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView;

        public GalleryViewHoler(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image);
            mTextView = (TextView) itemView.findViewById(R.id.tv_num);
        }
    }
}
