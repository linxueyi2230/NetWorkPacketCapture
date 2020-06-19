package com.ecent.capture;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ecent.capture.activity.BaseActivity;


/**
 * @author e
 * @datetime
 */
public abstract class BaseFragment<A extends BaseActivity> extends Fragment {

    protected A mActivity;
    protected View mContentView;

    protected abstract int layout();
    protected abstract void created(View contentView, Bundle bundle);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, final @Nullable Bundle savedInstanceState) {

        if (mContentView == null) {
            mContentView = inflater.inflate(layout(), container, false);
            mContentView.setClickable(true);
            created(mContentView, savedInstanceState);
        } else {

            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (null != parent) {
                parent.removeAllViewsInLayout();
            }
        }

        return mContentView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (A)activity;
    }

    public  <V extends View> V find(int id) {
        return mContentView.findViewById(id);
    }

    public  <V extends View> V findViewById(int id) {
        return mContentView.findViewById(id);
    }

    protected A activity() {
        return mActivity;
    }

    protected void toast(String toast){
        Toast.makeText(getContext(),toast,Toast.LENGTH_SHORT).show();
    }

    protected void toast(int toast){
        Toast.makeText(getContext(),toast,Toast.LENGTH_SHORT).show();
    }

    public void forResult(Class<?> clazz, Bundle extras, int requestCode) {
        Intent mIntent = new Intent(this.getContext(), clazz);
        if (extras != null) {
            mIntent.putExtras(extras);
        }
        startActivityForResult(mIntent, requestCode);
    }

    public void forResult(Class<?> clazz, int requestCode) {
        forResult(clazz,null,requestCode);
    }

    public void toActivity(Class<?> clazz, Bundle extras) {
        Intent mIntent = new Intent(this.getContext(), clazz);
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


}
