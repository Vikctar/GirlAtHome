package com.girlathome.utilities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by steve on 6/6/17.
 */

public class AlarmReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub


        // here you can start an activity or service depending on your need
        // for ex you can start an activity to vibrate phone or to ring the phone

        // Show the toast  like in above screen shot
        Toast.makeText(context, "Alarm Triggered", Toast.LENGTH_LONG).show();
    }

}