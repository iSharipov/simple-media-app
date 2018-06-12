package com.isharipov.simplemediaapp.news.ui;

import javax.inject.Inject;

/**
 * 12.06.2018.
 */
public class NewsPresenter implements NewsContract.Presenter {

    private NewsContract.View view;

    @Inject
    NewsPresenter() {

    }

    @Override
    public void attachView(NewsContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }
}
