package com.girlathome.activities;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.girlathome.R;
import com.girlathome.databaseHandlers.BookingsDB;
import com.girlathome.utilities.AccountSharedPreferences;
import com.girlathome.utilities.AlarmReciever;
import com.girlathome.utilities.TimeTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by steve on 5/13/17.
 */
public class ConfirmBookingFragment extends Fragment {
    private static final String TAG = ConfirmBookingFragment.class.getSimpleName();
    Activity parentActivity;
    EditText edMessage;
    @BindView(R.id.note_preview)
    TextView tvNotePreview;
    @BindView(R.id.note_layout)
    CardView noteCardView;
    @BindView(R.id.payment_mode)
    TextView tvPaymentMode;
    @BindView(R.id.time_date_selected)
    TextView tvTimeDateSelected;
    String message, paymentMode, timeSelected, dateSelected, styleName;
    BookingsDB bDb;
    TimeTask timeTask;
    AccountSharedPreferences asp;
    int minutes;

    public ConfirmBookingFragment() {
    }


    public static ConfirmBookingFragment newInstance() {
        ConfirmBookingFragment fragment = new ConfirmBookingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: hit");
        super.onCreate(savedInstanceState);
    }

    /**
     * Change the null parameter in {@code inflater.inflate()}
     * to a layout resource {@code R.layout.example}
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: hit");
        View rootView = inflater.inflate(R.layout.confirm_booking_layout, container, false);
        ButterKnife.bind(this, rootView);
//        title
        ((BookingActivity) parentActivity).setUpTitle(getString(R.string.confirm_booking));
        bDb = new BookingsDB(parentActivity);
        asp = new AccountSharedPreferences(parentActivity);
        timeTask = new TimeTask();
        setViews();
        return rootView;
    }

    private void setViews() {
        noteCardView.setVisibility(View.GONE);
        paymentMode = ((BookingActivity) parentActivity).getPaymentMode();
        timeSelected = ((BookingActivity) parentActivity).getTime();
        dateSelected = ((BookingActivity) parentActivity).getDateSelected();

        if (paymentMode.equalsIgnoreCase("cash")) {
            tvPaymentMode.setText(getString(R.string.cash_payment_subtext) + " " + paymentMode);
        } else {
            tvPaymentMode.setText(getString(R.string.mpesa_payment_subtext) + " " + paymentMode);
        }
        tvTimeDateSelected.setText(timeSelected + " " + dateSelected);
        try {
            tvTimeDateSelected.setText(timeTask.formatIntoDayOfWeek(dateSelected + " " + timeSelected));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        styleName = ((BookingActivity) parentActivity).getStyleName();

        //get minutes to show notification before
        minutes = (int) (asp.getTimeMillsForEarlyNotification() / 60) / 1000;

    }


    @OnClick(R.id.done)
    void addAppointment() {

        try {
            bDb.addAppointment(0, "", "", styleName, "", "", dateSelected, timeSelected,
                    timeTask.formatInto24HRS(dateSelected + " " + timeSelected), "", "", "", message);
            setAlarmTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ((BookingActivity) parentActivity).createFragments(new BookingAccepted());
//        Toast.makeText(parentActivity, "Successfully booked an appointment!", Toast.LENGTH_LONG).show();

        /*Intent intent = new Intent(getActivity(), Login.class);
        intent.putExtra("loginOut", message);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);*/
    }

    public void setAlarmTime() throws ParseException {
        // time at which alarm will be scheduled here alarm is scheduled at 1 day from current time,
        // we fetch time in milliseconds
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = (Date) formatter.parse(timeTask.formatInto24HRS(dateSelected + " " + timeSelected));
        long time = date.getTime() - asp.getTimeMillsForEarlyNotification();
        // create an Intent and set the class which will execute when Alarm triggers, here we have
        // given AlarmReciever in the Intent, the onRecieve() method of this class will execute when
        // alarm triggers
        Intent intentAlarm = new Intent(parentActivity, AlarmReciever.class);
        intentAlarm.putExtra("minutes_before_notification", minutes);
        // create the object
        AlarmManager alarmManager = (AlarmManager) parentActivity.getSystemService(Context.ALARM_SERVICE);
        final int broadcast_id = (int) System.currentTimeMillis();
        //set the alarm for particular time
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(parentActivity, broadcast_id, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
        Toast.makeText(parentActivity, "You will be notified", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.add_note_layout)
    void addNote() {
        showAddNoteDialog();
    }

    @OnClick(R.id.cancel)
    void removeNote() {
        noteCardView.setVisibility(View.GONE);
        message = "";
    }

    private void showAddNoteDialog() {
        // custom dialog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_note_dialog, null);
        dialogBuilder.setView(dialogView);

        edMessage = (EditText) dialogView.findViewById(R.id.ed_message);
        edMessage.setText(message);

        //dialogBuilder.setTitle("Add note");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                message = edMessage.getText().toString();
                //do something with edt.getText().toString();
                if (!TextUtils.isEmpty(message)) {
                    noteCardView.setVisibility(View.VISIBLE);
                    tvNotePreview.setText(message);
                }

            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: hit");
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onResume() {
        Log.d(TAG, "onResume: hit");
        super.onResume();
    }


    @Override
    public void onPause() {
        Log.d(TAG, "onPause: hit");
        super.onPause();
    }


    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView: hit");
        super.onDestroyView();
    }


    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: hit");
        super.onDestroy();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        parentActivity = activity;
    }
} 