package com.example.android_reference.loaders;

import java.lang.reflect.Type;
import java.util.ArrayList;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.android_reference.Config;
import com.example.android_reference.database.DBOpenHelper;
import com.example.android_reference.database.ClipTable;
import com.example.android_reference.models.Clip;
import com.example.libs.loader.RestClient;

public class ClipDataLoader extends AbstractDataLoader<ArrayList<Clip>>{

    public ClipDataLoader(RestClient rest, DBOpenHelper dbHelper) {
        super(rest, new ClipTable(dbHelper));
    }

    @Override
    public void fetch() {
        setStatus(STATUS_FETCH);
        mRest.get(Config.API_URL_CLIPS, new RestClient.Listener() {
            @Override
            public void onResponse(String s) {
                Type arrayType = new TypeToken<ArrayList<Clip>>(){}.getType();
                Gson gson = mRest.getGson();
                ArrayList<Clip> updatedList = gson.fromJson(s, arrayType);

                mTable.updateData(updatedList);
                setStatus(STATUS_DONE);
                if (mPendingListener != null) {
                    mPendingListener.onDataLoaded(updatedList);
                    mPendingListener = null;
                }
            }
        }, new RestClient.ErrorListener() {
            @Override
            public void onErrorResponse(String s) {
                // Retry or fail
                Log.e("rest clips", " " + s);
                setStatus(STATUS_DONE);
            }
        });

    }

}
