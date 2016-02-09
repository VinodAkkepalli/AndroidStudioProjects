package com.shine.recyclerview.util;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.toolbox.ImageLoader.ImageCache;


/**
 * Created by Vinod Akkepalli
 */
public class LruBitmapCache extends LruCache<String, Bitmap> implements
        ImageCache {

    private static final String TAG = LruBitmapCache.class.getSimpleName();

    public static int getDefaultLruCacheSize() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        Log.d(TAG, "LruCache maxMemory is = " + maxMemory);

        return maxMemory / 8;
    }

    public LruBitmapCache() {
        this(getDefaultLruCacheSize());
    }

    public LruBitmapCache(int sizeInKiloBytes) {
        super(sizeInKiloBytes);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight() / 1024;
    }

    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);
    }
}