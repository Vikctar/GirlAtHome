package com.girlathome.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.girlathome.R;
import com.girlathome.activities.FavouritesList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by steve on 6/12/17.
 */
public class FavouriteCategories extends RecyclerView.Adapter<FavouriteCategories.ViewHolder> {

    private static final String TAG = FavouriteCategories.class.getSimpleName();

    private Context mContext;
    private List<String> mData;

    /**
     * Change {@link List} type according to your needs
     */
    public FavouriteCategories(Context context, List<String> data) {
        if (context == null) {
            throw new NullPointerException("context can not be NULL");
        }

        if (data == null) {
            throw new NullPointerException("data list can not be NULL");
        }

        this.mContext = context;
        this.mData = data;
    }


    /**
     * Change the null parameter to a layout resource {@code R.layout.example}
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favourites_categorized_row, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // include binding logic here
        holder.tvCount.setText(mData.get(position));
        holder.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, FavouritesList.class);
                i.putExtra("category_variant", "styles");
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
        @BindView(R.id.count_of_favs)
        TextView tvCount;
        @BindView(R.id.avatar)
        ImageView avatar;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
} 