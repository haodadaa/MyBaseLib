package com.hao.mybaselib.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hao on 2017/3/1.
 */

public abstract class BaseRecyclerAdapter<T, H extends RecyclerView.ViewHolder> extends
        RecyclerView.Adapter<H> {

    protected List<T> mList = new ArrayList<>();
    protected LayoutInflater mInflater;
    protected View mView;

    protected AdapterView.OnItemClickListener onItemClickListener;

    public BaseRecyclerAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public BaseRecyclerAdapter(Context context, List<T> list) {
        this.mInflater = LayoutInflater.from(context);
        this.mList = list;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    protected void onItemHolderClick(RecyclerView.ViewHolder itemHolder) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(null, itemHolder.itemView,
                    itemHolder.getAdapterPosition(), itemHolder.getItemId());
        } else {
            throw new IllegalStateException("Please call setOnItemClickListener method set the click event listeners");
        }
    }

    public void setData(List<T> items) {
        mList.clear();
        mList.addAll(items);
        notifyDataSetChanged();
    }

    public void addData(List<T> items) {
        mList.addAll(items);
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return mList;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }
}
