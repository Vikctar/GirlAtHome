package com.girlathome.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.girlathome.R;
import com.girlathome.models.ServiceModel;
import com.girlathome.utilities.BaseActivity;
import com.girlathome.utilities.ExpandableTextView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.nineoldandroids.view.ViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StyleDetails extends BaseActivity implements ObservableScrollViewCallbacks {
    @BindView(R.id.toolbar)
    Toolbar toolBar;
    @BindView(R.id.name)
    TextView tvName;
    @BindView(R.id.description_text)
    ExpandableTextView tvDescription;
    @BindView(R.id.see_more_description)
    TextView tvMoreDescription;
    ServiceModel styleModel;
    FragmentTransaction fragmentTransaction;
    private View mImageView;
    private View mToolbarView;
    private ObservableScrollView mScrollView;
    private int mParallaxImageHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style_details);
        ButterKnife.bind(this);
        Intent i = getIntent();
        styleModel = (ServiceModel) i.getSerializableExtra("serviceModel");
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

        tvName.setText(styleModel.getName());
//        tvMemberSince.setText(getString(R.string.member_since) + " " + stylistModel.getCreated_at());
        createFragments(new EmptyFragment());

        //see more text
        // set animation duration via code, but preferable in your layout files by using the animation_duration attribute
        tvDescription.setAnimationDuration(750L);

        // set interpolators for both expanding and collapsing animations
        tvDescription.setInterpolator(new OvershootInterpolator());

        // or set them separately
        tvDescription.setExpandInterpolator(new OvershootInterpolator());
        tvDescription.setCollapseInterpolator(new OvershootInterpolator());

        // toggle the ExpandableTextView
        tvMoreDescription.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("ConstantConditions")
            @Override
            public void onClick(final View v) {
                tvDescription.toggle();
                tvMoreDescription.setText(tvDescription.isExpanded() ? R.string.collapse : R.string.expand);
            }
        });

        // but, you can also do the checks yourself
        tvMoreDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (tvDescription.isExpanded()) {
                    tvDescription.collapse();
                    tvMoreDescription.setText(R.string.expand);
                } else {
                    tvDescription.expand();
                    tvMoreDescription.setText(R.string.collapse);
                }
            }
        });

        // listen for expand / collapse events
        tvDescription.setOnExpandListener(new ExpandableTextView.OnExpandListener() {
            @Override
            public void onExpand(final ExpandableTextView view) {
                Log.d("text-expand", "ExpandableTextView expanded");
            }

            @Override
            public void onCollapse(final ExpandableTextView view) {
                Log.d("text-expand", "ExpandableTextView collapsed");
            }
        });
    }

   /* @OnClick(R.id.book)
    void book() {
        startActivity(new Intent(getApplicationContext(), BookingActivity.class));
    }

    @OnClick(R.id.view_on_map)
    void openMap() {
//        createFragments(new MapFragment());
        startActivity(new Intent(getApplicationContext(), MapActivity.class));
    }*/


    public void createFragments(Fragment fragment) {
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.slide_up,
                R.animator.slide_down,
                R.animator.slide_up,
                R.animator.slide_down);
        fragmentTransaction.replace(R.id.container_body, fragment);
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

}
