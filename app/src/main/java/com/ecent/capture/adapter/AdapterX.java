package com.ecent.capture.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * RecyclerView 多种布局
 * 单布局
 * @see Adapter
 * Created by lxy on 2016/7/5.
 */
public abstract class AdapterX<Data> extends EAdapter<Data> {
    public AdapterX(Context context, List<Data> datas) {
        super(context, datas);
    }

    @Override
    public abstract int getItemViewType(int position);

    protected abstract int layout(int viewType);

    protected abstract void bind(EHolder holder, Data data, int position, int viewType);

    @Override
    public EHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = layout(viewType);
        if (layoutId == invalid){
            return null;
        }
        return new EHolder(LayoutInflater.from(mContext).inflate(layoutId,parent,false));
    }

    @Override
    public void onBindViewHolder(EHolder holder, int position) {
        if (holder == null){
            return;
        }

        int viewType = getItemViewType(position);
        if (viewType == invalid){
            return;
        }
        Data data = mData.get(position);
        bind(holder,data,position,viewType);
        bindingListener(holder.itemView,data,position);
    }

}
