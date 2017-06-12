package com.girlathome.activities;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.girlathome.R;
import com.girlathome.adapters.CategoriesAdapter;
import com.girlathome.adapters.StylesAdapter;
import com.girlathome.adapters.StylistAdapter;
import com.girlathome.models.CategoriesModel;
import com.girlathome.models.ServiceModel;
import com.girlathome.models.StylistModel;
import com.girlathome.utilities.AccountSharedPreferences;
import com.girlathome.utilities.ConnectivityUtility;
import com.girlathome.utilities.MyAlarmService;
import com.girlathome.utilities.ScheduleClient;
import com.girlathome.utilities.ScheduleService;
import com.girlathome.utilities.TimeTask;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tuyenmonkey.mkloader.MKLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.ALARM_SERVICE;


/**
 * Created by steve on 4/16/17.
 */
public class HomeFragment extends Fragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    Activity parentActivity;
    AccountSharedPreferences asp;
    ConnectivityUtility cu;
    @BindView(R.id.progress)
    MKLoader mkLoader;
    @BindView(R.id.just_booked_recyclerview)
    RecyclerView justBookedRecyclerView;
    @BindView(R.id.styles_recyclerview)
    RecyclerView stylesRecyclerView;
    @BindView(R.id.stylists_recyclerview)
    RecyclerView stylistsRecyclerView;
    @BindView(R.id.categories_recyclerview)
    RecyclerView categoriesRecyclerView;
    @BindView(R.id.body_content)
    ScrollView bodyContent;
    Boolean hasList = false;
    List<StylistModel> stylistModelList = new ArrayList<>();
    List<ServiceModel> serviceModelList = new ArrayList<>();
    List<CategoriesModel> categoriesData = new ArrayList<>();
    ServiceConnection serviceConnection;
    private ScheduleService mBoundService;
    private ScheduleClient scheduleClient;// This is a handle so that we can call methods on our service
    private PendingIntent pendingIntent;

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
        cu = new ConnectivityUtility();
        TimeTask timeTask = new TimeTask();
        try {
            Log.d("time_to_convert", timeTask.formatInto24HRS("12/12/2017 02:00 pm"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rootView;
    }

    public void setAlarmTime() {
        Intent myIntent = new Intent(parentActivity, MyAlarmService.class);

        pendingIntent = PendingIntent.getService(parentActivity, 0, myIntent, 0);


        AlarmManager alarmManager = (AlarmManager) parentActivity.getSystemService(ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();

//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.set(2016, 7, 9, 17, 40, 0);//23 January, 2016, 18:05:00.
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 40);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);


//        calendar.add(Calendar.SECOND, 10);
//        calendar.set(Calendar.DATE, 9);  //1-31
//        calendar.set(Calendar.MONTH, 7);  //first month is 0!!! January is zero!!!
//        calendar.set(Calendar.YEAR, 2017);
//
//        calendar.set(Calendar.HOUR_OF_DAY, 16);
//        calendar.set(Calendar.MINUTE, 11);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);


        Toast.makeText(parentActivity, "Start Alarm", Toast.LENGTH_LONG).show();
        // Create a new service client and bind our activity to this service
        // This is called when the connection with our service has been established,
        // giving us the service object we can use to interact with our service.
       /* serviceConnection = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mBoundService = ((ScheduleService.ServiceBinder) service).getService();
                String input = "09/06/2017 15:03:00";
                Calendar cal = Calendar.getInstance();
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.ENGLISH);
                try {
                    date = sdf.parse(input);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Log.d("date_set", "" + date.getDate() + "/" + date.getMonth()+1 + "/" + date.getYear() + "==" + date.getHours() + ":" +
                        date.getMinutes());
               *//* cal.set(Calendar.DATE, date.getDate());  //1-31
                cal.set(Calendar.MONTH, date.getMonth() + 1);  //first month is 0!!! January is zero!!!
                cal.set(Calendar.YEAR, 2017);

                cal.set(Calendar.HOUR_OF_DAY, date.getHours());
                cal.set(Calendar.MINUTE, date.getMinutes());*//*
                cal.setTimeInMillis(System.currentTimeMillis());

                cal.add(Calendar.SECOND, 10);

//                cal.add(Calendar.MINUTE, -60);
                // Ask our service to set an alarm for that date, this activity talks to the client that talks to the service
//                scheduleClient.setAlarmForNotification(cal);
                Log.d("date_set", "" + cal);
                mBoundService.setAlarm(cal);
                // Notify the user what they just did
                Toast.makeText(parentActivity, "Notification set for: " + cal, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mBoundService = null;
            }
        };
        Intent bindIntent = new Intent(parentActivity, ScheduleService.class);
        parentActivity.bindService(bindIntent, serviceConnection, Context.BIND_AUTO_CREATE);*/

        /*// time at which alarm will be scheduled here alarm is scheduled at 1 day from current time,
        // we fetch  the current time in milliseconds and added 1 day time
        // i.e. 24*60*60*1000= 86,400,000   milliseconds in a day
        Long time = new GregorianCalendar().getTimeInMillis() + 24 * 60 * 60 * 1000;

        // create an Intent and set the class which will execute when Alarm triggers, here we have
        // given AlarmReciever in the Intent, the onRecieve() method of this class will execute when
        // alarm triggers and
        //we will write the code to send SMS inside onRecieve() method pf Alarmreciever class
        Intent intentAlarm = new Intent(parentActivity, AlarmReciever.class);

        // create the object
        AlarmManager alarmManager = (AlarmManager) parentActivity.getSystemService(Context.ALARM_SERVICE);

        //set the alarm for particular time
        alarmManager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + 20000,
                PendingIntent.getBroadcast(parentActivity, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
        Toast.makeText(parentActivity, "Alarm Scheduled for Tommrrow", Toast.LENGTH_LONG).show();*/
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
        serviceModelList.clear();
        categoriesData.clear();
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
                stylistModelList.add(stylistModel);
            }
            Log.d("stylists_1", stylistModelList.size() + "");


            //set Dummy Data for styles, just booked, categories

            //God, i feel so dirty doing this, like a dirty dirty stripper who got stuffed
            //with some shit where the light dont shine.
            //Oh help me Lord.


            for (int i = 0; i < 4; i++) {
                ServiceModel serviceModel = new ServiceModel();
                serviceModel.setName("Jamaican Braids");
                serviceModelList.add(serviceModel);
            }

            categoriesData.add(new CategoriesModel(R.drawable.ic_categories, "All Categories"));
            categoriesData.add(new CategoriesModel(R.drawable.image_placeholder, "Relaxed Hair"));
            categoriesData.add(new CategoriesModel(R.drawable.image_placeholder, "Hair Cuts"));
            categoriesData.add(new CategoriesModel(R.drawable.image_placeholder, "Lines"));
            categoriesData.add(new CategoriesModel(R.drawable.image_placeholder, "Braids"));
            categoriesData.add(new CategoriesModel(R.drawable.image_placeholder, "Weaves"));
            categoriesData.add(new CategoriesModel(R.drawable.image_placeholder, "Extensions"));
            categoriesData.add(new CategoriesModel(R.drawable.image_placeholder, "Wigs"));
            categoriesData.add(new CategoriesModel(R.drawable.image_placeholder, "Manicures"));
            categoriesData.add(new CategoriesModel(R.drawable.image_placeholder, "Pedicures"));
            categoriesData.add(new CategoriesModel(R.drawable.image_placeholder, "Facials"));


            setUpAdapter();
            displayContent();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("locatiion_req", e.toString());
        }
    }

    private void setUpAdapter() {

        Log.d("stylists_2", stylistModelList.size() + "");

        //stylists adapter
        LinearLayoutManager stylistLlm = new LinearLayoutManager(parentActivity);
        stylistsRecyclerView.setLayoutManager(stylistLlm);
        stylistLlm.setOrientation(LinearLayoutManager.HORIZONTAL);
        stylistsRecyclerView.setItemViewCacheSize(stylistModelList.size());
        StylistAdapter stylistsAdapter = new StylistAdapter(parentActivity, stylistModelList, "home");
        stylistsRecyclerView.setAdapter(stylistsAdapter);

        //just booked adapter
        LinearLayoutManager justBookedLlm = new LinearLayoutManager(parentActivity);
        justBookedRecyclerView.setLayoutManager(justBookedLlm);
        justBookedLlm.setOrientation(LinearLayoutManager.HORIZONTAL);
        justBookedRecyclerView.setItemViewCacheSize(serviceModelList.size());
        StylesAdapter stylesAdapter = new StylesAdapter(parentActivity, serviceModelList, "home");
        justBookedRecyclerView.setAdapter(stylesAdapter);

        //styles adapter
        LinearLayoutManager stylesLlm = new LinearLayoutManager(parentActivity);
        stylesRecyclerView.setLayoutManager(stylesLlm);
        stylesLlm.setOrientation(LinearLayoutManager.HORIZONTAL);
        stylesRecyclerView.setItemViewCacheSize(serviceModelList.size());
        stylesRecyclerView.setAdapter(stylesAdapter);

        //categories adapter
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(parentActivity, categoriesData);
        LinearLayoutManager categoriesLlm = new LinearLayoutManager(parentActivity);
        categoriesRecyclerView.setLayoutManager(categoriesLlm);
        categoriesLlm.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoriesRecyclerView.setItemViewCacheSize(categoriesData.size());
        categoriesRecyclerView.setAdapter(categoriesAdapter);
    }

    @OnClick(R.id.fab)
    void open() {
        setAlarmTime();
        /*if (asp.getCounty().equals("")) {
            startActivity(new Intent(parentActivity, LocationActivity.class));
        } else {
            startActivity(new Intent(parentActivity, AddNew.class));
        }*/
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
        displayContent();
        parseHomeDataRequest(asp.getHomeData());
        if (cu.isOnline()) {
            //update data in background
            homeDataRequest("all_stylists");
        } else {//not connected to internet
            // TODO: 6/3/17  display empty-ness/needs internet connection
            //display empty-ness
        }
        // Create a new service client and bind our activity to this service
        scheduleClient = new ScheduleClient(getContext());
        scheduleClient.doBindService();

    }

    private void displayContent() {
        //check if there is existent data
        if (asp.getHomeData().length() > 1) {
            hasList = true;
            bodyContent.setVisibility(View.VISIBLE);
        } else {
            hasList = false;
            bodyContent.setVisibility(View.INVISIBLE);
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
    public void onStop() {
        // When our activity is stopped ensure we also stop the connection to the service
        // this stops us leaking our activity into the system *bad*
        if (scheduleClient != null)
            scheduleClient.doUnbindService();
        super.onStop();
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