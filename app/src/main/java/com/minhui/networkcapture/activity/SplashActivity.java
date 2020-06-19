package com.minhui.networkcapture.activity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.minhui.networkcapture.R;

/**
 * @author lxy
 * @time 2020/6/19 15:03
 */
public class SplashActivity extends BaseActivity{

    @Override
    protected int layout() {
        return R.layout.activity_splash;
    }


    @Override
    protected void beforeSetContentView() {
        super.beforeSetContentView();
        // 全屏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 无标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }


    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {

    }
}
