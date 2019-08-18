package com.exercise.postslist;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.exercise.postslist.adapter.CustomListAdapter;
import com.exercise.postslist.app.AppController;
import com.exercise.postslist.model.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends Activity {

    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    //Posts json url
    private static final String url = "https://jsonplaceholder.typicode.com/posts";
    private ProgressDialog pDialog;
    private final List<Post> postList = new ArrayList<>();
    private CustomListAdapter adapter;
    @BindView(R.id.list)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new CustomListAdapter(this, postList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        //showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        // Creating volley request obj
        JsonArrayRequest postReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject postObj = response.getJSONObject(i);
                                Post post = new Post();
                                post.setId(postObj.getString("id"));
                                post.setUserId(postObj.getString("userId"));
                                post.setTitle(postObj.getString("title"));
                                post.setBody(postObj.getString("body"));
                                // adding post to post array
                                postList.add(post);

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Post postItem = postList.get(position);

                String postId = postItem.getId();
                String userId = postItem.getUserId();
                String title = postItem.getTitle();
                String body = postItem.getBody();

                Intent intent = new Intent(MainActivity.this, DetailViewActivity.class);
                intent.putExtra(DetailViewActivity.ARG_ID, postId);
                intent.putExtra(DetailViewActivity.ARG_USERID, userId);
                intent.putExtra(DetailViewActivity.ARG_TITLE, title);
                intent.putExtra(DetailViewActivity.ARG_BODY, body);

                startActivity(intent);
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(postReq);
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
