package com.example.catsmoxy.favourites.di;

import com.example.catsmoxy.database.AppDatabase;
import com.example.catsmoxy.favourites.CatsFavouriteModel;
import com.example.catsmoxy.favourites.CatsFavouriteInteractor;

import dagger.Module;
import dagger.Provides;

@Module
public class CatsFavouriteModule {

    @Provides
    @CatsFavouriteScope
    CatsFavouriteInteractor provideCatsFavouriteModelView(AppDatabase appDatabase) {
        return new CatsFavouriteModel(appDatabase);
    }
}
