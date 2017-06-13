package com.girlathome;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.girlathome.utilities.AccountSharedPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by steve on 6/12/17.
 */
public class NotificationsSettingsFragment extends Fragment {
    private static final String TAG = NotificationsSettingsFragment.class.getSimpleName();
    Activity parentActivity;
    AccountSharedPreferences asp;
    @BindView(R.id.tv_early_notifications_subtext)
    TextView tvEarlyNotificationsSubtext;


    public NotificationsSettingsFragment() {
    }


    public static NotificationsSettingsFragment newInstance() {
        NotificationsSettingsFragment fragment = new NotificationsSettingsFragment();
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
        View rootView = inflater.inflate(R.layout.notifications_settings_fragment, container, false);
        ButterKnife.bind(this, rootView);
        asp = new AccountSharedPreferences(parentActivity);
        setViews();
        return rootView;
    }

    private void setViews() {
        tvEarlyNotificationsSubtext.setText(asp.getTimeForEarlyNotification());
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: hit");
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick(R.id.early_notifications)
    void timeDurationBeforeNotification() {
        final CharSequence[] items = {
                "15 mins before", "30 mins before", "1 hour before"
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(parentActivity);
        builder.setTitle("Notify me");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                //mDoneButton.setText(items[item]);
                tvEarlyNotificationsSubtext.setText(items[item]);
                asp.setTimeForEarlyNotification(String.valueOf(items[item]));
//                asp.set
                int minutes = 15;
                if (item == 0) {
                    minutes = 15;
                } else if (item == 1) {
                    minutes = 30;
                } else {
                    minutes = 60;
                }
                long millis = minutes * 60 * 1000;
                asp.setTimeMillsForEarlyNotification(millis);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
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