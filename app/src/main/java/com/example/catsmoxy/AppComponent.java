package com.example.catsmoxy;

import com.example.catsmoxy.database.di.DatabaseModule;
import com.example.catsmoxy.favourites.di.CatsFavouriteComponent;
import com.example.catsmoxy.list.di.CatsListComponent;
import com.example.catsmoxy.network.di.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, DatabaseModule.class})
public interface AppComponent {

    CatsListComponent plusCatsListComponent();

    CatsFavouriteComponent plusCatsFavouriteComponent();

}
