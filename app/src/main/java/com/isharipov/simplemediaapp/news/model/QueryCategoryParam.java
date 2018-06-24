package com.isharipov.simplemediaapp.news.model;

import android.support.annotation.NonNull;

/**
 * 22.06.2018.
 */
public class QueryCategoryParam extends QueryParam {
    private final String country;
    private final String category;

    public QueryCategoryParam(@NonNull String country, @NonNull String category, @NonNull Integer page) {
        super(page);
        this.country = country;
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public String getCategory() {
        return category;
    }
}
