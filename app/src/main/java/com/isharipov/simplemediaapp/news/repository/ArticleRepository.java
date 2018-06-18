package com.isharipov.simplemediaapp.news.repository;

import com.isharipov.simplemediaapp.news.model.ArticleResponse;
import com.isharipov.simplemediaapp.news.model.QueryParam;

import io.reactivex.Observable;

/**
 * 13.06.2018.
 */
public interface ArticleRepository {
    Observable<ArticleResponse> getArticleResponse(QueryParam queryParam);
}
