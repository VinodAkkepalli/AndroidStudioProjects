package com.exercise.postslist;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailViewActivity extends Activity {

    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;

    public static final String ARG_ID = "id";
    public static final String ARG_USERID = "userId";
    public static final String ARG_TITLE = "title";
    public static final String ARG_BODY = "body";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();

        String id = "";
        String userId = "";
        String title = "";
        String body = "";
        if (extras != null) {
            id = extras.getString(ARG_ID);
            userId = extras.getString(ARG_USERID);
            title = extras.getString(ARG_TITLE);
            body = extras.getString(ARG_BODY);
        }

        if(getActionBar() != null){
            getActionBar().setTitle(id);
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        TextView idView = (TextView) findViewById(R.id.id);
        TextView useridView = (TextView) findViewById(R.id.userid);
        TextView titleView = (TextView) findViewById(R.id.tile);
        TextView bodyView = (TextView) findViewById(R.id.body);

        pDialog = new ProgressDialog(this);
        //showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        idView.setText("Id: " + id);
        useridView.setText("UserId: " + userId);
        titleView.setText("Title: " + title);
        bodyView.setText("Body: " + body);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
