package com.example.catsmoxy.favourites;

import com.example.catsmoxy.database.AppDatabase;
import com.example.catsmoxy.database.CatEntity;

import java.util.List;

import io.reactivex.Observable;

public class CatsFavouriteModel implements CatsFavouriteInteractor {

    private AppDatabase mAppDatabase;

    @Override
    public Observable<List<CatEntity>> loadCatsFromDb() {
        return Observable.fromCallable(() -> mAppDatabase.getDao().getAll());
    }

    public CatsFavouriteModel(AppDatabase mAppDatabase) {
        this.mAppDatabase = mAppDatabase;
    }
}
