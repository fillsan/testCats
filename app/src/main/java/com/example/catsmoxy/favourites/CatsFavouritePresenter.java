package com.example.catsmoxy.favourites;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.catsmoxy.CatsMoxyApplication;
import com.example.catsmoxy.favourites.ui.CatsFavouritesView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class CatsFavouritePresenter extends MvpPresenter<CatsFavouritesView> {

    @Inject
    CatsFavouriteInteractor catsFavouriteInteractor;

    private Disposable mDisposable;

    public CatsFavouritePresenter() {
        CatsMoxyApplication.getInstance().getAppComponent().plusCatsFavouriteComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadCatsFromDb();
    }

    public void loadCatsFromDb() {
        mDisposable = catsFavouriteInteractor
            .loadCatsFromDb()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                catEntities -> getViewState().showCatsFromDb(catEntities),
                throwable -> getViewState().showError("Can't load favourite cats"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
