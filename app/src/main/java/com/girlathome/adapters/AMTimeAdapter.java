package com.girlathome.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.girlathome.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by steve on 5/11/17.
 */
public class AMTimeAdapter extends BaseAdapter {

    private String daytime_variant, hour_variant, dateSelected;
    private Activity mActivity;
    private List<String> mData;
    private ListAdapterListener mListener;

    public AMTimeAdapter(Activity a, List<String> mData, String daytime_variant, String hour_variant, String dateSelected, ListAdapterListener mListener) {
        mActivity = a;
        this.mData = mData;
        this.daytime_variant = daytime_variant;
        this.hour_variant = hour_variant;
        this.mListener = mListener;
        this.dateSelected = dateSelected;
    }

    public int getCount() {
        return mData.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mActivity.getLayoutInflater().inflate(R.layout.time_cell_row, null);
            holder = new ViewHolder();

            holder.text = (TextView) convertView.findViewById(R.id.time_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(mData.get(position));
        Log.d("selected_time_am", dateSelected + "=" + daytime_variant);
        //if its today
        if (dateSelected.equalsIgnoreCase(getDate())) {
            //check what time of day we are at
            if (daytime_variant.equalsIgnoreCase("am")) {
                if (Integer.valueOf(hour_variant) >= Integer.valueOf(mData.get(position))) {
                    //means current time is past the current value
                    holder.text.setTextColor(mActivity.getResources().getColor(R.color.grey_2));
                } else {
                    if (Integer.valueOf(mData.get(position)) == 12) {
                        holder.text.setTextColor(mActivity.getResources().getColor(R.color.grey_2));
                    } else {
                        holder.text.setTextColor(mActivity.getResources().getColor(R.color.darkAccent));
                        //set listener for enabled hours
                        holder.text.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mListener.onAMClick(position, mData.get(position), "AM");
//                            Toast.makeText(mActivity, "Clicked " + mData.get(position) + " " + daytime_variant, Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            } else if (daytime_variant.equalsIgnoreCase("pm")) {
                holder.text.setTextColor(mActivity.getResources().getColor(R.color.grey_2));
            }
        } else {
            holder.text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onAMClick(position, mData.get(position), "AM");
//                            Toast.makeText(mActivity, "Clicked " + mData.get(position) + " " + daytime_variant, Toast.LENGTH_LONG).show();
                }
            });
        }
        return convertView;
    }

    String getDate() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(now);
    }

    public interface ListAdapterListener { // create an interface
        void onAMClick(int position, String s, String daytime_variant); // create callback function
    }

    private class ViewHolder {
        TextView text;
    }
} 