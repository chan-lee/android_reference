package com.example.android_reference.loaders;

public interface DataLoader<T> {
    public interface Listener<T> {
        public void onDataLoaded(T data);
    }
    public void fetch();
    public T getData(boolean autoFetchWhenDidNotFetched, Listener<T> listener);
}
