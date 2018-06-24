package com.isharipov.simplemediaapp;

import com.isharipov.simplemediaapp.news.model.Article;

import java.util.List;

/**
 * 12.06.2018.
 */
public interface BaseView<P> {
    void setData(List<Article> articles);
}
