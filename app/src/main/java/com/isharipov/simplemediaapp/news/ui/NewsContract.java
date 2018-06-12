package com.isharipov.simplemediaapp.news.ui;

import com.isharipov.simplemediaapp.BasePresenter;
import com.isharipov.simplemediaapp.BaseView;

/**
 * 12.06.2018.
 */
public interface NewsContract {
    interface View extends BaseView<NewsContract.Presenter> {

    }

    interface Presenter extends BasePresenter<NewsContract.View> {

    }
}
