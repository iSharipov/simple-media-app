package com.isharipov.simplemediaapp.news.ui;

import java.util.List;

/**
 * 12.06.2018.
 */
public interface BaseView<P, T> {
    void setData(List<T> articles);
}
