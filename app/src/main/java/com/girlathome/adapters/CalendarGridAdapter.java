package com.girlathome.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.girlathome.R;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by steve on 5/10/17.
 */

public class CalendarGridAdapter extends ArrayAdapter {
    private static final String TAG = CalendarGridAdapter.class.getSimpleName();
    Calendar dateCal;
    private LayoutInflater mInflater;
    private List<Date> monthlyDates;
    private Calendar currentMonthView;
    private Calendar todayDate;

    //    private List<EventObjects> allEvents;
    public CalendarGridAdapter(Context context, List<Date> monthlyDates, Calendar currentMonthView, Calendar todayDate/*, List<EventObjects> allEvents*/) {
        super(context, R.layout.single_cell_layout);
        this.monthlyDates = monthlyDates;
        this.currentMonthView = currentMonthView;
        this.todayDate = todayDate;
//        this.allEvents = allEvents;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Date mDate = monthlyDates.get(position);
        dateCal = Calendar.getInstance();
        dateCal.setTime(mDate);
        int dayValue = dateCal.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCal.get(Calendar.MONTH) + 1;
        int displayYear = dateCal.get(Calendar.YEAR);
        int currentDay = currentMonthView.get(Calendar.DAY_OF_MONTH);
        int currentMonth = currentMonthView.get(Calendar.MONTH) + 1;
        int currentYear = currentMonthView.get(Calendar.YEAR);
        int todayDay = todayDate.get(Calendar.DAY_OF_MONTH);
        int todayMonth = todayDate.get(Calendar.MONTH) + 1;
        int todayYear = todayDate.get(Calendar.YEAR);
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(R.layout.single_cell_layout, parent, false);
        }

        //Add day to calendar
        TextView cellNumber = (TextView) view.findViewById(R.id.calendar_date_id);
        cellNumber.setText(String.valueOf(dayValue));
        if (displayMonth == currentMonth && displayYear == currentYear) {
            view.setBackgroundColor(Color.parseColor("#FFFFFF"));
            cellNumber.setTextColor(getContext().getResources().getColor(R.color.darkAccent));
        } else {
//            view.setBackgroundColor(getContext().getResources().getColor(R.color.grey_lighter));
            cellNumber.setTextColor(getContext().getResources().getColor(R.color.grey_2));
        }
        //highlight current day
        if (dayValue == todayDay && displayMonth == todayMonth && displayYear == todayYear) {
            view.setBackgroundColor(getContext().getResources().getColor(R.color.mauvre));
            cellNumber.setTextColor(getContext().getResources().getColor(R.color.white));
        }

        //Add events to the calendar
//        TextView eventIndicator = (TextView)view.findViewById(R.id.event_id);
//        Calendar eventCalendar = Calendar.getInstance();
//        for(int i = 0; i < allEvents.size(); i++){
//            eventCalendar.setTime(allEvents.get(i).getDate());
//            if(dayValue == eventCalendar.get(Calendar.DAY_OF_MONTH) && displayMonth == eventCalendar.get(Calendar.MONTH) + 1
//                    && displayYear == eventCalendar.get(Calendar.YEAR)){
//                eventIndicator.setBackgroundColor(Color.parseColor("#FF4081"));
//            }
//        }
        return view;
    }

    @Override
    public int getCount() {
        return monthlyDates.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return monthlyDates.get(position);
    }

    @Override
    public int getPosition(Object item) {
        return monthlyDates.indexOf(item);
    }

}
