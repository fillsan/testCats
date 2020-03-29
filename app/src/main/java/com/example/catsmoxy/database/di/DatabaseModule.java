package com.example.catsmoxy.database.di;

import android.content.Context;

import com.example.catsmoxy.database.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(Context context) {
        return AppDatabase.getDatabaseInstance(context);
    }
}
