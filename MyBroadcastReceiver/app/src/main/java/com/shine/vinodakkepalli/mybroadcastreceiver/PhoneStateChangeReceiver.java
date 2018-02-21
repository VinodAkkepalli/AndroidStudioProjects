package com.shine.vinodakkepalli.mybroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class PhoneStateChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, intent.getAction() + " Broadcast received", Toast.LENGTH_LONG).show();
        Log.d(context.getClass().toString(), intent.getAction() + " Broadcast received");

    }
}
