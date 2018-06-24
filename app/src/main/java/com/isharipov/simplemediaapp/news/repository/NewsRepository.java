package com.isharipov.simplemediaapp.news.repository;

import com.isharipov.simplemediaapp.news.model.Article;
import com.isharipov.simplemediaapp.news.model.ArticleResponse;
import com.isharipov.simplemediaapp.news.model.QueryCategoryParam;
import com.isharipov.simplemediaapp.news.model.QueryEverythingParam;
import com.isharipov.simplemediaapp.news.model.source.QuerySourceParam;
import com.isharipov.simplemediaapp.news.model.source.SourceResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * 13.06.2018.
 */
public interface NewsRepository {
    Observable<ArticleResponse> getArticlesByCategoryFromApi(QueryCategoryParam queryParam);

    Observable<ArticleResponse> getArticlesEverythingFromApi(QueryEverythingParam queryParam);

    Observable<ArticleResponse> getArticlesByCategoryFromDb(QueryCategoryParam queryParam);

    Observable<ArticleResponse> getArticlesEverythingFromDb();

    void storeArticlesInDb(List<Article> articles);

    Observable<SourceResponse> getSourcesFromApi(QuerySourceParam queryParam);
}
