package com.practice.shine.customlistview.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.practice.shine.customlistview.R;
import com.practice.shine.customlistview.model.School;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomListAdapter extends BaseAdapter{

    private final Activity activity;
    private final List<School> schoolItems;

    public CustomListAdapter(Activity activity, List<School> schoolItems) {
        this.activity = activity;
        this.schoolItems = schoolItems;
    }

    @Override
    public int getCount() {
        return schoolItems.size();
    }

    @Override
    public Object getItem(int position) {
        return schoolItems.get(position);
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
        School school = schoolItems.get(position);

        //thumbnail image
        viewHolder.name.setText(school.getName());
        viewHolder.city.setText("City: " + school.getCity());
        viewHolder.zip.setText("Zip: " + school.getZip());

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
