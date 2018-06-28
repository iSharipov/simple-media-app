package com.isharipov.simplemediaapp.news.ui.news.everything;

import com.isharipov.simplemediaapp.BasePresenter;
import com.isharipov.simplemediaapp.BaseView;
import com.isharipov.simplemediaapp.news.model.QueryParam;

/**
 * 22.06.2018.
 */
public interface EverythingContract {
    interface View extends BaseView<EverythingContract.Presenter> {

        void showContent();

        void showProgress();

        void hideProgress();

        void onItemsLoadComplete();

        void setMoreLoaded(boolean moreLoaded);
    }

    interface Presenter extends BasePresenter<EverythingContract.View> {
        void loadSources(QueryParam queryParam);
    }
}
