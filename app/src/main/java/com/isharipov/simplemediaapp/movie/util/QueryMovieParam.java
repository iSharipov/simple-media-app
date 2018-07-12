package com.isharipov.simplemediaapp.movie.util;

import android.support.annotation.NonNull;

import com.isharipov.simplemediaapp.news.model.QueryCategoryParam;

/**
 * 12.07.2018.
 */
public class QueryMovieParam extends QueryCategoryParam {

    private final String language;

    public QueryMovieParam(@NonNull String country,
                           @NonNull String category,
                           @NonNull Integer page,
                           @NonNull String language) {
        super(country, category, page);
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
}
