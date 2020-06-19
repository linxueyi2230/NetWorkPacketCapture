package com.ecent.capture.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author minhui.zhu
 *         Created by minhui.zhu on 2018/5/5.
 *         Copyright © 2017年 Oceanwing. All rights reserved.
 */

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(getLayout(), container, false);
        return layout;
    }

    abstract int getLayout();

    public void onVisible() {

    }

    public void onInVisible() {

    }
}
