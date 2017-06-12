package com.girlathome.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.girlathome.R;

import butterknife.ButterKnife;

public class BookingActivityDetails extends AppCompatActivity {
    Toolbar toolbar;
    TextView toolbar_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        ButterKnife.bind(this);
        setUpViews();
        setUpToolBar();
        setUpTitle(getString(R.string.details));
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
        Intent i = getIntent();
       /* styleModel = (ServiceModel) i.getSerializableExtra("serviceModel");
        BookingModel bookingModel = new BookingModel();
        bookingModel.setName(styleModel.getName());
        setStyleName(styleModel.getName());*/

    }
}
