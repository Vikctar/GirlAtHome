package com.girlathome.utilities;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by steve on 4/23/17.
 */

public class AccountSharedPreferences {
    SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public AccountSharedPreferences(Activity activity) {
        sharedPreferences = activity.getSharedPreferences("ACCOUNT", activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public String getLocationList() {
        return sharedPreferences.getString("location_list", "");
    }

    public void setLocationList(String countyList) {
        editor.putString("location_list", countyList);
        editor.apply();
    }

    public String getCountyList() {
        return sharedPreferences.getString("county_list", "");
    }

    public void setCountyList(String countyList) {
        editor.putString("county_list", countyList);
        editor.apply();
    }

    public void setCounty(String county) {
        editor.putString("county", county);
        editor.apply();
    }
    public String getCounty() {
        return sharedPreferences.getString("county", "");
    }

    public void setPlaceLabel(String label) {
        editor.putString("place_label", label);
        editor.apply();
    }

    public void setPlaceLabelList(String labelList) {
        editor.putString("place_label_list", labelList);
        editor.apply();
    }

}
