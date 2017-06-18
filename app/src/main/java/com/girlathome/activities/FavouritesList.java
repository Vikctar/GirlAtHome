package com.girlathome.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.girlathome.R;
import com.girlathome.adapters.StylesAdapter;
import com.girlathome.databaseHandlers.FavouritesDB;
import com.girlathome.models.ServiceModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouritesList extends AppCompatActivity {
    Toolbar toolbar;
    TextView toolbar_text;
    @BindView(R.id.styles_recyclerview)
    RecyclerView stylesRecyclerView;
    List<ServiceModel> serviceModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_list);
        ButterKnife.bind(this);
        setUpToolBar();
        setUpViews();

    }

    private void setUpViews() {
        Intent intent = getIntent();
        String variant = intent.getStringExtra("category_variant");
        if (variant.equalsIgnoreCase("styles")) {
            setUpTitle(getString(R.string.favourites_styles));
        }

        FavouritesDB favouritesDB = new FavouritesDB(this);
        serviceModelList = favouritesDB.getAllFavouriteStyles();
        setUpAdapter();

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

    private void setUpAdapter() {

        RecyclerView.LayoutManager stylistLlm = new GridLayoutManager(this, 2);
        stylesRecyclerView.setHasFixedSize(true);
        stylesRecyclerView.setLayoutManager(stylistLlm);
        stylesRecyclerView.setItemViewCacheSize(serviceModelList.size());
        StylesAdapter stylesAdapter = new StylesAdapter(this, serviceModelList, "gallery");
        stylesRecyclerView.setAdapter(stylesAdapter);
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
