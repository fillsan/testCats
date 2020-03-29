package com.example.catsmoxy.list.di;

import com.example.catsmoxy.list.CatsListPresenter;

import dagger.Subcomponent;

@CatsListScope
@Subcomponent(modules = {CatsListModule.class})
public interface CatsListComponent {
    void inject(CatsListPresenter catsListPresenter);
}
