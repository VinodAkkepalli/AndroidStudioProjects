package com.shine.volley.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

//import com.shine.volley.R;
import com.practice.shine.volley.R;
import com.shine.volley.app.AppController;
import com.shine.volley.model.Inventory;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by Vinod Akkepalli
 */
public class CustomListAdapter extends BaseAdapter{

    // Log tag
    private static final String TAG = CustomListAdapter.class.getSimpleName();

    private Activity activity;
    private LayoutInflater inflater;
    private List<Inventory> inventoryItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<Inventory> inventoryItems) {
        this.activity = activity;
        this.inventoryItems = inventoryItems;
    }

    @Override
    public int getCount() {
        return inventoryItems.size();
    }

    @Override
    public Object getItem(int position) {
        return inventoryItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_row, null);
        }

        if(imageLoader == null){
            imageLoader = AppController.getInstance().getImageLoader();
        }

        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);

        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);
        TextView nRatings = (TextView) convertView.findViewById(R.id.nratings);
        TextView fPrice = (TextView) convertView.findViewById(R.id.fprice);

        //getting movie data for the row
        Inventory m = inventoryItems.get(position);

        //thumbnail image
        thumbNail.setImageUrl(m.getImageUrl(), imageLoader);

        title.setText(m.getTitle());
        rating.setText("Avg. Rating: " + String.valueOf(m.getRating()));
        nRatings.setText("Total Ratings: " + String.valueOf(m.getnRatings()));
        fPrice.setText("Final Price: " + String.valueOf(m.getfPrice()));

        Log.d(TAG, title.getText().toString());
        Log.d(TAG, rating.getText().toString());
        Log.d(TAG, nRatings.getText().toString());
        Log.d(TAG, fPrice.getText().toString());

        return convertView;
    }
}
