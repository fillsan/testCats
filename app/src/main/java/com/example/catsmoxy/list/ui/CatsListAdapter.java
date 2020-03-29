package com.example.catsmoxy.list.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.catsmoxy.GlideApp;
import com.example.catsmoxy.R;
import com.example.catsmoxy.list.CatDTO;

import java.util.List;

public class CatsListAdapter extends RecyclerView.Adapter<CatsListAdapter.ViewHolder> {

    private List<CatDTO> cats;
    private Context mContext;
    private OnSaveClickListener mOnSaveClickListener;

    public CatsListAdapter(List<CatDTO> cats, OnSaveClickListener onSaveClickListener) {
        this.cats = cats;
        mOnSaveClickListener = onSaveClickListener;
    }

    @NonNull
    @Override
    public CatsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.cat_viewholder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CatsListAdapter.ViewHolder holder, final int position) {
        holder.butons_layout.setVisibility(View.INVISIBLE);
        GlideApp.with(mContext)
            .asBitmap()
            .load(cats.get(position).getUrl())
            .into(new BitmapImageViewTarget(holder.cat_image) {
                @Override
                public void onResourceReady(Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                    super.onResourceReady(bitmap, transition);
                    holder.butons_layout.setVisibility(View.VISIBLE);
                }
            });

        holder.mSaveToDisk.setOnClickListener(v -> {
            Bitmap bitmap = ((BitmapDrawable) holder.cat_image.getDrawable()).getBitmap();
            mOnSaveClickListener.onSaveDiskClick(cats.get(position).getId(), bitmap);
        });

        holder.mSaveToDB.setOnClickListener(v -> {
            Bitmap bitmap = ((BitmapDrawable) holder.cat_image.getDrawable()).getBitmap();
            mOnSaveClickListener.onSaveFavouriteClick(bitmap);
        });
    }

    @Override
    public int getItemCount() {
        return cats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView cat_image;
        private LinearLayout butons_layout;
        private Button mSaveToDisk;
        private Button mSaveToDB;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cat_image = itemView.findViewById(R.id.cat_image);
            butons_layout = itemView.findViewById(R.id.buttons_layout);
            mSaveToDisk = itemView.findViewById(R.id.save_to_disc);
            mSaveToDB = itemView.findViewById(R.id.save_do_db);
        }
    }
}
