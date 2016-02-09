package com.shine.recyclerview.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.shine.recyclerview.ListItemDetailActivity;
import com.shine.recyclerview.R;
import com.shine.recyclerview.app.AppController;
import com.shine.recyclerview.model.Inventory;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

import static android.content.Context.CONTEXT_IGNORE_SECURITY;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.shine.recyclerview.R.layout.list_row;

/**
 * Created by Vinod Akkepalli on 11/14/2015.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<Inventory> inventoryItems = Collections.emptyList();
    private LayoutInflater inflater;
    private Activity activity;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private static String TAG = RecyclerViewAdapter.class.getSimpleName();

    public RecyclerViewAdapter(Context context, List<Inventory> inventoryItems){
        inflater = LayoutInflater.from(context);
        this.inventoryItems = inventoryItems;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_row, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Inventory currentItem = inventoryItems.get(position);
        holder.thumbNail.setImageUrl(currentItem.getImageUrl(), imageLoader);
        holder.title.setText(currentItem.getTitle());
        holder.fPrice.setText(String.valueOf(currentItem.getfPrice()));
        holder.nRatings.setText(String.valueOf(currentItem.getnRatings()));
        holder.rating.setText(String.valueOf(currentItem.getRating()));

    }


    @Override
    public int getItemCount() {
        return inventoryItems.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        NetworkImageView thumbNail;
        TextView title;
        TextView rating;
        TextView nRatings;
        TextView fPrice;

        public MyViewHolder(View itemView) {
            super(itemView);

            thumbNail = (NetworkImageView) itemView.findViewById(R.id.thumbnail);
            title = (TextView) itemView.findViewById(R.id.title);
            rating = (TextView) itemView.findViewById(R.id.rating);
            nRatings = (TextView) itemView.findViewById(R.id.nratings);
            fPrice = (TextView) itemView.findViewById(R.id.fprice);

        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "Element " + getPosition() + " clicked.");

            Intent intent = new Intent(view.getContext(), ListItemDetailActivity.class);

            intent.putExtra("title", String.valueOf(title));
            intent.putExtra("rating", String.valueOf(rating));
            intent.putExtra("price", String.valueOf(fPrice));
            intent.putExtra("imageUrl", String.valueOf(thumbNail));

            startActivity(intent);
        }
    }
}
