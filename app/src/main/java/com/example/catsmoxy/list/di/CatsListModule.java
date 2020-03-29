package com.example.catsmoxy.list.di;

import com.example.catsmoxy.database.AppDatabase;
import com.example.catsmoxy.list.CatsListModel;
import com.example.catsmoxy.list.CatsListInteractor;
import com.example.catsmoxy.network.CatsApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class CatsListModule {

    @Provides
    @CatsListScope
    CatsListInteractor provideModel(CatsApiService apiService, AppDatabase appDatabase) {
        return new CatsListModel(apiService, appDatabase);
    }
}
