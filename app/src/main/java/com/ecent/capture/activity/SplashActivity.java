package com.ecent.capture.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.ecent.capture.BuildConfig;
import com.ecent.capture.R;
import com.ecent.capture.utils.ESPUtils;
import com.ecent.capture.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lxy
 * @time 2020/6/19 15:03
 */
public class SplashActivity extends BaseActivity {

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
        boolean agree = ESPUtils.getBoolean(this, "agree", false);
        if (agree) {
            launch();
        } else {
            dialog();
        }
    }

    private void launch() {
        if (Build.VERSION.SDK_INT >= 23) {
            checkAndRequestPermission();
        } else {
            this.toMainActivity();
        }
    }

    private void toMainActivity() {
        long delay = 3 * 1000;
        if (BuildConfig.DEBUG) {
            delay = 100;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toActivity(VPNCaptureActivity.class);
                finish();
            }
        }, delay);
    }

    private void dialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_auth, null);
        final AlertDialog dialog = new AlertDialog.Builder(this).setCancelable(false).setView(view).create();

        TextView shadow_title = view.findViewById(R.id.shadow_title);
        TextView shadow_disagree = view.findViewById(R.id.shadow_disagree);
        TextView shadow_agree = view.findViewById(R.id.shadow_agree);
        TextView shadow_privacy_policy = view.findViewById(R.id.shadow_privacy_policy);
        TextView shadow_user_agreement = view.findViewById(R.id.shadow_user_agreement);

        String name = Utils.appName(this);
        shadow_title.setText(String.format("尊敬的用户，感谢您使用%s！在使用前请您务必审阅以下信息", name));

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.shadow_agree) {
                    ESPUtils.setboolean(SplashActivity.this, "agree", true);
                    dialog.dismiss();
                    launch();

                } else if (id == R.id.shadow_disagree) {
                    ESPUtils.setboolean(SplashActivity.this, "agree", false);
                    dialog.dismiss();
                    toMainActivity();

                } else if (id == R.id.shadow_privacy_policy) {
                    AssetsActivity.toPrivacyPolicy(SplashActivity.this);
                } else if (id == R.id.shadow_user_agreement) {
                    AssetsActivity.toUserAgreement(SplashActivity.this);
                }
            }
        };

        shadow_agree.setOnClickListener(click);
        shadow_disagree.setOnClickListener(click);
        shadow_privacy_policy.setOnClickListener(click);
        shadow_user_agreement.setOnClickListener(click);

        dialog.show();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkAndRequestPermission() {
        List<String> lackedPermission = new ArrayList<>();
        if (!checkPermission(Manifest.permission.READ_PHONE_STATE)) {
            lackedPermission.add(Manifest.permission.READ_PHONE_STATE);
        }

        if (!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            lackedPermission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            lackedPermission.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        // 权限都已经有了，那么直接调用SDK
        if (lackedPermission.isEmpty()) {
            toMainActivity();
        } else {
            // 请求所缺少的权限，在onRequestPermissionsResult中再看是否获得权限，如果获得权限就可以调用SDK，否则不要调用SDK。
            String[] permissions = new String[lackedPermission.size()];
            lackedPermission.toArray(permissions);
            ActivityCompat.requestPermissions(this, permissions, 1024);
        }
    }

    private boolean checkPermission(String permission) {
        return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean hasAllPermissionsGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1024 && hasAllPermissionsGranted(grantResults)) {
            toMainActivity();
        } else {
            toMainActivity();
        }
    }
}
