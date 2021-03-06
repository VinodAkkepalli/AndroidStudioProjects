package news.agoda.com.sample;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private List<ProductsEntity> newsItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);

        newsItemList = new ArrayList<>();

        //moved the json object fetch logic to an AsyncTask called FetchProductsTask
        FetchProductsTask newsTask = new FetchProductsTask();
        newsTask.execute();

        ProductsAdapter adapter = new ProductsAdapter(this, R.layout.list_item_news, newsItemList);
        setListAdapter(adapter);

        ListView listView = getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductsEntity productsEntity = newsItemList.get(position);
                String title = productsEntity.getAmount();
                Intent intent = new Intent(MainActivity.this, DetailViewActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("summary", productsEntity.getSku());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /*private String loadResource() {
        InputStream inputStream = getResources().openRawResource(R.raw.news_list);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];

        try {
            InputStreamReader inputReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferReader = new BufferedReader(inputReader);
            int n;
            while ((n = bufferReader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
            inputStream.close();
        } catch (IOException ioException) {
            return null;
        }

        return writer.toString();
    }*/


    //AsyncTask to perform time consuming operations such as fetching data over network
    //In our case, the task is simplified as the json is available resource/raw folder
    public class FetchProductsTask extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... params) {

            InputStream productsStream = getResources().openRawResource(R.raw.transactions);
            Writer writer = new StringWriter(Integer.MAX_VALUE);
            char[] buffer = new char[1024];

            try {
                InputStreamReader inputReader = new InputStreamReader(productsStream, "UTF-8");
                BufferedReader bufferReader = new BufferedReader(inputReader);
                int n;
                while ((n = bufferReader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
                productsStream.close();
            } catch (IOException ioException) {
                return null;
            }

            Log.d(TAG, writer.toString());
            return writer.toString();
        }

        @Override
        protected void onPostExecute(String newsListSource) {
            super.onPostExecute(newsListSource);

            JSONObject jsonObject;

            try {
                jsonObject = new JSONObject(newsListSource);
                JSONArray resultArray = jsonObject.getJSONArray("results");
                for (int i = 0; i < resultArray.length(); i++) {
                    JSONObject newsObject = resultArray.getJSONObject(i);
                    ProductsEntity productsEntity = new ProductsEntity(newsObject);
                    newsItemList.add(productsEntity);
                }
            } catch (JSONException e) {
                Log.e(TAG, "fail to parse json string");
            }

        }
    }
}
