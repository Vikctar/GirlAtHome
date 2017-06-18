package com.girlathome.databaseHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.girlathome.models.ServiceModel;
import com.girlathome.models.StylistModel;

import java.util.ArrayList;

/**
 * Created by steve on 6/5/17.
 */

public class FavouritesDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "favouritesDB";
    //styles
    public static final String TABLE_STYLES = "favouriteStyleTable";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_STYLE_ID = "style_id";
    public static final String COLUMN_STYLE_NAME = "style_name";
    public static final String COLUMN_STYLE_PRICE = "style_price";
    public static final String COLUMN_STYLE_TYPE = "style_type";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_DATE_TIME = "date_time";
    //stylist
    public static final String TABLE_STYLIST = "favouriteStylistTable";
    public static final String COLUMN_STYLIST_ID = "stylist_id";
    public static final String COLUMN_STYLIST_NAME = "stylist_name";


    // Database Version
    private static final int DATABASE_VERSION = 1;

    public FavouritesDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STYLES_TABLE = "CREATE TABLE " + TABLE_STYLES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_STYLE_ID + " INTEGER," + COLUMN_STYLIST_ID + " TEXT,"
                + COLUMN_STYLIST_NAME + " TEXT,"
                + COLUMN_STYLE_NAME + " TEXT," + COLUMN_STYLE_PRICE + " TEXT," + COLUMN_STYLE_TYPE + " TEXT,"
                + COLUMN_DATE + " TEXT," + COLUMN_TIME + " TEXT,"
                /*+ COLUMN_PAYMENT_MODE + " TEXT," + COLUMN_PAYMENT_STATUS + " TEXT,"
                + COLUMN_APPOINTMENT_STATUS + " TEXT,"*/
                + COLUMN_DATE_TIME + " TEXT" + ")";

        String CREATE_STYLISTS_TABLE = "CREATE TABLE " + TABLE_STYLIST + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_STYLIST_ID + " INTEGER,"
                + COLUMN_STYLIST_NAME + " TEXT,"
                + COLUMN_DATE + " TEXT," + COLUMN_TIME + " TEXT,"
                + COLUMN_DATE_TIME + " TEXT" + ")";

        db.execSQL(CREATE_STYLES_TABLE);
        db.execSQL(CREATE_STYLISTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STYLES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STYLIST);
        onCreate(db);
    }

    //STYLES

    public void addFavouriteStyle(int style_id, String stylist_id, String stylist_name, String style_name, String style_price,
                                  String style_type, String date, String time, String date_time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("style_id", style_id);
        contentValues.put("stylist_id", stylist_id);
        contentValues.put("stylist_name", stylist_name);
        contentValues.put("style_name", style_name);
        contentValues.put("style_price", style_price);
        contentValues.put("style_type", style_type);
        contentValues.put("date", date);
        contentValues.put("time", time);
        contentValues.put("date_time", date_time);

        db.insert(TABLE_STYLES, null, contentValues);

    }

    public ArrayList<ServiceModel> getAllFavouriteStyles() {
        ArrayList<ServiceModel> styleModelArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT  * FROM " + TABLE_STYLES + " ORDER BY datetime(date_time) DESC", null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            ServiceModel styleModel = new ServiceModel();
            styleModel.setId(res.getInt(res
                    .getColumnIndex("style_id")));
            styleModel.setName(res.getString(res.getColumnIndex("style_name")));
            Log.d("bookings_db", "==" + styleModel.getName() +
                    res.getString(res.getColumnIndex("date")) + res.getString(res.getColumnIndex("time")));
            styleModel.setDate(res.getString(res.getColumnIndex("date")));
            styleModel.setTime(res.getString(res.getColumnIndex("time")));
            styleModelArrayList.add(styleModel);
            res.moveToNext();
        }
        res.close();
        return styleModelArrayList;
    }

    public int getStylesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_STYLES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        // return row count
        cursor.close();
        return cnt;
    }


    //STYLISTS

    public void addFavouriteStylist(int stylist_id, String stylist_name, String date, String time, String date_time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("stylist_id", stylist_id);
        contentValues.put("stylist_name", stylist_name);
        contentValues.put("date", date);
        contentValues.put("time", time);
        contentValues.put("date_time", date_time);

        db.insert(TABLE_STYLES, null, contentValues);

    }

    public ArrayList<StylistModel> getAllFavouriteStylists() {
        ArrayList<StylistModel> stylistModelArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT  * FROM " + TABLE_STYLES + " ORDER BY datetime(date_time) DESC", null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            StylistModel stylistModel = new StylistModel();
            stylistModel.setId(res.getInt(res
                    .getColumnIndex("style_id")));
            stylistModel.setName(res.getString(res.getColumnIndex("style_name")));
            Log.d("bookings_db", "==" + stylistModel.getName() +
                    res.getString(res.getColumnIndex("date")) + res.getString(res.getColumnIndex("time")));
            stylistModel.setDate(res.getString(res.getColumnIndex("date")));
            stylistModel.setTime(res.getString(res.getColumnIndex("time")));
            stylistModelArrayList.add(stylistModel);
            res.moveToNext();
        }
        res.close();
        return stylistModelArrayList;
    }

    public int getStylistsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_STYLIST;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        // return row count
        cursor.close();
        return cnt;

    }
}
