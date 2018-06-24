package com.isharipov.simplemediaapp.news.model.source;

import android.support.annotation.NonNull;

import com.isharipov.simplemediaapp.news.model.QueryParam;

/**
 * 24.06.2018.
 */
public class QuerySourceParam extends QueryParam {

    private final String country;

    public QuerySourceParam(@NonNull String country, @NonNull Integer page) {
        super(page);
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
