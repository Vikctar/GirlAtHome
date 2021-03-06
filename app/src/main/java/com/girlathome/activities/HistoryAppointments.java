package com.girlathome.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.girlathome.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by steve on 6/2/17.
 */
public class HistoryAppointments extends Fragment {
    private static final String TAG = HistoryAppointments.class.getSimpleName();
    Activity parentActivity;
    @BindView(R.id.empty_layout)
    RelativeLayout emptyLayout;
    @BindView(R.id.icon)
    ImageView emptyImageView;
    @BindView(R.id.title)
    TextView emptyTitleTextView;
    @BindView(R.id.sub_title)
    TextView emptySubTitleTextView;


    public HistoryAppointments() {
    }


    public static HistoryAppointments newInstance() {
        HistoryAppointments fragment = new HistoryAppointments();
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
        View rootView = inflater.inflate(R.layout.history_appointments_layout, container, false);
        ButterKnife.bind(this, rootView);
        setViews();
        return rootView;
    }

    private void setViews() {
        emptyLayout.setVisibility(View.VISIBLE);
        emptyImageView.setImageResource(R.drawable.ic_empty_calendar);
        emptyTitleTextView.setText(getString(R.string.empty_history_title));
        emptySubTitleTextView.setText(getString(R.string.empty_history_sub_title));
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