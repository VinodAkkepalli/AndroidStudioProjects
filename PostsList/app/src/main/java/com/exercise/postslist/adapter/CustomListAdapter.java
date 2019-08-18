package com.exercise.postslist.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.exercise.postslist.R;
import com.exercise.postslist.model.Post;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomListAdapter extends BaseAdapter{

    private final Activity activity;
    private final List<Post> postItems;

    public CustomListAdapter(Activity activity, List<Post> postItems) {
        this.activity = activity;
        this.postItems = postItems;
    }

    @Override
    public int getCount() {
        return postItems.size();
    }

    @Override
    public Object getItem(int position) {
        return postItems.get(position);
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

        //getting movie data for the row
        Post post = postItems.get(position);

        //thumbnail image
        viewHolder.name.setText(post.getId());
        viewHolder.city.setText("UserId: " + post.getUserId());
        viewHolder.zip.setText("Title: " + post.getTitle());

        return convertView;
    }

    public static class MyViewHolder {
        @BindView(R.id.list_name) TextView name;
        @BindView(R.id.list_city) TextView city;
        @BindView(R.id.list_zip) TextView zip;

        private MyViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
