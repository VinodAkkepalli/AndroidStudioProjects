package com.example.shine.viewsystempractice;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;


public class viewSystem extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_system);

        final Button clickMeB = (Button) findViewById(R.id.Button1);

        clickMeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickMeB.setText(R.string.clickResponse);
            }
        });


        /*AutoCompleteTextView actv1 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        actv1.set
        */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view_system, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
