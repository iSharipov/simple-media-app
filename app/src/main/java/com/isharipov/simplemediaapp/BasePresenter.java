package com.isharipov.simplemediaapp;

import com.isharipov.simplemediaapp.news.model.QueryParam;

/**
 * 12.06.2018.
 */
public interface BasePresenter<V> {

    void attachView(V v);

    void detachView();

    void unsubscribe();

    void loadArticlesFromApi(QueryParam queryParam);
}
