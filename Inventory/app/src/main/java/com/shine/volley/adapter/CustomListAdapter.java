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
import com.shine.volley.R;
import com.shine.volley.app.AppController;
import com.shine.volley.model.Inventory;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by Vinod Akkepalli
 */
public class CustomListAdapter extends BaseAdapter{

    //ViewHolder pattern for smooth scrolling of the listview
    private static class ViewHolder{
        NetworkImageView thumbNail;
        TextView title;
        TextView rating;
        TextView nRatings;
        TextView fPrice;
    }

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

        ViewHolder viewHolder;

        if(inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_row, null);
            viewHolder = new ViewHolder();

            viewHolder.thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.rating = (TextView) convertView.findViewById(R.id.rating);
            viewHolder.nRatings = (TextView) convertView.findViewById(R.id.nratings);
            viewHolder.fPrice = (TextView) convertView.findViewById(R.id.fprice);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(imageLoader == null){
            imageLoader = AppController.getInstance().getImageLoader();
        }

        Inventory m = inventoryItems.get(position);

        if(m != null) {
            viewHolder.thumbNail.setImageUrl(m.getImageUrl(), imageLoader);
            viewHolder.title.setText(m.getTitle());
            viewHolder.rating.setText("Avg. Rating: " + String.valueOf(m.getRating()));
            viewHolder.nRatings.setText("Total Ratings: " + String.valueOf(m.getnRatings()));
            viewHolder.fPrice.setText("Final Price: " + String.valueOf(m.getfPrice()));
        }

        Log.d(TAG, viewHolder.title.getText().toString());
        Log.d(TAG, viewHolder.rating.getText().toString());
        Log.d(TAG, viewHolder.nRatings.getText().toString());
        Log.d(TAG, viewHolder.fPrice.getText().toString());

        return convertView;
    }
}