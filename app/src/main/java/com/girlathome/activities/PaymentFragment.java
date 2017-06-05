package com.girlathome.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.girlathome.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by steve on 5/23/17.
 */
public class PaymentFragment extends Fragment {
    private static final String TAG = PaymentFragment.class.getSimpleName();
    Activity parentActivity;


    public PaymentFragment() {
    }


    public static PaymentFragment newInstance() {
        PaymentFragment fragment = new PaymentFragment();
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
        View rootView = inflater.inflate(R.layout.payment_fragment_layout, container, false);
        ButterKnife.bind(this, rootView);
        //        title
        ((BookingActivity) parentActivity).setUpTitle(getString(R.string.payment));
        return rootView;
    }

    @OnClick(R.id.mpesa_layout)
    void mpesaClicked() {
        onPaymentSelected("M-Pesa");
    }

    @OnClick(R.id.cash_layout)
    void cashClicked() {
        onPaymentSelected("Cash");

    }

    private void onPaymentSelected(String payment_mode) {
        ((BookingActivity) parentActivity).setPaymentMode(payment_mode);
        ((BookingActivity) parentActivity).createFragments(new ConfirmBookingFragment());
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