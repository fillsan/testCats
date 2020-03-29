package com.example.catsmoxy.list.ui;

import com.arellomobile.mvp.MvpView;
import com.example.catsmoxy.list.CatDTO;

import java.util.List;

public interface CatsListView extends MvpView {

    void showCats(List<CatDTO> catList);

    void loadingCatsFailed(String errorMessage);

    void savingCatsSuccess(String message);

    void savingCatsFailed(String errorMessage);
}
