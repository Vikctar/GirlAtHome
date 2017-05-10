package com.girlathome.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.girlathome.R;
import com.girlathome.adapters.StylistAdapter;
import com.girlathome.models.StylistModel;
import com.girlathome.utilities.AccountSharedPreferences;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tuyenmonkey.mkloader.MKLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by steve on 4/16/17.
 */
public class HomeFragment extends Fragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    Activity parentActivity;
    AccountSharedPreferences asp;
    @BindView(R.id.progress)
    MKLoader mkLoader;
    @BindView(R.id.stylists_recyclerview)
    RecyclerView stylistsRecyclerView;
    @BindView(R.id.body_content)
    ScrollView bodyContent;
    Boolean hasList = false;
    List<StylistModel> stylistModelList = new ArrayList<>();


    public HomeFragment() {
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, rootView);
        asp = new AccountSharedPreferences(parentActivity);
        homeDataRequest("all_stylists");
        return rootView;
    }

    private void homeDataRequest(String data_variant) {
        final int DEFAULT_TIMEOUT = 20 * 10000;
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(DEFAULT_TIMEOUT);
        RequestParams params = new RequestParams();
        client.get(getResources().getString(R.string.base_url) + data_variant, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                Log.d("backgrnd_req", "started");
                if (hasList) {
                    mkLoader.setVisibility(View.INVISIBLE);
                } else {
                    mkLoader.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                String s = new String(responseBody);

                mkLoader.setVisibility(View.INVISIBLE);
                Log.d("stylists_", s);
                System.out.print(s);
                asp.setHomeData(s);
                parseHomeDataRequest(s);
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
//                displayRetry();
                Log.d("backgrnd_req", statusCode + "");
                mkLoader.setVisibility(View.INVISIBLE);

            }
        });
    }

    private void parseHomeDataRequest(String s) {
        stylistModelList.clear();
        try {
            JSONObject dataObject = new JSONObject(s);
            JSONArray productsArray = dataObject.getJSONArray("data");
            for (int i = 0; i < productsArray.length(); i++) {
                JSONObject stylistsObject = productsArray.getJSONObject(i);
                StylistModel stylistModel = new StylistModel();
                stylistModel.setStylist_id(stylistsObject.getString("stylist_id"));
                stylistModel.setName(stylistsObject.getString("name"));
                stylistModel.setEmail(stylistsObject.getString("email"));
                stylistModel.setPhone_number(stylistsObject.getString("phone_number"));
              /*  stylistModel.setStylist_id();
                stylistModel.setStylist_id();
                stylistModel.setStylist_id();*/

                stylistModelList.add(stylistModel);
            }
            Log.d("stylists_1", stylistModelList.size() + "");
            setUpAdapter();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("locatiion_req", e.toString());
        }
    }

    private void setUpAdapter() {

        Log.d("stylists_2", stylistModelList.size() + "");
        //new stuff adapter
        LinearLayoutManager Llm = new LinearLayoutManager(parentActivity);
        stylistsRecyclerView.setLayoutManager(Llm);
        Llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        stylistsRecyclerView.setItemViewCacheSize(stylistModelList.size());
        StylistAdapter stylistsAdapter = new StylistAdapter(parentActivity, stylistModelList);
        stylistsRecyclerView.setAdapter(stylistsAdapter);
    }

    @OnClick(R.id.fab)
    void open() {
        if (asp.getCounty().equals("")) {
            startActivity(new Intent(parentActivity, LocationActivity.class));
        } else {
            startActivity(new Intent(parentActivity, AddNew.class));
        }
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
        if (asp.getHomeData().length() > 1) {
            hasList = true;
            bodyContent.setVisibility(View.VISIBLE);
            parseHomeDataRequest(asp.getHomeData());
        } else {
            bodyContent.setVisibility(View.INVISIBLE);
            hasList = false;
        }
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