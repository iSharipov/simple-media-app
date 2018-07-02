package com.isharipov.simplemediaapp.music.ui;

import com.isharipov.simplemediaapp.music.model.artist.Artist;
import com.isharipov.simplemediaapp.news.model.Article;

import java.util.List;

/**
 * 12.06.2018.
 */
public interface BaseView<P, D> {
    void setData(List<D> articles);
}
