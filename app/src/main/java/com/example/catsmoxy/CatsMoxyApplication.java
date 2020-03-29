package com.example.catsmoxy;

import androidx.multidex.MultiDexApplication;

public class CatsMoxyApplication extends MultiDexApplication {

    private static CatsMoxyApplication mInstance;

    private AppComponent appComponent;

    public static CatsMoxyApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        appComponent = DaggerAppComponent.builder()
            .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
