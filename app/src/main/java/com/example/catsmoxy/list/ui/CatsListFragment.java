package com.example.catsmoxy.list.ui;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.catsmoxy.CatsListBaseFragment;
import com.example.catsmoxy.database.CatEntity;
import com.example.catsmoxy.list.CatDTO;
import com.example.catsmoxy.list.CatsListPresenter;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class CatsListFragment extends CatsListBaseFragment implements CatsListView, OnSaveClickListener {

    @InjectPresenter
    CatsListPresenter catsListPresenter;

    private List<CatDTO> cats = new ArrayList<>();

    @Override
    public void initializeAdapter() {
        mAdapter = new CatsListAdapter(cats, this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int lastCompletelyVisibleItemPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                    int lastVisibleItemPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findLastVisibleItemPosition();
                    if (lastCompletelyVisibleItemPosition == lastVisibleItemPosition) {
                        catsListPresenter.loadNextCats();
                    }
                }
            }
        });
        return view;
    }

    @Override
    public void showCats(List<CatDTO> catList) {
        cats.addAll(catList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadingCatsFailed(String errorMessage) {
        showRetrySnackBar(errorMessage);
    }

    @Override
    public void savingCatsSuccess(String message) {
        showSnackBar(message);
    }

    @Override
    public void savingCatsFailed(String errorMessage) {
        showSnackBar(errorMessage);
    }

    @Override
    public void onSaveDiskClick(String name, Bitmap bitmap) {
        saveImageToDownloads(bitmap, name);
    }

    @Override
    public void onSaveFavouriteClick(Bitmap image) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        catsListPresenter.saveCatInDb(new CatEntity(stream.toByteArray()));
    }

    private void saveImageToDownloads(Bitmap bitmap, @NonNull String name) {
        OutputStream outputStream;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ContentResolver resolver = getActivity().getContentResolver();
                ContentValues contentValues = new ContentValues();
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name + ".jpg");
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg");
                contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS);
                Uri imageUri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues);
                outputStream = resolver.openOutputStream(imageUri);
            } else {
                String imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
                File image = new File(imagesDir, name + ".jpg");
                outputStream = new FileOutputStream(image);
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            if (outputStream != null) {
                outputStream.close();
            }
            showSnackBar("Image saved successfully");
        } catch (Exception e) {
            showSnackBar("Can't save image to Downloads");
        }
    }

    private void showRetrySnackBar(String message) {
        Snackbar.make(rootLayout, message, BaseTransientBottomBar.LENGTH_INDEFINITE)
            .setAction("RETRY", v -> catsListPresenter.loadCats())
            .setActionTextColor(Color.YELLOW)
            .show();
    }
}
