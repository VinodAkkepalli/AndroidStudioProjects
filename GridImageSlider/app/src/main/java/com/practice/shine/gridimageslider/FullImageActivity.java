package com.practice.shine.gridimageslider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by Vinod Akkepalli on 12/04/14.
 */
public class FullImageActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullimage_layout);

        Intent intent = getIntent();

        int position = intent.getExtras().getInt("id");
        ImageAdapter imageAdapter = new ImageAdapter(this);

        ImageView imageView = (ImageView) findViewById(R.id.full_screen_image);
        imageView.setImageResource(imageAdapter.imageArray[position]);
    }
}
