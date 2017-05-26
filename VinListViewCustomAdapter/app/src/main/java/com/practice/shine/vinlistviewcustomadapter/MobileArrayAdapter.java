package com.practice.shine.vinlistviewcustomadapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vinod Akkepalli on 12/13/14.
 */
class MobileArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    MobileArrayAdapter(Context context, String[] values) {
        super(context, R.layout.activity_list_mobile, values);
        this.context = context;
        this.values = values;
        ButterKnife.bind((Activity) context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.activity_list_mobile, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }


        String s = values[position];
        holder.textView.setText(s);

        switch (s){
            case "Android":
                holder.imageView.setImageResource(R.drawable.android);
                break;
            case "iOS":
                holder.imageView.setImageResource(R.drawable.ios);
                break;
            case "WindowsMobile":
                holder.imageView.setImageResource(R.drawable.windows);
                break;
            default:
                holder.imageView.setImageResource(R.drawable.blackberry);
        }

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.name) TextView textView;
        @BindView(R.id.logo) ImageView imageView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
