package com.example.catsmoxy.favourites.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.catsmoxy.database.CatEntity;
import com.example.catsmoxy.CatsListBaseFragment;
import com.example.catsmoxy.favourites.CatsFavouritePresenter;

import java.util.ArrayList;
import java.util.List;

public class CatsFavouriteFragment extends CatsListBaseFragment implements CatsFavouritesView {

    @InjectPresenter
    CatsFavouritePresenter catsFavouritePresenter;

    private List<Bitmap> catsImages = new ArrayList<>();

    @Override
    public void initializeAdapter() {
        mAdapter = new CatsFavouriteAdapter(catsImages);
    }

    public void loadCatsFromDb() {
        catsFavouritePresenter.loadCatsFromDb();
    }

    @Override
    public void showCatsFromDb(List<CatEntity> catsList) {
        catsImages.clear();
        List<Bitmap> tmp = new ArrayList<>();
        for (CatEntity catEntity : catsList) {
            tmp.add(BitmapFactory.decodeByteArray(catEntity.getImage(), 0, catEntity.getImage().length));
        }
        catsImages.addAll(tmp);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        showSnackBar(error);
    }
}
