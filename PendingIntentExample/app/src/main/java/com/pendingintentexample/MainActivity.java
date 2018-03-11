package com.pendingintentexample;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    //create an intent for the activity that will be launched by alarm service
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, MainActivity.class);

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    }


    public void registerIntent(View view) {

        //wrap the intent into a pending intent
        PendingIntent pendingIntent = getActivity(this,
                1001, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //tell alarm manager to run the activity every minute, RTC_wake up is for phone in sleep mode.
        alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(),60000, pendingIntent);

    }

    public void unRegisterIntent(View view) {

        //Construct the Pending Intent with exact parameter to to re-retrieve it
        //same operation, same Intent action, data, categories, and components, and same flags
        PendingIntent pendingIntent = getActivity(this,
                1001, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        //and then cancel it
        pendingIntent.cancel();
    }
}
