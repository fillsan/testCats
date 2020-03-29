package com.example.catsmoxy.favourites.ui;

import com.arellomobile.mvp.MvpView;
import com.example.catsmoxy.database.CatEntity;

import java.util.List;

public interface CatsFavouritesView extends MvpView {

    void showCatsFromDb(List<CatEntity> catsList);

    void showError(String error);
}
