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
 * Created by steve on 5/13/17.
 */

public class PMTimeAdapter extends BaseAdapter {

    private String daytime_variant, hour_variant, dateSelected;
    private Activity mActivity;
    private List<String> mData;

    private ListAdapterListener mListener;


    public PMTimeAdapter(Activity a, List<String> mData, String daytime_variant, String hour_variant, String dateSelected, ListAdapterListener mListener) {
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
        PMTimeAdapter.ViewHolder holder;

        if (convertView == null) {
            convertView = mActivity.getLayoutInflater().inflate(R.layout.time_cell_row, null);
            holder = new PMTimeAdapter.ViewHolder();

            holder.text = (TextView) convertView.findViewById(R.id.time_text);
            convertView.setTag(holder);
        } else {
            holder = (PMTimeAdapter.ViewHolder) convertView.getTag();

        }
        holder.text.setText(mData.get(position));
        //style the time
        //if its today
        if (dateSelected.equalsIgnoreCase(getDate())) {
            Log.d("date_selected", "today");
            //disable previous hours if we're in the afternoon
            if (daytime_variant.equalsIgnoreCase("pm")) {
                Log.d("date_selected", "today" + daytime_variant);
                if (Integer.valueOf(mData.get(position)) <= Integer.valueOf(hour_variant)) { //disable previous dates
                    Log.d("date_selected_1", "this");
                    holder.text.setTextColor(mActivity.getResources().getColor(R.color.grey_2));

                } else {
                    if (Integer.valueOf(mData.get(position)) == 12) {
                        Log.d("date_selected_3", "== 12");
                        holder.text.setTextColor(mActivity.getResources().getColor(R.color.grey_2));
                    } else {
                        holder.text.setTextColor(mActivity.getResources().getColor(R.color.darkAccent));
                        holder.text.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mListener.onPMClick(position, mData.get(position), "pm");
                            }
                        });
                    }

                }


            } else {
                holder.text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.onPMClick(position, mData.get(position), "pm");
                    }
                });
            }
        } else {
            holder.text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onPMClick(position, mData.get(position), "pm");
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
        void onPMClick(int position, String s, String daytime_variant); // create callback function
    }

    class ViewHolder {
        TextView text;
    }

}
