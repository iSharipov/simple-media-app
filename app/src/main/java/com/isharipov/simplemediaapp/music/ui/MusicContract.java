package com.isharipov.simplemediaapp.music.ui;

import com.isharipov.simplemediaapp.ui.BasePresenter;
import com.isharipov.simplemediaapp.news.model.QueryParam;

/**
 * 01.07.2018.
 */
public interface MusicContract {
    interface View<D> extends BaseView<Presenter, D> {

        void showContent();

        void showProgress();

        void hideProgress();

        void onItemsLoadComplete();
    }

    interface Presenter extends BasePresenter<MusicContract.View> {
        void loadFromApi(QueryParam queryParam);
    }
}
