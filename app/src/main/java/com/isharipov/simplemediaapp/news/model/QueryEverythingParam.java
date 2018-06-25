package com.isharipov.simplemediaapp.news.model;

import android.support.annotation.NonNull;

/**
 * 22.06.2018.
 */
public class QueryEverythingParam extends QueryParam {
    private final String country;

    public QueryEverythingParam(@NonNull String country, @NonNull Integer page) {
        super(page);
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
