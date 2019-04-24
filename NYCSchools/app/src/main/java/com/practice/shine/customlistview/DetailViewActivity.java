package com.practice.shine.customlistview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.practice.shine.customlistview.model.SchoolSATData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class DetailViewActivity extends Activity {

    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    private final HashMap<String, SchoolSATData> schoolSATDataMap = new HashMap<>();
    private ProgressDialog pDialog;


    public static final String ARG_SCHOOL_NAME = "name";
    public static final String ARG_SCHOOL_DBN = "dbn";
    public static final String ARG_SCHOOL_ZIP = "zip";
    public static final String ARG_SCHOOL_CITY = "city";
    public static final String ARG_SCHOOL_PHONE_NUMBER = "phoneNumber";
    public static final String ARG_SCHOOL_LOCATION = "location";
    public static final String ARG_SCHOOL_EMAIL = "email";
    public static final String ARG_SCHOOL_WEBSITE = "website";

    String dbn = "";
    TextView numSatTestTakersView;
    TextView mathScoreView;
    TextView readingScoreView;
    TextView writingScoreView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();

        String name = "";
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

        if(getActionBar() != null){
            getActionBar().setTitle(name);
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        TextView nameView = (TextView) findViewById(R.id.name);
        TextView dbnView = (TextView) findViewById(R.id.dbn);
        TextView zipView = (TextView) findViewById(R.id.zip);
        TextView cityView = (TextView) findViewById(R.id.city);
        TextView phoneNumberView = (TextView) findViewById(R.id.phone_number);
        TextView locationView = (TextView) findViewById(R.id.location);
        TextView emailView = (TextView) findViewById(R.id.email);
        TextView websiteView = (TextView) findViewById(R.id.website);
        numSatTestTakersView = (TextView) findViewById(R.id.num_sat_test_takers);
        mathScoreView = (TextView) findViewById(R.id.math_score);
        readingScoreView = (TextView) findViewById(R.id.reading_score);
        writingScoreView = (TextView) findViewById(R.id.writing_score);

        pDialog = new ProgressDialog(this);
        //showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        FetchSatDataTask fetchSatDataTask = new FetchSatDataTask();
        fetchSatDataTask.execute();

        nameView.setText("Name: " + name);
        dbnView.setText("DBN: " + dbn);
        zipView.setText("Zip: " + zip);
        cityView.setText("City: " + city);
        phoneNumberView.setText("Phone#: " + phoneNumber);
        locationView.setText("Location: " + location);
        emailView.setText("Email: " + email);
        websiteView.setText("Website: " + website);
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


    public class FetchSatDataTask extends AsyncTask<String, Integer, HashMap<String, SchoolSATData>> {

        @Override
        protected HashMap<String, SchoolSATData> doInBackground(String... strings) {
            String forecastJsonStr = null;
            URL url = null;
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            try {
                url = new URL("https://data.cityofnewyork.us/resource/f9bf-2cp4.json");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                forecastJsonStr = buffer.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("AsyncTask", "Error closing stream", e);
                    }
                }
            }

            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(forecastJsonStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < jsonArray.length(); i++) {

                try {
                    JSONObject schoolSatObj = jsonArray.getJSONObject(i);
                    SchoolSATData schoolSATData = new SchoolSATData();
                    schoolSATData.setNumSatTestTakers(schoolSatObj.getString("num_of_sat_test_takers"));
                    schoolSATData.setReadingScore(schoolSatObj.getString("sat_critical_reading_avg_score"));
                    schoolSATData.setWritingScore(schoolSatObj.getString("sat_writing_avg_score"));
                    schoolSATData.setMathScore(schoolSatObj.getString("sat_math_avg_score"));
                    schoolSATData.setName(schoolSatObj.getString("school_name"));

                    schoolSATDataMap.put(schoolSatObj.getString("dbn"), schoolSATData);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return schoolSATDataMap;
        }

        @Override
        protected void onPostExecute(HashMap<String, SchoolSATData> stringSchoolSATDataHashMap) {
            super.onPostExecute(stringSchoolSATDataHashMap);
            SchoolSATData schoolSATData = schoolSATDataMap.get(dbn);
            if(schoolSATData != null){
                readingScoreView.setText("SAT Critical Reading Avg Score: " + schoolSATData.getReadingScore());
                writingScoreView.setText("SAT Writing Avg Score: " + schoolSATData.getWritingScore());
                mathScoreView.setText("SAT Math Avg Score: " + schoolSATData.getMathScore());
                numSatTestTakersView.setText("Num of SAT Test Takers: " + schoolSATData.getNumSatTestTakers());
            }
            hidePDialog();

        }
    }
}
