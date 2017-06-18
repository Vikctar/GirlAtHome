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
 * Created by steve on 6/1/17.
 */

public class BookingsDB extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "upcomingAppointmentsTable";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_STYLE_ID = "style_id";
    public static final String COLUMN_STYLIST_ID = "stylist_id";
    public static final String COLUMN_STYLIST_NAME = "stylist_name";
    public static final String COLUMN_STYLE_NAME = "style_name";
    public static final String COLUMN_STYLE_PRICE = "style_price";
    public static final String COLUMN_STYLE_TYPE = "style_type";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_DATE_TIME = "date_time";
    public static final String COLUMN_PAYMENT_MODE = "payment_mode";
    public static final String COLUMN_PAYMENT_STATUS = "payment_status";
    public static final String COLUMN_APPOINTMENT_STATUS = "appointment_status";
    public static final String COLUMN_NOTE = "note_message";
    private static final String DATABASE_NAME = "bookingsDB";
    // Database Version
    private static final int DATABASE_VERSION = 3;

    public BookingsDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WISHLIST_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_STYLE_ID + " INTEGER," + COLUMN_STYLIST_ID + " TEXT,"
                + COLUMN_STYLIST_NAME + " TEXT,"
                + COLUMN_STYLE_NAME + " TEXT," + COLUMN_STYLE_PRICE + " TEXT," + COLUMN_STYLE_TYPE + " TEXT,"
                + COLUMN_DATE + " TEXT," + COLUMN_TIME + " TEXT," + COLUMN_DATE_TIME + " TEXT,"
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
                               String style_type, String date, String time, String dateTime, String payment_mode,
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
        contentValues.put("date_time", dateTime);
        contentValues.put("payment_mode", payment_mode);
        contentValues.put("payment_status", payment_status);
        contentValues.put("appointment_status", appointment_status);
        contentValues.put("note_message", note_message);

        db.insert(TABLE_NAME, null, contentValues);

    }

    public ArrayList<BookingModel> getAllBookings() {
        ArrayList<BookingModel> bookingModelArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT  * FROM " + TABLE_NAME + " ORDER BY datetime(date_time) DESC", null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            BookingModel bookingModel = new BookingModel();
            bookingModel.setId(res.getInt(res
                    .getColumnIndex("style_id")));
            bookingModel.setName(res.getString(res.getColumnIndex("style_name")));
            Log.d("bookings_db", "=="+ res.getString(res.getColumnIndex("date_time")));
            bookingModel.setDate(res.getString(res.getColumnIndex("date")));
            bookingModel.setTime(res.getString(res.getColumnIndex("time")));
            bookingModel.setDateTime(res.getString(res.getColumnIndex("date_time")));
            bookingModelArrayList.add(bookingModel);
            res.moveToNext();
        }
        res.close();
        return bookingModelArrayList;
    }

    /*    public ArrayList<BookingModel> getAllInstitutions() {
            ArrayList<BookingModel> BookingModelArrayList = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res = db.rawQuery("select * from institutionTable", null);
            res.moveToFirst();
            while (!res.isAfterLast()) {

                BookingModel bookingModel = new BookingModel();
                bookingModel.setId(res.getInt(res
                        .getColumnIndex("inst_id")));
                bookingModel.setName(res.getString(res.getColumnIndex("name")));
                bookingModel.setInstitutionTypeId(res.getString(res.getColumnIndex("institution_type_id")));
                bookingModel.setPhone(res.getString(res.getColumnIndex("phone")));
                bookingModel.setAlternativePhone(res.getString(res.getColumnIndex("alt_phone")));
                bookingModel.setEmail(res.getString(res.getColumnIndex("email")));
                bookingModel.setWebsite(res.getString(res.getColumnIndex("website")));
                bookingModel.setPostalAddress(res.getString(res.getColumnIndex("postal_address")));
                bookingModel.setPhoto(res.getString(res.getColumnIndex("photo")));
                bookingModel.setLogo(res.getString(res.getColumnIndex("logo")));
                bookingModel.setDescription(res.getString(res.getColumnIndex("description")));
                bookingModel.setAccountEmail(res.getString(res.getColumnIndex("email")));
                bookingModel.setAccountPhone(res.getString(res.getColumnIndex("phone")));
                bookingModel.setCountyName(res.getString(res.getColumnIndex("county")));
                bookingModel.setSubcountyName(res.getString(res.getColumnIndex("sub_county")));
                bookingModel.setRegionName(res.getString(res.getColumnIndex("region")));
                bookingModel.setPackageId(res.getString(res.getColumnIndex("package_id")));
                bookingModel.setGender(res.getString(res.getColumnIndex("gender")));
                bookingModel.setAccommodation(res.getString(res.getColumnIndex("accommodation")));
                bookingModel.setReligiousAffiliation(res.getString(res.getColumnIndex("religious_affiliation")));
                bookingModel.setSpecialNeeds(res.getString(res.getColumnIndex("specials_needs")));
                BookingModelArrayList.add(bookingModel);
                res.moveToNext();
            }
            res.close();
            for (int i = 0; i < BookingModelArrayList.size(); i++) {
                Log.d("region_2", "" + BookingModelArrayList.get(i).getRegionName());
            }
            return BookingModelArrayList;
        }

        public void deleteInstitutions() {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("institutionTable", null, null);
            db.close();
        }

        public void updateInstitution(int institutionId, String institutionName, String countyName,
                                      String subCountyName, String schoolEmail, String schoolPhone,
                                      String postalAddress, String website, String gender, String accommodation, String religiousAffiliation, String specialNeeds) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put("name", institutionName);
            contentValues.put("phone", schoolPhone);
            contentValues.put("email", schoolEmail);
            contentValues.put("website", website);
            contentValues.put("postal_address", postalAddress);
            contentValues.put("county", countyName);
            contentValues.put("sub_county", subCountyName);
            contentValues.put("gender", gender);
            contentValues.put("accommodation", accommodation);
            contentValues.put("religious_affiliation", religiousAffiliation);
            contentValues.put("specials_needs", specialNeeds);
            // update Row
            db.update(TABLE_NAME, contentValues, COLUMN_INST_ID + "=" + institutionId, null);
        }*/
    public void deleteAllAppointments() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("upcomingAppointmentsTable", null, null);
        db.close();
    }
}
