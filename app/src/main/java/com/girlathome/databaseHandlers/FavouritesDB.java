package com.girlathome.databaseHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.girlathome.models.BookingModel;

import java.util.ArrayList;

/**
 * Created by steve on 6/5/17.
 */

public class FavouritesDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "favouritesDB";
    public static final String TABLE_NAME = "categoriesTable";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_STYLE_ID = "style_id";
    public static final String COLUMN_STYLIST_ID = "stylist_id";
    public static final String COLUMN_STYLIST_NAME = "stylist_name";
    public static final String COLUMN_STYLE_NAME = "style_name";
    public static final String COLUMN_STYLE_PRICE = "style_price";
    public static final String COLUMN_STYLE_TYPE = "style_type";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_PAYMENT_MODE = "payment_mode";
    public static final String COLUMN_PAYMENT_STATUS = "payment_status";
    public static final String COLUMN_APPOINTMENT_STATUS = "appointment_status";
    public static final String COLUMN_NOTE = "note_message";

    // Database Version
    private static final int DATABASE_VERSION = 2;

    public FavouritesDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WISHLIST_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_STYLE_ID + " INTEGER," + COLUMN_STYLIST_ID + " TEXT,"
                + COLUMN_STYLIST_NAME + " TEXT,"
                + COLUMN_STYLE_NAME + " TEXT," + COLUMN_STYLE_PRICE + " TEXT," + COLUMN_STYLE_TYPE + " TEXT,"
                + COLUMN_DATE + " TEXT," + COLUMN_TIME + " TEXT,"
                + COLUMN_PAYMENT_MODE + " TEXT," + COLUMN_PAYMENT_STATUS + " TEXT,"
                + COLUMN_APPOINTMENT_STATUS + " TEXT,"
                /*
                 + COLUMN_STYLE_ID + " INTEGER UNIQUE,"
                + COLUMN_APPOINTMENT_STATUS + " TEXT," + COLUMN_POSTALADDRESS + " TEXT,"
                + COLUMN_WEBSITE + " TEXT,"
                + COLUMN_GENDER + " TEXT,"
                + COLUMN_ACCOMMODATION + " TEXT,"
                + COLUMN_RELIGIOUS_AFFILIATION + " TEXT,"
                + COLUMN_SPECIAL_NEEDS + " TEXT," + COLUMN_PACKAGE_ID + " TEXT,"*/
                + COLUMN_NOTE + " TEXT" + ")";

        db.execSQL(CREATE_WISHLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addAppointment(int style_id, String stylist_id, String stylist_name, String style_name, String style_price,
                               String style_type, String date, String time, String payment_mode,
                               String payment_status, String appointment_status, String note_message) {
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
        contentValues.put("payment_mode", payment_mode);
        contentValues.put("payment_status", payment_status);
        contentValues.put("appointment_status", appointment_status);
        contentValues.put("note_message", note_message);

        db.insert(TABLE_NAME, null, contentValues);

    }

    public ArrayList<BookingModel> getAllBookings() {
        ArrayList<BookingModel> bookingModelArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from upcomingAppointmentsTable", null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            BookingModel bookingModel = new BookingModel();
            bookingModel.setId(res.getInt(res
                    .getColumnIndex("style_id")));
            bookingModel.setName(res.getString(res.getColumnIndex("style_name")));
            Log.d("bookings_db", "==" + bookingModel.getName() +
                    res.getString(res.getColumnIndex("date")) + res.getString(res.getColumnIndex("time")));
            bookingModel.setDate(res.getString(res.getColumnIndex("date")));
            bookingModel.setTime(res.getString(res.getColumnIndex("time")));
            bookingModelArrayList.add(bookingModel);
            res.moveToNext();
        }
        res.close();
        return bookingModelArrayList;
    }
}
