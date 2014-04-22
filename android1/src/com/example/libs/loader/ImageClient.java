package com.example.libs.loader;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

public interface ImageClient {
    public interface Listener {
        public void onLoaded(String imageUri, View view, Bitmap loadedImage);
    }
    public void display(String imageUri, ImageView imageView);
    public void load(String imageUri, final Listener listener);
}
