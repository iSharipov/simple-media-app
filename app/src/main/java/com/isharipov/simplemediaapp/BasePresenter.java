package com.isharipov.simplemediaapp;

/**
 * 12.06.2018.
 */
public interface BasePresenter<V> {

    void attachView(V v);

    void detachView();
}
