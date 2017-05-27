package com.practice.shine.customlistview.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.practice.shine.customlistview.R;
import com.practice.shine.customlistview.app.AppController;
import com.practice.shine.customlistview.model.Movie;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vinod Akkepalli on 12/14/14.
 * Added ViewHolder
 * Added Butterknife usage
 */
public class CustomListAdapter extends BaseAdapter{

    private final Activity activity;
    private final List<Movie> movieItems;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<Movie> movieItems) {
        this.activity = activity;
        this.movieItems = movieItems;
    }

    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int position) {
        return movieItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_row, parent, false);
            viewHolder = new MyViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }

        if(imageLoader == null){
            imageLoader = AppController.getInstance().getImageLoader();
        }

        //getting movie data for the row
        Movie m = movieItems.get(position);

        //thumbnail image
        viewHolder.thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
        viewHolder.title.setText(m.getTitle());
        viewHolder.rating.setText(String.format("Rating: %s", String.valueOf(m.getRating())));
        // release year
        viewHolder.year.setText(String.valueOf(m.getYear()));

        String genreStr="";
        for(String str : m.getGenre()){
            genreStr += str + ", ";
        }
        genreStr = genreStr.length() > 0 ? genreStr.substring(0, genreStr.length() - 2) : genreStr;
        viewHolder.genre.setText(genreStr);

        return convertView;
    }

    public static class MyViewHolder {
        @BindView(R.id.title) TextView title;
        @BindView(R.id.rating) TextView rating;
        @BindView(R.id.genre) TextView genre;
        @BindView(R.id.releaseYear) TextView year;
        @BindView(R.id.thumbnail) NetworkImageView thumbNail;

        private MyViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
