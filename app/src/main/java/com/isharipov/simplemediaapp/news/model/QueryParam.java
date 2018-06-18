package com.isharipov.simplemediaapp.news.model;

import android.support.annotation.NonNull;

/**
 * 13.06.2018.
 */
public class QueryParam {
    private final String country;
    private final String category;
    private String query;
    private final Integer page;

    public QueryParam(@NonNull String country, @NonNull String category, @NonNull Integer page) {
        this.country = country;
        this.category = category;
        this.page = page;
    }

    public void withQuery(@NonNull String query) {
        this.query = query;
    }

    public String getCountry() {
        return country;
    }

    public String getCategory() {
        return category;
    }

    public String getQuery() {
        return query;
    }

    public Integer getPage() {
        return page;
    }
}
