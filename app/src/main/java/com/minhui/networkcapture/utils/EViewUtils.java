package com.minhui.networkcapture.utils;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author lxy
 * @version V2.0
 * @date 2020/04/30 14:39
 */
public class EViewUtils {

    public static void vertical(RecyclerView rec) {
        setLinearLayoutManager(rec.getContext(), rec, LinearLayoutManager.VERTICAL);
    }

    public static void vertical(Context context, RecyclerView rec) {
        setLinearLayoutManager(context, rec, LinearLayoutManager.VERTICAL);
    }

    public static void horizontal(RecyclerView rec) {
        setLinearLayoutManager(rec.getContext(), rec, LinearLayoutManager.HORIZONTAL);
    }

    public static void horizontal(Context context, RecyclerView rec) {
        setLinearLayoutManager(context, rec, LinearLayoutManager.HORIZONTAL);
    }

    public static void setLinearLayoutManager(Context context, RecyclerView rec, int orientation) {
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(orientation);
        manager.setSmoothScrollbarEnabled(true);
        rec.setLayoutManager(manager);
    }
}
