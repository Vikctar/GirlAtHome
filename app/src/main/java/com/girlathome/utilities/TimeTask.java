package com.girlathome.utilities;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by steve on 6/12/17.
 */

public class TimeTask {
    public String getCurrentTimeStamp24HRS() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        return sdfDate.format(now);
    }

    public String dateTimeDifference(String dateTime) {

        String dateStart = getCurrentTimeStamp24HRS();
        String dateStop = dateTime;
        String dateTimeDiff = "0";

        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            Log.d("Date-", diffDays + " days, ");
            Log.d("Date-", diffHours + " hours, ");
            Log.d("Date-", diffMinutes + " minutes, ");
            Log.d("Date-", diffSeconds + " seconds.");
            if (diffDays > 1) {
                Log.d("Date-", diffDays + " days, ");
                dateTimeDiff = diffDays + " days";
            } else if (diffDays == 1) {
                dateTimeDiff = diffDays + " day";
            } else if (diffHours > 1) {
                Log.d("Date-", diffHours + " hours, ");
                dateTimeDiff = diffHours + " hours";
            } else if (diffHours == 1) {
                Log.d("Date-", diffHours + " hours, ");
                dateTimeDiff = diffHours + " hour";
            } else if (diffMinutes >= 1) {
                Log.d("Date-", diffSeconds + " seconds.");
                dateTimeDiff = diffMinutes + " min";
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Date-", " error, ");
        }
//            }
//
//        }, 0, 1000);
        return dateTimeDiff;
//        return "";
    }

    public String formatIntoDayOfWeek(String strCurrentDate) throws ParseException {
       /* String strCurrentDate = "Wed, 18 Apr 2012 07:55:29 +0000";
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss Z");
        Date newDate = format.parse(strCurrentDate);

        format = new SimpleDateFormat("MMM dd,yyyy hh:mm a");
        String date = format.format(newDate);*/

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH);
        Date newDate = format.parse(strCurrentDate);

        format = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm a", Locale.ENGLISH);
        return format.format(newDate);
    }

    public String formatInto24HRS(String strCurrentDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH);
        Date newDate = format.parse(strCurrentDate);

        format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);

        return format.format(newDate);
    }
}
