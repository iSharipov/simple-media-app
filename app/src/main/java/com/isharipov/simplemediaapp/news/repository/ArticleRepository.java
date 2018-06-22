package com.isharipov.simplemediaapp.news.repository;

import com.isharipov.simplemediaapp.news.model.Article;
import com.isharipov.simplemediaapp.news.model.ArticleResponse;
import com.isharipov.simplemediaapp.news.model.QueryParam;

import java.util.List;

import io.reactivex.Observable;

/**
 * 13.06.2018.
 */
public interface ArticleRepository {
    Observable<ArticleResponse> getArticlesFromApi(QueryParam queryParam);

    Observable<ArticleResponse> getArticlesFromDb(QueryParam queryParam);

    void storeArticlesInDb(List<Article> articles);
}
