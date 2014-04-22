package com.example.android_reference;

import com.example.android_reference.database.DBOpenHelper;
import com.example.android_reference.loaders.ClipDataLoader;
import com.example.libs.loader.RestClient;

public class DataManager {
    private final RestClient mRest;
    private final DBOpenHelper mDBHelper;

    private ClipDataLoader mClipLoader;

    public DataManager(RestClient rest, DBOpenHelper dbHelper) {
        mRest = rest;
        mDBHelper = dbHelper;
    }
    public void init() {
        mClipLoader = new ClipDataLoader(mRest, mDBHelper);
        mClipLoader.fetch();
    }

    public ClipDataLoader getClipDataLoader() {
        return mClipLoader;
    }

    public void invalidateListener() {
        mClipLoader.invalidateListener();
    }
}
