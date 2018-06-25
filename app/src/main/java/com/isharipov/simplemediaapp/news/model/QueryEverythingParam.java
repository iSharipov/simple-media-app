package com.isharipov.simplemediaapp.news.model;

import android.support.annotation.NonNull;

/**
 * 22.06.2018.
 */
public class QueryEverythingParam extends QueryParam {
    private String country;

    public QueryEverythingParam(@NonNull Integer page) {
        super(page);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(@NonNull String country) {
        this.country = country;
    }
}
