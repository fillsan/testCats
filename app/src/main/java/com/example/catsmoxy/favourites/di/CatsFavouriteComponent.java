package com.example.catsmoxy.favourites.di;

import com.example.catsmoxy.favourites.CatsFavouritePresenter;

import dagger.Subcomponent;

@CatsFavouriteScope
@Subcomponent(modules = {CatsFavouriteModule.class})
public interface CatsFavouriteComponent {
    void inject(CatsFavouritePresenter catsFavouritePresenter);
}
