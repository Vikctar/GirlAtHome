package com.girlathome.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.girlathome.R;
import com.girlathome.models.StylistModel;
import com.girlathome.utilities.BaseActivity;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.nineoldandroids.view.ViewHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StylistDetails extends BaseActivity implements ObservableScrollViewCallbacks {
    @BindView(R.id.toolbar)
    Toolbar toolBar;
    @BindView(R.id.name)
    TextView tvName;
    @BindView(R.id.member_since)
    TextView tvMemberSince;
    StylistModel stylistModel;
    FragmentTransaction fragmentTransaction;
    android.app.FragmentManager fragmentManager;
    private View mImageView;
    private View mToolbarView;
    private ObservableScrollView mScrollView;
    private int mParallaxImageHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stylist_details);
        ButterKnife.bind(this);
        Intent i = getIntent();
        stylistModel = (StylistModel) i.getSerializableExtra("stylistModel");
        setViews();
    }

    private void setViews() {
        mImageView = findViewById(R.id.avatar);
        mToolbarView = findViewById(R.id.toolbar);
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.colorPrimary)));
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        mScrollView = (ObservableScrollView) findViewById(R.id.scroll);
        mScrollView.setScrollViewCallbacks(this);

        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.parallax_image_height);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        tvName.setText(stylistModel.getName());
//        tvMemberSince.setText(getString(R.string.member_since) + " " + stylistModel.getCreated_at());
        createFragments(new EmptyFragment(), "empty");
    }

    String getMemberSinceData(String dataString) throws ParseException {
        String strCurrentDate = "Wed, 18 Apr 2012 07:55:29 +0000";
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss Z");
        Date newDate = format.parse(strCurrentDate);

        format = new SimpleDateFormat("MMM dd,yyyy hh:mm a");
        String date = format.format(newDate);
        return date;
    }

    @OnClick(R.id.book)
    void book() {
        /*Intent i = new Intent(getApplicationContext(), BookingActivity.class);
        i.putExtra("serviceModel", stylistModel);
        startActivity(i);*/
        createFragments(new StylesAvailableFragment(), "available");
    }

    @OnClick(R.id.view_on_map)
    void openMap() {
//        createFragments(new MapFragment());
        startActivity(new Intent(getApplicationContext(), MapActivity.class));
    }


    public void createFragments(Fragment fragment, String tag) {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.slide_up,
                R.animator.slide_down,
                R.animator.slide_up,
                R.animator.slide_down);
        fragmentTransaction.replace(R.id.container_body, fragment,tag);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.stylist_details, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onScrollChanged(mScrollView.getCurrentScrollY(), false, false);
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = getResources().getColor(R.color.colorPrimary);
        float alpha = Math.min(1, (float) scrollY / mParallaxImageHeight);
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));
        ViewHelper.setTranslationY(mImageView, scrollY / 2);
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Fragment currentFragment = fragmentManager.findFragmentByTag("empty");
        if (currentFragment != null) {
            finish();
        } else {
            createFragments(new EmptyFragment(), "empty");
        }

        return true;
    }
}
