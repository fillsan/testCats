package com.example.catsmoxy.list;

import com.example.catsmoxy.database.AppDatabase;
import com.example.catsmoxy.database.CatEntity;
import com.example.catsmoxy.network.CatsApiService;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class CatsListModel implements CatsListInteractor {

    private final int DEFAULT_CATS_LIMIT = 10;

    private CatsApiService apiService;

    private AppDatabase mAppDatabase;

    public CatsListModel(CatsApiService apiService, AppDatabase appDatabase) {
        this.apiService = apiService;
        mAppDatabase = appDatabase;
    }

    @Override
    public Observable<List<CatDTO>> loadCats(int currentPage) {
        return apiService.loadCats(DEFAULT_CATS_LIMIT, currentPage);
    }

    @Override
    public Completable saveCatToDb(CatEntity catEntity) {
        return Completable.fromAction(() -> mAppDatabase.getDao().insert(catEntity));
    }
}
