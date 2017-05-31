package com.girlathome.utilities;

/**
 * Created by steve on 5/10/17.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.girlathome.R;
import com.girlathome.activities.BookingActivity;
import com.girlathome.activities.TimeFragment;
import com.girlathome.adapters.CalendarGridAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarCustomView extends LinearLayout {
    private static final String TAG = CalendarCustomView.class.getSimpleName();
    private static final int MAX_CALENDAR_COLUMN = 42;
    List<Date> dayValueInCells = new ArrayList<Date>();
    private ImageView previousButton, nextButton;
    private TextView currentDate;
    private GridView calendarGridView;
    private Button addEventButton;
    private int month, year;
    private SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    private Calendar cal = Calendar.getInstance(Locale.ENGLISH);
    private Context context;
    private CalendarGridAdapter mAdapter;

    public CalendarCustomView(Context context) {
        super(context);
    }

    public CalendarCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initializeUILayout();
        setUpCalendarAdapter();
        setPreviousButtonClickEvent();
        setNextButtonClickEvent();
        setGridCellClickEvents();
        Log.d(TAG, "I need to call this method");
    }

    public CalendarCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initializeUILayout() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.calendar_layout, this);
        previousButton = (ImageView) view.findViewById(R.id.previous_month);
        nextButton = (ImageView) view.findViewById(R.id.next_month);
        currentDate = (TextView) view.findViewById(R.id.display_current_date);
//        addEventButton = (Button) view.findViewById(R.id.add_calendar_event);
        calendarGridView = (GridView) view.findViewById(R.id.calendar_grid);
    }

    private void setPreviousButtonClickEvent() {
        previousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.add(Calendar.MONTH, -1);
                setUpCalendarAdapter();
            }
        });
    }

    private void setNextButtonClickEvent() {
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.add(Calendar.MONTH, 1);
                setUpCalendarAdapter();
            }
        });
    }

    private void setGridCellClickEvents() {
        calendarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Date mDate = dayValueInCells.get(position);
                Calendar dateCal = Calendar.getInstance();
                dateCal.setTime(mDate);
                int dayValue = dateCal.get(Calendar.DAY_OF_MONTH);
                int displayMonth = dateCal.get(Calendar.MONTH) + 1;
                int displayYear = dateCal.get(Calendar.YEAR);
                String correctedMonth = "" + displayMonth;
                if (displayMonth < 10) {
                    correctedMonth = "0" + displayMonth;
                }
                Log.d("selected_date_1", mDate.toString());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                //check if its today
                if (sdf.format(mDate).equals(sdf.format(new Date()))) {
                    Log.d("selected_date_1", mDate + "==" + sdf.format(new Date()) + " The date is today");
                    ((BookingActivity) context).setDateSelected(dayValue + "/" + correctedMonth + "/" + displayYear);
                    Toast.makeText(context, "Clicked " + dayValue + "/" + displayMonth + "/" + displayYear, Toast.LENGTH_LONG).show();
                    ((BookingActivity) context).createFragments(new TimeFragment());
                } else {
                    //check if date is passed/future
                    Date current = new Date();
                    Long l = mDate.getTime();
                    //create date object
                    Date next = new Date(l);
                    //compare both dates
                    if (next.after(current)) {
                        //The date is future day
                        Log.d("selected_date_1", next + "==" + current + " The date is future day");
                        ((BookingActivity) context).setDateSelected(dayValue + "/" + correctedMonth + "/" + displayYear);
                        Toast.makeText(context, "Clicked " + dayValue + "/" + displayMonth + "/" + displayYear, Toast.LENGTH_LONG).show();
                        ((BookingActivity) context).createFragments(new TimeFragment());
                    } else {
                        //The date is older than current day
                        Log.d("selected_date_2", next + "==" + current + "The date is older than current day");
                    }
                }

            }
        });
    }

    private void setUpCalendarAdapter() {
       /* mQuery = new DatabaseQuery(context);
        List<EventObjects> mEvents = mQuery.getAllFutureEvents();*/
        Calendar mCal = (Calendar) cal.clone();
        mCal.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfTheMonth = mCal.get(Calendar.DAY_OF_WEEK) - 1;
        mCal.add(Calendar.DAY_OF_MONTH, -firstDayOfTheMonth);
        while (dayValueInCells.size() < MAX_CALENDAR_COLUMN) {
            dayValueInCells.add(mCal.getTime());
            mCal.add(Calendar.DAY_OF_MONTH, 1);
        }
        Log.d(TAG, "Number of date " + dayValueInCells.size());
        String sDate = formatter.format(cal.getTime());
        currentDate.setText(sDate);
        mAdapter = new CalendarGridAdapter(context, dayValueInCells, cal/*, mEvents*/);
        calendarGridView.setAdapter(mAdapter);
    }
}