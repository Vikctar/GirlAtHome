package com.girlathome.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.girlathome.R;
import com.girlathome.models.BookingModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by steve on 6/2/17.
 */
public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.ViewHolder> {

    private static final String TAG = BookingsAdapter.class.getSimpleName();

    private Context mContext;
    private List<BookingModel> mData;
    private DisplayImageOptions options;

    /**
     * Change {@link List} type according to your needs
     */
    public BookingsAdapter(Context context, List<BookingModel> data) {
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
    }


    /**
     * Change the null parameter to a layout resource {@code R.layout.example}
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bookings_row, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // include binding logic here
        final BookingModel bookingModel = mData.get(position);
        Log.d("bookings_adapter", "--"+bookingModel.getName());
        //holder.tvName.setText(bookingModel.getDate());
        holder.tvName.setText(bookingModel.getName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // include {@link View} components here
        @BindView(R.id.avatar)
        ImageView avatarImageView;
        /*@BindView(R.id.wishlist_icon)
        ImageView wishListImageView;*/
        @BindView(R.id.name)
        TextView tvName;
        @BindView(R.id.time_left)
        TextView tvTimeLeft;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
} 