package com.example.libs.loader;

import com.google.gson.Gson;

public interface RestClient {

    interface Listener {
        public void onResponse(String s);
    }

    interface ErrorListener {
        public void onErrorResponse(String s);
    }

    public void post(String uri, String body, Listener listener, ErrorListener errorListener);
    public void get(String uri, Listener listener, ErrorListener errorListener);
    public void put(String uri, String body, Listener listener, ErrorListener errorListener);
    public void delete(String uri, Listener listener, ErrorListener errorListener);

    public Gson getGson();
}
