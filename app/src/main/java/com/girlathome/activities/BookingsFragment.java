package com.girlathome.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.girlathome.R;
import com.girlathome.adapters.BookingsAdapter;
import com.girlathome.databaseHandlers.BookingsDB;
import com.girlathome.models.BookingModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by steve on 4/16/17.
 */
public class BookingsFragment extends Fragment {
    private static final String TAG = BookingsFragment.class.getSimpleName();
    Activity parentActivity;
    @BindView(R.id.bookings_recyclerview)
    RecyclerView bookingsRecyclerView;
    List<BookingModel> bookingsModelList = new ArrayList<>();
    BookingsDB bDb;

    public BookingsFragment() {
    }


    public static BookingsFragment newInstance() {
        BookingsFragment fragment = new BookingsFragment();
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
        View rootView = inflater.inflate(R.layout.bookings_fragment, container, false);
        ButterKnife.bind(this, rootView);
        bDb = new BookingsDB(parentActivity);
        setViews();
        return rootView;
    }

    private void setViews() {

    }

    private void setUpAdapter() {
        RecyclerView.LayoutManager stylistLlm = new GridLayoutManager(parentActivity, 2);
        bookingsRecyclerView.setHasFixedSize(true);
        bookingsRecyclerView.setLayoutManager(stylistLlm);
        bookingsRecyclerView.setItemViewCacheSize(bookingsModelList.size());
        BookingsAdapter bookingsAdapter = new BookingsAdapter(parentActivity, bookingsModelList);
        bookingsRecyclerView.setAdapter(bookingsAdapter);
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
    public void onStart() {
        Log.d(TAG, "onStart: hit");
        super.onStart();
        bookingsModelList = bDb.getAllBookings();
        Log.d("bookings_size", "="+bookingsModelList.size());
        setUpAdapter();
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