package com.practice.shine.customlistview;

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
import com.practice.shine.customlistview.adapter.CustomListAdapter;
import com.practice.shine.customlistview.app.AppController;
import com.practice.shine.customlistview.model.School;

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

    //Schools json url
    private static final String url = "https://data.cityofnewyork.us/resource/s3k6-pzi2.json";
    private ProgressDialog pDialog;
    private final List<School> schoolList = new ArrayList<>();
    private CustomListAdapter adapter;
    @BindView(R.id.list) ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new CustomListAdapter(this, schoolList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        //showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        // Creating volley request obj
        JsonArrayRequest schoolReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject schoolObj = response.getJSONObject(i);
                                School school = new School();
                                school.setName(schoolObj.getString("school_name"));
                                school.setDbn(schoolObj.getString("dbn"));
                                school.setCity(schoolObj.getString("city"));
                                school.setEmail(schoolObj.has("school_email")?
                                        schoolObj.getString("school_email") : "No Email");
                                school.setLocation(schoolObj.getString("location"));
                                school.setPhoneNumber(schoolObj.getString("phone_number"));
                                school.setWebsite(schoolObj.getString("website"));
                                school.setZip(schoolObj.getString("zip"));

                                // adding school to school array
                                schoolList.add(school);

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
                School schoolItem = schoolList.get(position);

                String name = schoolItem.getName();
                String dbn = schoolItem.getDbn();
                String zip = schoolItem.getZip();
                String city = schoolItem.getCity();
                String phoneNumber = schoolItem.getPhoneNumber();
                String location = schoolItem.getLocation();
                String email = schoolItem.getEmail();
                String website = schoolItem.getWebsite();

                Intent intent = new Intent(MainActivity.this, DetailViewActivity.class);
                intent.putExtra(DetailViewActivity.ARG_SCHOOL_NAME, name);
                intent.putExtra(DetailViewActivity.ARG_SCHOOL_DBN, dbn);
                intent.putExtra(DetailViewActivity.ARG_SCHOOL_ZIP, zip);
                intent.putExtra(DetailViewActivity.ARG_SCHOOL_CITY, city);
                intent.putExtra(DetailViewActivity.ARG_SCHOOL_EMAIL, email);
                intent.putExtra(DetailViewActivity.ARG_SCHOOL_LOCATION, location);
                intent.putExtra(DetailViewActivity.ARG_SCHOOL_PHONE_NUMBER, phoneNumber);
                intent.putExtra(DetailViewActivity.ARG_SCHOOL_WEBSITE, website);

                startActivity(intent);
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(schoolReq);
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
