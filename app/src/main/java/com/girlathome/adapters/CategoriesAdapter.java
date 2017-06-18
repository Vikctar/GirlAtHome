package com.girlathome.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.girlathome.R;
import com.girlathome.models.CategoriesModel;
import com.pkmmte.view.CircularImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by steve on 5/16/17.
 */
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private static final String TAG = CategoriesAdapter.class.getSimpleName();

    private Context mContext;
    private List<CategoriesModel> mData;

    /**
     * Change {@link List} type according to your needs
     */
    public CategoriesAdapter(Context context, List<CategoriesModel> data) {
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
                .inflate(R.layout.categories_row, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // include binding logic here
        final CategoriesModel categoriesModel = mData.get(position);
        holder.tvName.setText(categoriesModel.getName());
        holder.avatarImageView.setImageResource(categoriesModel.getimageId());
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // include {@link View} components here
        @BindView(R.id.avatar)
        CircularImageView avatarImageView;
        /*@BindView(R.id.wishlist_icon)
        ImageView wishListImageView;*/
        @BindView(R.id.name)
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
} 