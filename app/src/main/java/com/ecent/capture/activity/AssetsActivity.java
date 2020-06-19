package com.ecent.capture.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ecent.capture.R;
import com.ecent.capture.utils.Utils;


/**
 * @author lxy
 * @time 2019/11/14 12:39
 */
public class AssetsActivity extends BaseActivity {

    public static void toPrivacyPolicy(Activity activity) {
        assets(activity, 0);
    }

    public static void toUserAgreement(Activity activity) {
        assets(activity, 1);
    }

    public static void assets(Activity activity, int type) {
        Intent intent = new Intent(activity, AssetsActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    @Override
    protected int layout() {
        return R.layout.activity_assets;
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        findViewById(R.id.tv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String title = null;
        String content = null;

        int type = getIntent().getIntExtra("type", 0);
        if (type == 0) {
            title = "隐私政策";
            content = Utils.assets(this, "privacy_policy.txt");

        } else {
            title = "用户协议";
            content = Utils.assets(this, "user_agreement.txt");
        }

        TextView tv_title = findViewById(R.id.tv_title);
        TextView tv_content = findViewById(R.id.tv_content);

        tv_title.setText(title);
        tv_content.setText(content);
    }
}
