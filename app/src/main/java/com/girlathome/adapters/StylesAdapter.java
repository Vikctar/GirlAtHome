package com.girlathome.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.girlathome.R;
import com.girlathome.activities.StyleDetails;
import com.girlathome.databaseHandlers.FavouritesDB;
import com.girlathome.models.ServiceModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by steve on 5/16/17.
 */
public class StylesAdapter extends RecyclerView.Adapter<StylesAdapter.ViewHolder> {

    private static final String TAG = StylesAdapter.class.getSimpleName();
    String layout_variant;
    FavouritesDB favouritesDB;
    private Context mContext;
    private List<ServiceModel> mData;
    private DisplayImageOptions options;

    /**
     * Change {@link List} type according to your needs
     */
    public StylesAdapter(Context context, List<ServiceModel> data, String layout_variant) {
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
        this.layout_variant = layout_variant;
        favouritesDB = new FavouritesDB(context);
    }


    /**
     * Change the null parameter to a layout resource {@code R.layout.example}
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (layout_variant.equals("home")) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.styles_home_row, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.styles_row, parent, false);
        }

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final StylesAdapter.ViewHolder holder, int position) {
        // include binding logic here
        final ServiceModel serviceModel = mData.get(position);
        holder.tvName.setText(serviceModel.getName());
        holder.tvStyleType.setText("Hair Style");

        holder.avatarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, StyleDetails.class);
                i.putExtra("serviceModel", serviceModel);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
            }
        });
        holder.favImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.favImageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_selected_wish));
                favouritesDB.addFavouriteStyle(serviceModel.getId(), "", "",
                        serviceModel.getName(), serviceModel.getPrice(), "", "", "", "");
            }
        });
       /* holder.wishListImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.wishListImageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_selected_wish));
            }
        });*/
//        setImageViewWithUrl(mContext.getString(R.string.base_url) + "products/images/" + stylistModel.get(), holder.avatarImageView);

    }

    private void setImageViewWithUrl(String imageUrl, ImageView avatarImageView) {
        com.nostra13.universalimageloader.core.ImageLoader.getInstance()
                .displayImage(imageUrl, avatarImageView, options, new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                    }
                }, new ImageLoadingProgressListener() {
                    @Override
                    public void onProgressUpdate(String imageUri, View view, int current, int total) {

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
        @BindView(R.id.fav_image_view)
        ImageView favImageView;
        @BindView(R.id.name)
        TextView tvName;
        @BindView(R.id.style_type)
        TextView tvStyleType;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
} 