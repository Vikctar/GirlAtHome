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
 * Created by steve on 4/20/17.
 */
public class StyleFormDetailsFragment extends Fragment {
    private static final String TAG = StyleFormDetailsFragment.class.getSimpleName();
    Activity parentActivity;


    public StyleFormDetailsFragment() {
    }


    public static StyleFormDetailsFragment newInstance() {
        StyleFormDetailsFragment fragment = new StyleFormDetailsFragment();
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
        View rootView = inflater.inflate(R.layout.style_form_details_fragment, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.continue_btn)
    void continueClicked() {
        ((AddNew) parentActivity).createFragments(new TimeProductsFormFragment());
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