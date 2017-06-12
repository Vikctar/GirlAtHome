package com.girlathome.activities;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.girlathome.R;
import com.girlathome.adapters.StylesAdapter;
import com.girlathome.models.ServiceModel;
import com.girlathome.utilities.AccountSharedPreferences;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by steve on 6/5/17.
 */
public class StylesAvailableFragment extends Fragment {
    private static final String TAG = StylesAvailableFragment.class.getSimpleName();
    Activity parentActivity;
    @BindView(R.id.styles_recyclerview)
    RecyclerView stylesRecyclerView;
    @BindView(R.id.fragment_title)
    TextView titleTextView;
    List<ServiceModel> serviceModelList = new ArrayList<>();
    AccountSharedPreferences asp;


    public StylesAvailableFragment() {
    }


    public static StylesAvailableFragment newInstance() {
        StylesAvailableFragment fragment = new StylesAvailableFragment();
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
        View rootView = inflater.inflate(R.layout.styles_available_fragment, container, false);
        ButterKnife.bind(this, rootView);
        asp = new AccountSharedPreferences(parentActivity);
        setViews();
        return rootView;
    }

    private void setViews() {
        titleTextView.setText(getString(R.string.available_services));
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

    @OnClick(R.id.cancel)
    void close() {
        ((StylistDetails) parentActivity).createFragments(new EmptyFragment(), "empty");
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
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    ((StylistDetails) parentActivity).createFragments(new EmptyFragment(), "empty");
                    return true;
                }
                return false;
            }
        });
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