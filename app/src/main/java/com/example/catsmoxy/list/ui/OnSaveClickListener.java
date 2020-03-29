package com.example.catsmoxy.list.ui;

import android.graphics.Bitmap;

public interface OnSaveClickListener {
    void onSaveDiskClick(String name, Bitmap bitmap);

    void onSaveFavouriteClick(Bitmap image);
}
