package com.practice.shine.customlistview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * News detail view
 */
public class DetailViewActivity extends Activity {

    public static final String ARG_SCHOOL_NAME = "name";
    public static final String ARG_SCHOOL_DBN = "dbn";
    public static final String ARG_SCHOOL_ZIP = "zip";
    public static final String ARG_SCHOOL_CITY = "city";
    public static final String ARG_SCHOOL_PHONE_NUMBER = "phoneNumber";
    public static final String ARG_SCHOOL_LOCATION = "location";
    public static final String ARG_SCHOOL_EMAIL = "email";
    public static final String ARG_SCHOOL_WEBSITE = "website";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();

        String name = "";
        String dbn = "";
        String zip = "";
        String city = "";
        String phoneNumber = "";
        String location = "";
        String email = "";
        String website = "";
        if (extras != null) {
            name = extras.getString(ARG_SCHOOL_NAME);
            dbn = extras.getString(ARG_SCHOOL_DBN);
            zip = extras.getString(ARG_SCHOOL_ZIP);
            city = extras.getString(ARG_SCHOOL_CITY);
            phoneNumber = extras.getString(ARG_SCHOOL_PHONE_NUMBER);
            location = extras.getString(ARG_SCHOOL_LOCATION);
            email = extras.getString(ARG_SCHOOL_EMAIL);
            website = extras.getString(ARG_SCHOOL_WEBSITE);
        }

        getActionBar().setTitle(name);
        TextView nameView = (TextView) findViewById(R.id.name);
        TextView dbnView = (TextView) findViewById(R.id.dbn);
        TextView zipView = (TextView) findViewById(R.id.zip);
        TextView cityView = (TextView) findViewById(R.id.city);
        TextView phoneNumberView = (TextView) findViewById(R.id.phone_number);
        TextView locationView = (TextView) findViewById(R.id.location);
        TextView emailView = (TextView) findViewById(R.id.email);
        TextView websiteView = (TextView) findViewById(R.id.website);

        nameView.setText("Name: " + name);
        dbnView.setText("DBN: " + dbn);
        zipView.setText("Zip: " + zip);
        cityView.setText("City: " + city);
        phoneNumberView.setText("Phone#: " + phoneNumber);
        locationView.setText("Location: " + location);
        emailView.setText("Email: " + email);
        websiteView.setText("Website: " + website);
    }
}
