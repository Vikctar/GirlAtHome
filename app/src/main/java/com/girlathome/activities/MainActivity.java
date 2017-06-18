package com.girlathome.activities;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.girlathome.R;
import com.girlathome.utilities.AccountSharedPreferences;
import com.girlathome.utilities.BadgeDrawable;
import com.girlathome.utilities.NavigationDrawerCallbacks;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity
        implements NavigationDrawerCallbacks {
    LayerDrawable icon;
    String count;
    MenuItem itemCart;
    FragmentTransaction fragmentTransaction;
    Toolbar toolbar;
    TextView toolbar_text;
    boolean activity_started = true;
    AccountSharedPreferences accountSharedPreferences;
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpViews();
        setUpToolBar("Home");
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);
        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), toolbar);
        mNavigationDrawerFragment.closeDrawer();
        accountSharedPreferences = new AccountSharedPreferences(this);

    }

    private void setUpToolBar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_text = (TextView) findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar_text.setText(title);
    }

    private void setUpViews() {
        locationsRequest("locations/counties", "counties");
        locationsRequest("locations/all", "location");
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        String title = getString(R.string.home);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                if (!activity_started) {
                    title = getString(R.string.home);
                    toolbar_text.setText(title);
                }
                activity_started = false;
                break;
            case 1:
                fragment = new GalleryFragment();
                title = getString(R.string.gallery);
                toolbar_text.setText(title);

                break;
            case 2:
                fragment = new StylistsFragment();
                title = getString(R.string.stylists);
                toolbar_text.setText(title);
                break;
            case 3:
                fragment = new ProductsFragment();
                title = getString(R.string.products);
                toolbar_text.setText(title);
                break;
            case 4:
                fragment = new BookingsFragment();
                title = getString(R.string.my_bookings);
                toolbar_text.setText(title);
                break;
            case 5:
                fragment = new FavouritesFragment();
                title = getString(R.string.favourites);
                toolbar_text.setText(title);
                break;
            case 6:
                fragment = new VirtualMakeOverFragment();
                title = getString(R.string.virtual_makeover);
                toolbar_text.setText(title);
                break;
            case 7:
                fragment = new AccountFragment();
//                startActivity(new Intent(getApplicationContext(), LoginOrSignUp.class));
//                title = getString(R.string.home);
                title = getString(R.string.account);
                toolbar_text.setText(title);
                break;

        }
//        Toast.makeText(this, activity_started + title + " selected -> " + position, Toast.LENGTH_SHORT).show();
//        if (position != 7) {
//            createFragments(fragment, title);
//        }
        createFragments(fragment, title);
    }

    private void createFragments(Fragment fragment, String title) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_body, fragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    public void showMyBookingsFragment() {
     /*Intent intent = new Intent(getActivity(), Login.class);
        intent.putExtra("loginOut", message);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);*/
    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            itemCart = menu.findItem(R.id.action_booking);
            itemCart.setVisible(false);

            icon = (LayerDrawable) itemCart.getIcon();
            setBadgeCount(MainActivity.this, icon);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    public void setBadgeCount(final Context context, final LayerDrawable icon) {

        new Thread() {
            public void run() {
//                final int profile_counts = cartDB.numberOfRows();
//                count = String.valueOf(profile_counts);
                count = "2";
                //show
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        BadgeDrawable badge;

                        // Reuse drawable if possible
                        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_badge);
                        if (reuse != null && reuse instanceof BadgeDrawable) {
                            badge = (BadgeDrawable) reuse;
                        } else {
                            badge = new BadgeDrawable(context);
                        }

                        badge.setCount(count);
                        icon.mutate();
                        icon.setDrawableByLayerId(R.id.ic_badge, badge);
                    }
                });

            }
        }.start();


    }

    public void locationsRequest(String url, final String variant) {
        /*final BackgroundNetworkRequest backgroundNetworkRequest = new BackgroundNetworkRequest();
        if (!backgroundNetworkRequest.makeRequest(this, "locations/counties").equals("error")) {
            new Thread() {
                public void run() {
                    accountSharedPreferences.setCountyList(backgroundNetworkRequest.makeRequest(MainActivity.this, "locations/counties"));
                }
            };
        }*/
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        client.get(getResources().getString(R.string.base_url) + url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                Log.d("backgrnd_req", "started");
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                String s = new String(responseBody);
                Log.d("backgrnd_req", s);
                System.out.print(s);
                if (variant.equals("counties")) {
                    accountSharedPreferences.setCountyList(s);
                } else {
                    accountSharedPreferences.setLocationList(s);
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
//                displayRetry();
                Log.d("backgrnd_req", statusCode + "");

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
