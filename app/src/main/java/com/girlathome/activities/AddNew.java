package com.girlathome.activities;

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

import butterknife.ButterKnife;

public class AddNew extends AppCompatActivity {

    FragmentTransaction fragmentTransaction;
    Toolbar toolbar;
    TextView toolbar_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        ButterKnife.bind(this);
        setUpViews();
        setUpToolBar();
        setUpTitle("List Your Item");
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
        createFragments(new TypeToAddFragment());
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
