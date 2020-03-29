package com.example.catsmoxy.list;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.catsmoxy.CatsMoxyApplication;
import com.example.catsmoxy.database.CatEntity;
import com.example.catsmoxy.list.ui.CatsListView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class CatsListPresenter extends MvpPresenter<CatsListView> {

    @Inject
    CatsListInteractor catsListInteractor;

    private int currentPage = 1;

    private boolean isLoading = false;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadCats();
    }

    public void loadCats() {
        isLoading = true;
        compositeDisposable.add(
            catsListInteractor.loadCats(currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEach(it -> isLoading = false)
                .subscribe(
                    cats -> getViewState().showCats(cats),
                    throwable -> getViewState().loadingCatsFailed("Can't load cats"))
        );
    }

    public void loadNextCats() {
        if (!isLoading) {
            currentPage++;
            loadCats();
        }
    }

    public CatsListPresenter() {
        CatsMoxyApplication.getInstance().getAppComponent().plusCatsListComponent().inject(this);
    }

    public void saveCatInDb(CatEntity catEntity) {
        compositeDisposable.add(
            catsListInteractor.saveCatToDb(catEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    () -> getViewState().savingCatsSuccess("Cat saved in favourites"),
                    throwable -> getViewState().savingCatsSuccess("Can't save in favourites"))
        );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
