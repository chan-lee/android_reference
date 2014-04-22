package com.example.libs.loader;

import android.content.Context;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RestClientVolley implements RestClient {
    private final RequestQueue mRequestQueue;
    private final Gson mGson;

    public RestClientVolley(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
        mGson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS").create();
    }

    @Override
    public void post(String uri, String body, final Listener listener, final ErrorListener errorListener) {
        request(Method.POST, uri, body, listener, errorListener);
    }

    @Override
    public void get(String uri, Listener listener, ErrorListener errorListener) {
        request(Method.GET, uri, null, listener, errorListener);
    }

    @Override
    public void put(String uri, String body, Listener listener, ErrorListener errorListener) {
        request(Method.PUT, uri, body, listener, errorListener);
    }

    @Override
    public void delete(String uri, Listener listener, ErrorListener errorListener) {
        request(Method.DELETE, uri, null, listener, errorListener);
    }

    @Override
    public Gson getGson() {
        return mGson;
    }

    public void request(int method, String uri, String body, final Listener listener, final ErrorListener errorListener) {
        mRequestQueue.add(new JsonStringRequest(method, uri, body, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                listener.onResponse(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                errorListener.onErrorResponse(volleyError.getMessage());
            }
        }));
    }
}
