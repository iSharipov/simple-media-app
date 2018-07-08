package com.isharipov.simplemediaapp.news.ui.news.category;

import com.isharipov.simplemediaapp.BasePresenter;
import com.isharipov.simplemediaapp.news.model.QueryParam;
import com.isharipov.simplemediaapp.news.ui.BaseView;

/**
 * 12.06.2018.
 */
public interface CategoryContract {

    interface View extends BaseView<CategoryContract.Presenter> {

        void showProgress();

        void hideProgress();


        void onItemsLoadComplete();

        void setMoreLoaded(boolean moreLoaded);
    }

    interface Presenter extends BasePresenter<CategoryContract.View> {
        void loadFromApi(QueryParam queryParam);
    }
}
