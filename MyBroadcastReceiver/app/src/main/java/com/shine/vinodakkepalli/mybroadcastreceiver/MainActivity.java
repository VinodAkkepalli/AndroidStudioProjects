package com.shine.vinodakkepalli.mybroadcastreceiver;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();

        //register the receiver in the activity to receive broadcast
        //android.intent.action.HEADSET_PLUG action requires these steps below
        PhoneStateChangeReceiver phoneStateChangeReceiver = new PhoneStateChangeReceiver();
        IntentFilter intentFilter = new IntentFilter("android.intent.action.HEADSET_PLUG");
        registerReceiver(phoneStateChangeReceiver , intentFilter);
    }
}
