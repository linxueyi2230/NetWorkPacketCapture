package com.ecent.capture.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    abstract protected int layout();

    abstract protected void onActivityCreate(Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.beforeSetContentView();
        setContentView(layout());
        this.onActivityCreate(savedInstanceState);
    }

    protected void beforeSetContentView() {

    }

    public void forResult(Class<?> clazz, Bundle extras, int requestCode) {
        Intent mIntent = new Intent(this, clazz);
        if (extras != null) {
            mIntent.putExtras(extras);
        }
        startActivityForResult(mIntent, requestCode);
    }

    public void forResult(Class<?> clazz, int requestCode) {
        forResult(clazz, null, requestCode);
    }

    public void toActivity(Class<?> clazz, Bundle extras) {
        Intent mIntent = new Intent(this, clazz);
        if (extras != null) {
            mIntent.putExtras(extras);
        }
        startActivity(mIntent);
    }

    public void toActivity(Class<?> clazz) {
        toActivity(clazz, null);
    }

    public void toActivity(String action) {
        toActivity(action, null);
    }

    public void toActivity(Intent intent) {
        if (intent == null) {
            return;
        }
        startActivity(intent);
    }

    public void toActivity(String action, Bundle extras) {
        Intent mIntent = new Intent(action);
        if (extras != null) {
            mIntent.putExtras(extras);
        }
        startActivity(mIntent);
    }

    public void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void toast(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    public void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
