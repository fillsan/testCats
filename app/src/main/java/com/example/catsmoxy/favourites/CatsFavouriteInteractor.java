package com.example.catsmoxy.favourites;

import com.example.catsmoxy.database.CatEntity;

import java.util.List;

import io.reactivex.Observable;

public interface CatsFavouriteInteractor {

    Observable<List<CatEntity>> loadCatsFromDb();
}
