package com.girlathome.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.girlathome.R;
import com.girlathome.activities.TimeFragment;

import java.util.List;

/**
 * Created by steve on 5/13/17.
 */

public class PMTimeAdapter
        extends BaseAdapter {

    String daytime_variant, hour_variant;
    private Activity mActivity;
    private List<String> mData;

    public PMTimeAdapter(Activity a, List<String> mData, String daytime_variant, String hour_variant) {
        mActivity = a;
        this.mData = mData;
        this.daytime_variant = daytime_variant;
        this.hour_variant = hour_variant;
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

//        disable previous hours if we're in the afternoon
        if (daytime_variant.equalsIgnoreCase("pm")) {
            if (Integer.valueOf(hour_variant) >= Integer.valueOf(mData.get(position))) {
//            means current time is past the current value
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
                            Log.d("is_it_time", "Clicked " + mData.get(position) + daytime_variant);
                            Toast.makeText(mActivity, "Clicked " + mData.get(position) + " " + daytime_variant, Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }

        }

        return convertView;
    }

    class ViewHolder {
        TextView text;
    }

}
