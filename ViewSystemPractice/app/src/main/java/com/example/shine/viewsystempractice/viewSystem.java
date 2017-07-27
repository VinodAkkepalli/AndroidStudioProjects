package com.example.shine.viewsystempractice;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class viewSystem extends AppCompatActivity {

    @BindView(R.id.numericalET)
    TextView numericalET;
    @BindView(R.id.OK)
    Button OK;

    private static final String[] Months = new String[] {
            "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "Novermber", "December"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_system);
        ButterKnife.bind(this);

        createButton();
        createAutoCompleteTextView();
        //numericalEditText();





    }

    private void createButton() {
        final Button clickMeB = (Button) findViewById(R.id.Button1);

        clickMeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickMeB.setText(R.string.clickResponse);
            }
        });
    }

    private void createAutoCompleteTextView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.autocomplete_layout, Months);

        AutoCompleteTextView actv1 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        actv1.setThreshold(1);  //Start suggesting from first char entry
        actv1.setAdapter(adapter);
    }


    @OnClick(R.id.OK)
    public void numericalEditText(){
        DecimalFormat formatTo = new DecimalFormat("##.##");
        String formatted = formatTo.format(Double.parseDouble(numericalET.getText().toString()));
                Snackbar.make(OK, formatted + " is entered", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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

        return (id == R.id.action_settings) || super.onOptionsItemSelected(item);
    }
}
