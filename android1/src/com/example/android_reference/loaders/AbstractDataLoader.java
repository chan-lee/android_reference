package com.example.android_reference.loaders;

import com.example.android_reference.database.Table;
import com.example.libs.loader.RestClient;

public abstract class AbstractDataLoader<T> implements DataLoader<T> {
    public static final int STATUS_NEVER_FETCHED = 0;
    public static final int STATUS_FETCH = 1;
    public static final int STATUS_DONE = 2;
    private int mStatus = STATUS_NEVER_FETCHED;

    protected RestClient mRest;
    protected Table<T> mTable;
    protected Listener<T> mPendingListener = null;

    public AbstractDataLoader(RestClient rest, Table<T> table) {
        mRest = rest;
        mTable = table;
    }

    @Override
    public T getData(boolean autoFetchWhenDidNotFetched, Listener<T> listener) {
        // auto fetch when the data wasn't fetched
        if (autoFetchWhenDidNotFetched && getStatus() == STATUS_NEVER_FETCHED) {
            mPendingListener = listener;
            fetch();
        }

        if (getStatus() == STATUS_FETCH) {
            mPendingListener = listener;
        }
        return mTable.getAllData();
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public int getStatus() {
        return mStatus;
    }

    // always invalidate
    public void invalidateListener() {
        mPendingListener = null;
    }

    // to prevent invalidating other fragment's listener
    /*
    public void invalidateListenerIfMatch(Listener<T> listener) {
        if (listener == mPendingListener) {
            mPendingListener = null;
        }
    }
    */
}
