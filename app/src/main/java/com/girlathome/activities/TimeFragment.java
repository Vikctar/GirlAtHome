package com.girlathome.activities;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.girlathome.R;
import com.girlathome.adapters.AMTimeAdapter;
import com.girlathome.adapters.PMTimeAdapter;
import com.girlathome.utilities.InnerGridView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by steve on 5/11/17.
 */
public class TimeFragment extends Fragment implements PMTimeAdapter.ListAdapterListener, AMTimeAdapter.ListAdapterListener {
    private static final String TAG = TimeFragment.class.getSimpleName();
    Activity parentActivity;
    @BindView(R.id.am_grid_view)
    InnerGridView amInnerGridView;
    @BindView(R.id.pm_grid_view)
    InnerGridView pmInnerGridView;
    String[] amHours;
    String[] pmHours;
    String daytime_variant;
    String hour_variant, dateSelected;


    public TimeFragment() {
    }


    public static TimeFragment newInstance() {
        TimeFragment fragment = new TimeFragment();
        return fragment;
    }

    // Method for converting DP value to pixels
    public static int getPixelsFromDPs(Activity activity, int dps) {
        Resources r = activity.getResources();
        int px = (int) (TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dps, r.getDisplayMetrics()));
        return px;
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
        View rootView = inflater.inflate(R.layout.time_fragment_layout, container, false);
        ButterKnife.bind(this, rootView);
        setViews();
        return rootView;
    }

    private void setViews() {
//        title
        ((BookingActivity) parentActivity).setUpTitle(getString(R.string.pick_a_time));
        dateSelected = ((BookingActivity) parentActivity).getDateSelected();
        daytime_variant = getDayFormatted(getDateTime()).substring(getDayFormatted(getDateTime()).length() - 2);
        hour_variant = getDayFormatted(getDateTime()).substring(getDayFormatted(getDateTime()).length() - 2);
        hour_variant = getDayFormatted(getDateTime()).length() < 2 ? getDayFormatted(getDateTime())
                : getDayFormatted(getDateTime()).substring(0, 2);

        Log.d("time_day", "" + hour_variant + "-" + getDayFormatted(getDateTime()));
        setAmAdapter();
        setPMAdapter();

    }

    private void setAmAdapter() {
        amHours = new String[]{"12", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
        List<String> amList = Arrays.asList(amHours);
        AMTimeAdapter timeAdapter = new AMTimeAdapter(parentActivity, amList, daytime_variant, hour_variant, dateSelected, this);
        amInnerGridView.setAdapter(timeAdapter);
    }

    private void setPMAdapter() {
        pmHours = new String[]{"12", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
        List<String> pmList = Arrays.asList(pmHours);
        PMTimeAdapter pmTimeAdapter = new PMTimeAdapter(parentActivity, pmList, daytime_variant, hour_variant, dateSelected, this);
        pmInnerGridView.setAdapter(pmTimeAdapter);

    }

    String getDateTime() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(now);
    }

    private String getDayFormatted(String dateStr) {
        DateFormat readFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat writeFormat = new SimpleDateFormat("hh a");
        Date date = null;
        try {
            date = readFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = writeFormat.format(date);
        return formattedDate;
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

    @Override
    public void onPMClick(int position, String hour_variant, String daytime_variant) {
        if (hour_variant.length() == 1) {
            hour_variant = "0" + hour_variant;
        }
        Log.d("time_to_get", hour_variant);
        onTimeSelected(hour_variant + ":00 " + daytime_variant);
    }

    @Override
    public void onAMClick(int position, String hour_variant, String daytime_variant) {
        if (hour_variant.length() == 1) {
            hour_variant = "0" + hour_variant;
        }
        Log.d("time_to_get", hour_variant);
        onTimeSelected(hour_variant + ":00 " + daytime_variant);
    }

    private void onTimeSelected(String selectedTime) {
        ((BookingActivity) parentActivity).setTime(selectedTime);
        ((BookingActivity) parentActivity).createFragments(new PaymentFragment());

    }

}