package com.practice.shine.vinlistviewcustomadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Vinod Akkepalli on 12/13/14.
 */
public class MobileArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public MobileArrayAdapter(Context context, String[] values) {
        super(context, R.layout.activity_list_mobile, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.activity_list_mobile, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.name);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
        String s = values[position];
        textView.setText(s);

        if(s.equals("Android")){
            imageView.setImageResource(R.drawable.android);
        }else if(s.equals("iOS")){
            imageView.setImageResource(R.drawable.ios);
        }else if(s.equals("WindowsMobile")){
            imageView.setImageResource(R.drawable.windows);
        }else if(s.equals("Blackberry")){
            imageView.setImageResource(R.drawable.blackberry);
        }

        return rowView;
    }
}
