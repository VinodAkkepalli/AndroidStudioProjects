package com.shine.recyclerview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.shine.recyclerview.adapter.RecyclerViewAdapter;
import com.shine.recyclerview.app.AppController;
import com.shine.recyclerview.model.Inventory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinod Akkepalli
 */

public class MainActivity extends Activity {

    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // Inventory json url
    private static final String url = "http://www.mysmartprice.com/android_data";
    private ProgressDialog pDialog;
    private RecyclerViewAdapter adapter;
    private List<Inventory> inventoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setTheme(R.style.MyTheme);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        if(savedInstanceState == null){
            adapter = new RecyclerViewAdapter(this, inventoryList);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }


        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Inventory item = (Inventory) parent.getItemAtPosition(position);

                Intent intent = new Intent(view.getContext(), ListItemDetailActivity.class);

                Log.d(TAG, item.toString());

                intent.putExtra("title", item.getTitle());
                intent.putExtra("rating", item.getRating());
                intent.putExtra("price", item.getfPrice());
                intent.putExtra("imageUrl", item.getImageUrl());

                startActivity(intent);
            }
        });*/


        pDialog = new ProgressDialog(this);
        //showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        if(savedInstanceState == null) {
            // Creating volley request obj
            JsonArrayRequest inventoryReq = new JsonArrayRequest(url,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d(TAG, response.toString());
                            hidePDialog();

                            // Parsing json
                            for (int i = 0; i < response.length(); i++) {

                                try {
                                    JSONObject obj = response.getJSONObject(i);
                                    Inventory inventory = new Inventory();
                                    inventory.setTitle(obj.getString("title"));
                                    inventory.setImageUrl(obj.getString("image"));
                                    inventory.setRating(((Number) obj.get("avg_rating"))
                                            .doubleValue());
                                    inventory.setnRatings(obj.getInt("ratings"));
                                    inventory.setfPrice(obj.getDouble("final_price"));

                                    // adding inventory to Inventories array
                                    inventoryList.add(inventory);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            // notifying list adapter about data changes
                            // so that it renders the list view with updated data
                            adapter.notifyDataSetChanged();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    hidePDialog();

                }
            });


            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(inventoryReq);
        }

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("message", "This is my message to be reloaded");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}