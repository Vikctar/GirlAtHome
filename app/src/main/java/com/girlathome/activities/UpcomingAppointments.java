package com.girlathome.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.girlathome.R;
import com.girlathome.adapters.UpcomingAppointmentsAdapter;
import com.girlathome.databaseHandlers.BookingsDB;
import com.girlathome.models.BookingModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by steve on 6/2/17.
 */
public class UpcomingAppointments extends Fragment {
    private static final String TAG = UpcomingAppointments.class.getSimpleName();
    Activity parentActivity;
    @BindView(R.id.bookings_recyclerview)
    RecyclerView bookingsRecyclerView;
    @BindView(R.id.empty_layout)
    RelativeLayout emptyLayout;
    @BindView(R.id.icon)
    ImageView emptyImageView;
    @BindView(R.id.title)
    TextView emptyTitleTextView;
    @BindView(R.id.sub_title)
    TextView emptySubTitleTextView;
    List<BookingModel> bookingsModelList = new ArrayList<>();
    BookingsDB bDb;

    public UpcomingAppointments() {
    }


    public static UpcomingAppointments newInstance() {
        UpcomingAppointments fragment = new UpcomingAppointments();
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
        View rootView = inflater.inflate(R.layout.upcoming_appointments_layout, container, false);
        ButterKnife.bind(this, rootView);
        bDb = new BookingsDB(parentActivity);
        setViews();
        return rootView;
    }

    private void setViews() {

    }

    private void setUpAdapter() {
        LinearLayoutManager stylistLlm = new LinearLayoutManager(parentActivity);
        bookingsRecyclerView.setHasFixedSize(true);
        bookingsRecyclerView.setLayoutManager(stylistLlm);
        bookingsRecyclerView.setItemViewCacheSize(bookingsModelList.size());
        UpcomingAppointmentsAdapter upcomingAppointmentsAdapter = new UpcomingAppointmentsAdapter(parentActivity, bookingsModelList);
        bookingsRecyclerView.setAdapter(upcomingAppointmentsAdapter);
    }

    @OnClick(R.id.fab)
    void deleteAppointments() {
        bDb.deleteAllAppointments();
        setList();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: hit");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart: hit");
        super.onStart();
        setList();
    }

    private void setList() {
        bookingsModelList = bDb.getAllBookings();
        Log.d("bookings_size", "=" + bookingsModelList.size());
        if (bookingsModelList.size() == 0) {
            emptyLayout.setVisibility(View.VISIBLE);
            emptyImageView.setImageResource(R.drawable.ic_empty_calendar);
            emptyTitleTextView.setText(getString(R.string.empty_upcoming_appointments_title));
            emptySubTitleTextView.setText(getString(R.string.empty_upcoming_appointments_sub_title));
        } else {
            emptyLayout.setVisibility(View.INVISIBLE);
        }
        setUpAdapter();
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