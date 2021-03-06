package com.example.vinodakkepalli.sunshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.action_map){
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            String location_code = sharedPref.getString(getString(R.string.LOCATION_KEY), getString(R.string.default_location));

            Uri geolocation = Uri.parse("geo:0,0?").buildUpon().appendQueryParameter("q", location_code).build();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(geolocation);

            if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }


}
