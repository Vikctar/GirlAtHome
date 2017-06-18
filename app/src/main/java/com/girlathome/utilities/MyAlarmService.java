package com.girlathome.utilities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.girlathome.R;
import com.girlathome.activities.BookingActivity;

/**
 * Created by steve on 6/9/17.
 */

public class MyAlarmService extends Service {


    @Override

    public void onCreate() {

// TODO Auto-generated method stub

        Toast.makeText(this, "MyAlarmService.onCreate()", Toast.LENGTH_LONG).show();

        Notification myNotication;
        NotificationManager manager;
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, BookingActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, 0);
        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        builder.setAutoCancel(true);
        builder.setTicker("weHappening event alert");
        builder.setContentTitle("weHappening");
        builder.setContentText("Your event will be up in an hour!!");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setOngoing(true);
        builder.setSubText("Let's get happening!");
        builder.setNumber(100);
        builder.build();
        myNotication = builder.getNotification();
//        myNotication.defaults |= Notification.DEFAULT_VIBRATE;
        myNotication.defaults |= Notification.DEFAULT_SOUND;
        manager.notify(11, myNotication);

    }


    @Override

    public IBinder onBind(Intent intent) {

// TODO Auto-generated method stub

        Toast.makeText(this, "MyAlarmService.onBind()", Toast.LENGTH_LONG).show();

        return null;

    }


    @Override

    public void onDestroy() {

// TODO Auto-generated method stub

        super.onDestroy();

        Toast.makeText(this, "MyAlarmService.onDestroy()", Toast.LENGTH_LONG).show();


    }


    @Override

    public void onStart(Intent intent, int startId) {

// TODO Auto-generated method stub

        super.onStart(intent, startId);

        Toast.makeText(this, "MyAlarmService.onStart()", Toast.LENGTH_LONG).show();

    }


    @Override

    public boolean onUnbind(Intent intent) {

// TODO Auto-generated method stub

        Toast.makeText(this, "MyAlarmService.onUnbind()", Toast.LENGTH_LONG).show();

        return super.onUnbind(intent);

    }


}
