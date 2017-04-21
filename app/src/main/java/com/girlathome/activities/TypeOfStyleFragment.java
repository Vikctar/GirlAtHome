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
public class TypeOfStyleFragment extends Fragment {
    private static final String TAG = TypeOfStyleFragment.class.getSimpleName();
    Activity parentActivity;

    public TypeOfStyleFragment() {
    }


    public static TypeOfStyleFragment newInstance() {
        TypeOfStyleFragment fragment = new TypeOfStyleFragment();
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
        View rootView = inflater.inflate(R.layout.type_of_style_fragment, container, false);
        ButterKnife.bind(this, rootView);
        setViews();
        return rootView;
    }

    private void setViews() {
        ((AddNew)parentActivity).setUpTitle(getString(R.string.style_category));
    }

    @OnClick(R.id.hair_layout)
    void hairClicked() {
        ((AddNew) parentActivity).createFragments(new StyleFormDetailsFragment());
    }

    @OnClick(R.id.manicure_layout)
    void manicureClicked() {
        ((AddNew) parentActivity).createFragments(new StyleFormDetailsFragment());
    }

    @OnClick(R.id.pedicure_layout)
    void pedicureClicked() {
        ((AddNew) parentActivity).createFragments(new StyleFormDetailsFragment());
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