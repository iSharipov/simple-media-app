package com.isharipov.simplemediaapp.music.ui;

import io.reactivex.disposables.CompositeDisposable;

/**
 * 01.07.2018.
 */
public abstract class MusicPresenter implements MusicContract.Presenter {

    protected MusicContract.View view;
    protected final CompositeDisposable compositeDisposable;

    public MusicPresenter(CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void attachView(MusicContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }
}
