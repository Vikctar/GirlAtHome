package com.girlathome.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.girlathome.R;
import com.girlathome.adapters.CountyAutoCompleteAdapter;
import com.girlathome.adapters.LocationAutoCompleteAdapter;
import com.girlathome.models.LocationModel;
import com.girlathome.utilities.AccountSharedPreferences;
import com.girlathome.utilities.LoadingDialog;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by steve on 4/23/17.
 */
public class CountiesFragment extends Fragment {
    private static final String TAG = CountiesFragment.class.getSimpleName();
    Activity parentActivity;
    @BindView(R.id.county)
    AutoCompleteTextView edCounty;
    @BindView(R.id.location)
    AutoCompleteTextView edLocation;
    @BindView(R.id.name_title)
    TextView tvTitle;
    @BindView(R.id.done_btn)
    Button doneBtn;
    Boolean requestedStarted = false;
    AccountSharedPreferences asp;
    List<LocationModel> countyModelList = new ArrayList<>();
    List<LocationModel> locationModelList = new ArrayList<>();
    LoadingDialog loadingDialog;

    public CountiesFragment() {
    }


    public static CountiesFragment newInstance() {
        CountiesFragment fragment = new CountiesFragment();
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
        View rootView = inflater.inflate(R.layout.counties_fragment, container, false);
        ButterKnife.bind(this, rootView);
        asp = new AccountSharedPreferences(parentActivity);
        loadingDialog = new LoadingDialog();
        setUpView();
        return rootView;
    }

    private void setUpView() {
        if (asp.getCountyList().isEmpty()) {
            locationsRequest("locations/counties", "county");
        } else {
            parseLocationData(asp.getCountyList(), "county");
        }

       /* edCounty.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (requestedStarted) {
                    locationsRequest("locations/county/" + countyModelList.get(position).getCounty_id(), "places");
                }
                requestedStarted = true;
                //get county id here
                Toast.makeText(parentActivity, countyModelList.get(position).getCounty_label(), Toast.LENGTH_SHORT).show();

            }
        });*/
        // hide location views
        edLocation.setVisibility(View.INVISIBLE);
        tvTitle.setVisibility(View.INVISIBLE);

        edCounty.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("county_name", s.toString().toUpperCase());
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                for (LocationModel county : countyModelList) {
                    if (county.getCounty_label().equals(s.toString().toUpperCase())) {

                        locationsRequest("locations/county/" + county.getCounty_id(), "places");
                        doneBtn.setVisibility(View.VISIBLE);
                        Log.d("dialog_shon", requestedStarted.toString());
                    } else {
                        doneBtn.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
    }

    @OnClick(R.id.done_btn)
    void done() {
        asp.setCounty(edCounty.getText().toString());
        asp.setPlaceLabel(edLocation.getText().toString());
        startActivity(new Intent(parentActivity, AddNew.class));
    }

    private void parseLocationData(String list, String variant) {
        try {
            JSONObject dataObject = new JSONObject(list);
            JSONArray locationsArray = dataObject.getJSONArray("data");
            for (int i = 0; i < locationsArray.length(); i++) {
                JSONObject locationObject = locationsArray.getJSONObject(i);
                LocationModel lm = new LocationModel();
                if (variant.equals("county")) {
                    lm.setCounty_id(locationObject.getString("county_id"));
                    lm.setCounty_label(locationObject.getString("county_label"));
                } else {
                    lm.setLocation_id(locationObject.getString("location_id"));
                    lm.setLocation_label(locationObject.getString("location_label"));
                }
                countyModelList.add(lm);
                Log.d("location-list", locationObject.getString("county_label"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("location-list", e.toString());
        }

        CountyAutoCompleteAdapter regionAutoCompleteAdapter = new CountyAutoCompleteAdapter(parentActivity,
                R.layout.autocomplete_row_item, countyModelList, variant);
        edCounty.setAdapter(regionAutoCompleteAdapter);


       /* new Thread() {
            public void run() {

                //show
                parentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    }
                });

            }
        }.start();*/
    }

    public void locationsRequest(final String url, final String variant) {
        final int DEFAULT_TIMEOUT = 20 * 10000;
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(DEFAULT_TIMEOUT);
        RequestParams params = new RequestParams();
        client.get(getResources().getString(R.string.base_url) + url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                Log.d("locatiion_req", url);
                loadingDialog.loadingDialog(parentActivity, "Fetching Places..");
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                String s = new String(responseBody);
                loadingDialog.dismissDialog();
                Log.d("locatiion_req", s);
                requestedStarted = false;
                if (variant.equals("places")) {
                    try {
                        JSONObject dataObject = new JSONObject(s);
                        JSONArray locationsArray = dataObject.getJSONArray("data");
                        for (int i = 0; i < locationsArray.length(); i++) {
                            JSONObject locationObject = locationsArray.getJSONObject(i);
                            LocationModel lm = new LocationModel();

                            lm.setLocation_id(locationObject.getString("location_id"));
                            lm.setLocation_label(locationObject.getString("location_label"));
                            locationModelList.add(lm);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("locatiion_req", e.toString());
                    }

                    LocationAutoCompleteAdapter regionAutoCompleteAdapter = new LocationAutoCompleteAdapter(parentActivity,
                            R.layout.autocomplete_row_item, locationModelList, "location");
                    edLocation.setAdapter(regionAutoCompleteAdapter);

                    edLocation.setVisibility(View.VISIBLE);
                    tvTitle.setVisibility(View.VISIBLE);

                } else {
                    asp.setCountyList(s);
                    parseLocationData(s, "county");
                }

            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
//                displayRetry();
                Log.d("locatiion_req-e", statusCode + "" + responseBody);
                loadingDialog.dismissDialog();

            }
        });
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