package com.example.catsmoxy.list;

import com.example.catsmoxy.database.CatEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface CatsListInteractor {

    Observable<List<CatDTO>> loadCats(int currentPage);

    Completable saveCatToDb(CatEntity catEntity);
}
