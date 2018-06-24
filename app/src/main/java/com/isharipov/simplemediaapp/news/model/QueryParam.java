package com.isharipov.simplemediaapp.news.model;

import android.support.annotation.NonNull;

/**
 * 13.06.2018.
 */
public class QueryParam {
    private final Integer page;
    private String query;
    private Integer pageSize;

    public QueryParam(@NonNull Integer page) {
        this.page = page;
    }

    public Integer getPage() {
        return page;
    }

    public void setQuery(@NonNull String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
