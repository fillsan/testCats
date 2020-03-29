package com.example.catsmoxy;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    public Context provideContext() {
        return CatsMoxyApplication.getInstance();
    }
}
