package com.example.android_reference;

import com.example.libs.loader.ImageClient;

import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {
    private ImageClient mImageClient;
    private DataManager mDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App app = (App) getApplication();
        mImageClient = app.getImageClient(); // lookup for speed
        mDataManager = app.getDataManager(); // lookup for speed
    }

    public ImageClient getImageClient() {
        return mImageClient;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }
}
