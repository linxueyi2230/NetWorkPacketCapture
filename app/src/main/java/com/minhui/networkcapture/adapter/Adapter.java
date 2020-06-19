package com.minhui.networkcapture.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * @author lxy
 * @time 2019/6/4 10:21
 */
public abstract class Adapter<Data> extends EAdapter<Data> {

    public Adapter(Context context, List<Data> datas) {
        super(context, datas);
    }

    protected abstract int layout();
    protected abstract void bind(EHolder holder, Data data, int position);

    @NonNull
    @Override
    public EHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return new EHolder(LayoutInflater.from(mContext).inflate(layout(),viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull EHolder holder, int position) {
        Data data = mData.get(position);
        bind(holder,data,position);
        bindingListener(holder.itemView,data,position);
    }
}