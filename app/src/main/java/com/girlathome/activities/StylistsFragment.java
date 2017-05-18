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
import com.girlathome.adapters.StylistAdapter;
import com.girlathome.models.StylistModel;
import com.girlathome.utilities.AccountSharedPreferences;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by steve on 4/16/17.
 */
public class StylistsFragment extends Fragment {
    private static final String TAG = StylistsFragment.class.getSimpleName();
    Activity parentActivity;
    @BindView(R.id.stylists_recyclerview)
    RecyclerView stylistsRecyclerView;
    List<StylistModel> stylistModelList = new ArrayList<>();
    AccountSharedPreferences asp;
  /*  @BindView(R.id.progress)
    MKLoader mkLoader;*/

    public StylistsFragment() {
    }


    public static StylistsFragment newInstance() {
        StylistsFragment fragment = new StylistsFragment();
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
        View rootView = inflater.inflate(R.layout.stylists_fragment, container, false);
        ButterKnife.bind(this, rootView);
        asp = new AccountSharedPreferences(parentActivity);
        setViews();

        return rootView;
    }

    private void setViews() {
        //set dummy data ofcourse. Jesus
        /*for (int i = 0; i < 10; i++) {
            StylistModel stylistModel = new StylistModel();
            stylistModel.setName("Steve Kamau");
            stylistModelList.add(stylistModel);
        }*/
        StylistModel stylistModel = new StylistModel();
        stylistModelList.add(new StylistModel("Steve Kamau"));
        stylistModelList.add(new StylistModel("Jame Taylor"));
        stylistModelList.add(new StylistModel("Christine Bahati"));
        stylistModelList.add(new StylistModel("Stranger inHere"));
        stylistModelList.add(new StylistModel("The Hair Master"));
        stylistModelList.add(new StylistModel("I am the Hair Bender"));
        stylistModelList.add(new StylistModel("Enough Names"));
        stylistModelList.add(new StylistModel("Or Maybe Not"));
        stylistModelList.add(new StylistModel("Let's add one more"));

        setUpAdapter();

    }

    private void setUpAdapter() {

        RecyclerView.LayoutManager stylistLlm = new GridLayoutManager(parentActivity, 2);
        stylistsRecyclerView.setHasFixedSize(true);
        stylistsRecyclerView.setLayoutManager(stylistLlm);
        stylistsRecyclerView.setItemViewCacheSize(stylistModelList.size());
        StylistAdapter stylistsAdapter = new StylistAdapter(parentActivity, stylistModelList,"standalone");
        stylistsRecyclerView.setAdapter(stylistsAdapter);
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