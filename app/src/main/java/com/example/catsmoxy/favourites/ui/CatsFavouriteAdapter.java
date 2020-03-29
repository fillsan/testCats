package com.example.catsmoxy.favourites.ui;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catsmoxy.R;

import java.util.List;

public class CatsFavouriteAdapter extends RecyclerView.Adapter<CatsFavouriteAdapter.ViewHolder> {

    private List<Bitmap> catsImages;

    public CatsFavouriteAdapter(List<Bitmap> catsImages) {
        this.catsImages = catsImages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_favourite_viewholder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cat_image.setImageBitmap(catsImages.get(position));
    }

    @Override
    public int getItemCount() {
        return catsImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView cat_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cat_image = itemView.findViewById(R.id.cat_image);
        }
    }
}
