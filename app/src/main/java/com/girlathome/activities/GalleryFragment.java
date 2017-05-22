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
import com.girlathome.adapters.StylesAdapter;
import com.girlathome.models.ServiceModel;
import com.girlathome.utilities.AccountSharedPreferences;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by steve on 4/18/17.
 */
public class GalleryFragment extends Fragment {
    private static final String TAG = GalleryFragment.class.getSimpleName();
    Activity parentActivity;
    @BindView(R.id.styles_recyclerview)
    RecyclerView stylesRecyclerView;
    List<ServiceModel> serviceModelList = new ArrayList<>();
    AccountSharedPreferences asp;


    public GalleryFragment() {
    }


    public static GalleryFragment newInstance() {
        GalleryFragment fragment = new GalleryFragment();
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
        View rootView = inflater.inflate(R.layout.gallery_fragment, container, false);
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
        for (int i = 0; i < 4; i++) {
            ServiceModel serviceModel = new ServiceModel();
            serviceModel.setName("Jamaican Braids");
            serviceModelList.add(serviceModel);
        }

        setUpAdapter();

    }

    private void setUpAdapter() {

        RecyclerView.LayoutManager stylistLlm = new GridLayoutManager(parentActivity, 2);
        stylesRecyclerView.setHasFixedSize(true);
        stylesRecyclerView.setLayoutManager(stylistLlm);
        stylesRecyclerView.setItemViewCacheSize(serviceModelList.size());
        StylesAdapter stylesAdapter = new StylesAdapter(parentActivity, serviceModelList, "gallery");
        stylesRecyclerView.setAdapter(stylesAdapter);
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