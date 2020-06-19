package com.minhui.networkcapture.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by lxy on 2016/7/5.
 */
public abstract class EAdapter<Data> extends RecyclerView.Adapter<EAdapter.EHolder> {

    protected int invalid = -1;
    protected List<Data> mData;
    protected Context mContext;
    protected Listener<Data> mListener;

    public EAdapter(Context context, List<Data> datas) {
        this.mContext = context;
        this.mData = datas;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public List<Data> get() {
        return mData;
    }

    public void set(List<Data> data) {
        this.mData = data;
    }

    public Data item(int position) {
        return mData.get(position);
    }

    public void remove(Data data) {
        if (mData != null && data != null) {
            int position = mData.indexOf(data);
            if (position == -1) {
                return;
            }
            remove(position);
        }
    }

    public void remove(int position) {

        if (mData != null) {
            if (position < 0 || position >= mData.size()) {
                return;
            }

            mData.remove(position);
            notifyItemRemoved(position);
            if (position != mData.size()) {
                notifyItemRangeChanged(position, mData.size() - position);
            }
        }
    }

    public void refresh(List<Data> list) {
        mData.clear();
        if (list != null && !list.isEmpty()) {
            mData.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void append(Data data) {
        if (mData != null && data != null) {
            mData.add(data);
            notifyItemInserted(mData.size() - 1);
        }
    }

    public void append(List<Data> list) {
        if (list != null && !list.isEmpty()) {
            int start = mData.size() - 1;
            int itemCount = list.size();
            mData.addAll(list);
            notifyItemRangeInserted(start, itemCount);
        }
    }

    public boolean isEmpty() {
        if (mData == null) {
            return true;
        }
        return mData.isEmpty();
    }

    public void clear() {
        if (mData != null) {
            mData.clear();
            notifyDataSetChanged();
        }
    }

    public void setListener(Listener<Data> listener) {
        this.mListener = listener;
    }

    protected void bindingListener(final View view, final Data data, final int position) {
        if (mListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.click(view, data, position);
                }
            });
        }

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mListener != null) {
//                    mListener.click(view, data, position);
//                }
//            }
//        });
    }

    public static class EHolder extends RecyclerView.ViewHolder {

        public EHolder(View itemView) {
            super(itemView);
        }

        public <V extends View> V find(int id) {
            return itemView.findViewById(id);
        }

        public TextView text(int id) {
            return itemView.findViewById(id);
        }

        public ImageView image(int id) {
            return itemView.findViewById(id);
        }

    }

    public interface Listener<Data> {
        void click(View view, Data data, int position);
    }
}
