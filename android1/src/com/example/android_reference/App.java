package com.example.android_reference;

import com.example.android_reference.database.DBOpenHelper;
import com.example.libs.loader.ImageClient;
import com.example.libs.loader.ImageClientAUIL;
import com.example.libs.loader.RestClient;
import com.example.libs.loader.RestClientVolley;

import android.app.Application;

public class App extends Application {

    static {
        com.android.volley.VolleyLog.DEBUG = true;
    }

    private ImageClient mImageClient;
    private RestClient mRestClient;
    private DBOpenHelper mDBHelper;
    private DataManager mDataManager;

    @Override public void onCreate() {
        super.onCreate();
        mImageClient = new ImageClientAUIL(getApplicationContext());
        mRestClient = new RestClientVolley(getApplicationContext());
        mDBHelper = new DBOpenHelper(getApplicationContext());
        mDataManager = new DataManager(mRestClient, mDBHelper);
        mDataManager.init();
    }

    public ImageClient getImageClient() {
        return mImageClient;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }
}
