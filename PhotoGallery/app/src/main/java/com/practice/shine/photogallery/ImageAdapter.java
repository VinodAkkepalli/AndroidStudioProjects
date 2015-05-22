package com.practice.shine.photogallery;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Vinod Akkepalli on 12/02/14.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    
    public Integer[] imageArray = {
        R.drawable.img_1,R.drawable.img_2, R.drawable.img_3, R.drawable.img_4, R.drawable.img_5, R.drawable.img_6,
        R.drawable.img_7, R.drawable.img_8, R.drawable.img_9, R.drawable.img_10, R.drawable.img_11, R.drawable.img_12,
        R.drawable.img_13, R.drawable.img_14, R.drawable.img_15, R.drawable.img_16, R.drawable.img_17, R.drawable.img_18,
        R.drawable.img_19, R.drawable.img_20, R.drawable.img_21, R.drawable.img_22
    };

    public ImageAdapter(Context Context) {
        this.mContext = Context;
    }

    @Override
    public int getCount() {
        return imageArray.length;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return imageArray[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(imageArray[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(70,70));
        return imageView;
    }
}
