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
import com.girlathome.adapters.FavouriteCategories;
import com.girlathome.databaseHandlers.FavouritesDB;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by steve on 4/16/17.
 */
public class FavouritesFragment extends Fragment {
    private static final String TAG = FavouritesFragment.class.getSimpleName();
    Activity parentActivity;
    @BindView(R.id.type_of_favourite_recyclerview)
    RecyclerView typeOfFavouriteRecyclerView;
    List<String> typeOfFavouriteModelList = new ArrayList<>();
    FavouritesDB favouritesDB;

    public FavouritesFragment() {
    }


    public static FavouritesFragment newInstance() {
        FavouritesFragment fragment = new FavouritesFragment();
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
        View rootView = inflater.inflate(R.layout.favourites_fragment, container, false);
        ButterKnife.bind(this, rootView);
        favouritesDB = new FavouritesDB(parentActivity);
        setViews();
        return rootView;
    }

    private void setViews() {
//        for (int i = 0; i < 4; i++) {
//            typeOfFavouriteModelList.add("");
//        }
        if (favouritesDB.getStylesCount() > 0) {
            typeOfFavouriteModelList.add(favouritesDB.getStylesCount() + " styles");
        }
        if (favouritesDB.getStylistsCount() > 0) {
            typeOfFavouriteModelList.add("" + favouritesDB.getStylistsCount() + " stylists");
        }
        setUpAdapter();
    }

    private void setUpAdapter() {
        RecyclerView.LayoutManager stylistLlm = new GridLayoutManager(parentActivity, 2);
        typeOfFavouriteRecyclerView.setHasFixedSize(true);
        typeOfFavouriteRecyclerView.setLayoutManager(stylistLlm);
        typeOfFavouriteRecyclerView.setItemViewCacheSize(typeOfFavouriteModelList.size());
        FavouriteCategories favouriteCategories = new FavouriteCategories(parentActivity, typeOfFavouriteModelList);
        typeOfFavouriteRecyclerView.setAdapter(favouriteCategories);
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