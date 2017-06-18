package com.girlathome.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.girlathome.R;
import com.girlathome.activities.BookingActivityDetails;
import com.girlathome.models.BookingModel;
import com.girlathome.utilities.TimeTask;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by steve on 6/2/17.
 */
public class UpcomingAppointmentsAdapter extends RecyclerView.Adapter<UpcomingAppointmentsAdapter.ViewHolder> {

    private static final String TAG = UpcomingAppointmentsAdapter.class.getSimpleName();
    TimeTask timeTask;
    private Context mContext;
    private List<BookingModel> mData;

    private DisplayImageOptions options;

    /**
     * Change {@link List} type according to your needs
     */
    public UpcomingAppointmentsAdapter(Context context, List<BookingModel> data) {
        if (context == null) {
            throw new NullPointerException("context can not be NULL");
        }

        if (data == null) {
            throw new NullPointerException("data list can not be NULL");
        }

        this.mContext = context;
        this.mData = data;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.image_placeholder)
                .showImageForEmptyUri(R.drawable.image_placeholder)
                .showImageOnFail(R.drawable.image_placeholder)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        timeTask = new TimeTask();
    }


    /**
     * Change the null parameter to a layout resource {@code R.layout.example}
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bookings_row, parent, false);
        view.getLayoutParams().width = parent.getWidth();//without this line, some shit happens i cant even explain

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // include binding logic here
        final BookingModel bookingModel = mData.get(position);
        Log.d("bookings_adapter", "--" + bookingModel.getName());
        //holder.tvName.setText(bookingModel.getDate());
//        holder.tvTimeLeft.setText(bookingModel.getDateTime());
        holder.tvTimeLeft.setText(timeTask.dateTimeDifference(bookingModel.getDateTime()));
//        dateTimeDifference(bookingModel.getDateTime(), holder.tvTimeLeft);
        holder.tvName.setText(bookingModel.getName());
        holder.tvPrice.setText(bookingModel.getDateTime());
        holder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, BookingActivityDetails.class);
//                i.putExtra("stylistModel", stylistModel);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // include {@link View} components here
        @BindView(R.id.avatar)
        ImageView avatarImageView;
        @BindView(R.id.name)
        TextView tvName;
        @BindView(R.id.time_left)
        TextView tvTimeLeft;
        @BindView(R.id.price)
        TextView tvPrice;
        @BindView(R.id.row_layout)
        RelativeLayout rowLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
} 