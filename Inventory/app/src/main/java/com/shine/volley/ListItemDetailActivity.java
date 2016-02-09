package com.shine.volley;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.shine.volley.R;
import com.shine.volley.app.AppController;

/**
 * Created by Vinod Akkepalli
 */

public class ListItemDetailActivity extends Activity {

    private static final String TAG = ListItemDetailActivity.class.getSimpleName();
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_detail);

        Bundle bundle = getIntent().getExtras();
        Log.d(TAG, bundle.toString());

        TextView titleText = (TextView) findViewById(R.id.dTitle);
        TextView ratingText = (TextView) findViewById(R.id.dRating);
        TextView priceText = (TextView) findViewById(R.id.dFprice);
        NetworkImageView thumbNail = (NetworkImageView) findViewById(R.id.dThumbnail);

        titleText.setText(bundle.getString("title"));
        ratingText.setText("Rating: " + String.valueOf(bundle.getDouble("rating")));
        priceText.setText("Final Price: " + String.valueOf(bundle.getDouble("price")));

        //thumbnail image
        thumbNail.setImageUrl(bundle.getString("imageUrl"), imageLoader);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_item_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
