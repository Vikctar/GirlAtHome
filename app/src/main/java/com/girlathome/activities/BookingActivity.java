package com.girlathome.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.girlathome.R;
import com.girlathome.models.BookingModel;
import com.girlathome.models.ServiceModel;

import butterknife.ButterKnife;

public class BookingActivity extends AppCompatActivity {
    FragmentTransaction fragmentTransaction;
    Toolbar toolbar;
    TextView toolbar_text;
    String item, dateSelected, paymentMode, timeSelected, styleName;
    ServiceModel styleModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButterKnife.bind(this);
        setUpViews();
        setUpToolBar();
        setUpTitle(getString(R.string.pick_a_date));
    }

    private void setUpToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_text = (TextView) findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


    }

    public void setUpTitle(String title) {
        toolbar_text.setText(title);
    }

    private void setUpViews() {
        createFragments(new DateFragment());
        Intent i = getIntent();
        styleModel = (ServiceModel) i.getSerializableExtra("serviceModel");
        BookingModel bookingModel = new BookingModel();
        bookingModel.setName(styleModel.getName());
        setStyleName(styleModel.getName());

    }

    public void createFragments(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_body, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (fragments == 1) {
            setUpTitle(getString(R.string.pick_a_date));
            finish();
        } else if (fragments == 2) {
            setUpTitle(getString(R.string.pick_a_date));
        } else if (fragments == 3) {
            setUpTitle(getString(R.string.pick_a_time));
        } else if (fragments == 4) {
            setUpTitle(getString(R.string.payment));
        } else if (fragments == 5) {
           finish();
        }
        Log.d("no_of_frga", fragments + "");
        super.onBackPressed();
    }

    public void setTypeOfItem(String item) {
        this.item = item;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String getDateSelected() {
        return dateSelected;
    }

    public void setDateSelected(String dateSelected) {
        this.dateSelected = dateSelected;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public void setDate(String dateSelected) {
        this.dateSelected = dateSelected;
    }

    public String getTime() {
        return timeSelected;
    }

    public void setTime(String timeSelected) {
        this.timeSelected = timeSelected;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }
}
