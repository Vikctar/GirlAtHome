package com.girlathome.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.girlathome.NotificationsSettingsFragment;
import com.girlathome.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView toolbar_text;
    FragmentTransaction fragmentTransaction;
    android.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        setUpViews();
        setUpToolBar();
        setUpTitle(getString(R.string.action_settings));
    }

    private void setUpViews() {
//        createFragments(new EmptyFragment(), "empty");

    }

    private void setUpToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_text = (TextView) findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    public void createFragments(Fragment fragment, String tag) {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.slide_up,
                R.animator.slide_down,
                R.animator.slide_up,
                R.animator.slide_down);
        fragmentTransaction.replace(R.id.container_body, fragment, tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @OnClick(R.id.notifications)
    void notificationsClicked() {
        createFragments(new NotificationsSettingsFragment(), "notif");

    }

    public void setUpTitle(String title) {
        toolbar_text.setText(title);
    }

    @Override
    public void onBackPressed() {
        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (fragments == 1) {
            setUpTitle(getString(R.string.notifications_settings));
            finish();
        } else if (fragments == 2) {
            setUpTitle(getString(R.string.notifications_settings));
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

}
