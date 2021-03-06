package news.agoda.com.sample;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.request.ImageRequest;

import java.util.List;

public class ProductsAdapter extends ArrayAdapter {
    private static class ViewHolder {
        TextView newsTitle;
        DraweeView imageView;
    }

    public ProductsAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProductsEntity productsEntity = (ProductsEntity) getItem(position);
        String thumbnailURL = "";

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item_news, parent, false);
            viewHolder.newsTitle = (TextView) convertView.findViewById(R.id.news_title);
            viewHolder.imageView = (DraweeView) convertView.findViewById(R.id.news_item_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.newsTitle.setText(productsEntity.getAmount());
        DraweeController draweeController = Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequest.fromUri
                (Uri.parse(thumbnailURL))).setOldController(viewHolder.imageView.getController()).build();
        viewHolder.imageView.setController(draweeController);
        return convertView;
    }
}
