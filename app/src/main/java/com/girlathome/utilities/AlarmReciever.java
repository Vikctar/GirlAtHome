package com.girlathome.utilities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.girlathome.R;
import com.girlathome.activities.MainActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by steve on 6/6/17.
 */

public class AlarmReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        // here you can start an activity or service depending on your need
        // for ex you can start an activity to vibrate phone or to ring the phone
        TimeTask t = new TimeTask();
        Log.d("Triggered", t.getCurrentTimeStamp24HRS());
        int minutes_value = intent.getIntExtra("minutes_before_notification", 10);
        showNotification(context, minutes_value);
    }

    private void showNotification(Context context, int minutes_value) {
        Notification myNotication;
        NotificationManager manager;
        manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(context, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, 0);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setAutoCancel(true);
        builder.setTicker("Dazzle: Your appointment is up!");
        builder.setContentTitle("Dazzle");
        builder.setContentText("One of your appointments is almost due in " + minutes_value + " minutes.");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setOngoing(true);
        builder.build();
        myNotication = builder.getNotification();
//        myNotication.defaults |= Notification.DEFAULT_VIBRATE;
        myNotication.defaults |= Notification.DEFAULT_SOUND;
        manager.notify(11, myNotication);
    }

}