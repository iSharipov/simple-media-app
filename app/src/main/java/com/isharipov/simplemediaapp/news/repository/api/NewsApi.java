package com.isharipov.simplemediaapp.news.repository.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.isharipov.simplemediaapp.news.model.ArticleResponse;
import com.isharipov.simplemediaapp.news.model.source.SourceResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 13.06.2018.
 */
public interface NewsApi {
    @GET("/v2/top-headlines")
    Observable<ArticleResponse> getArticlesByCategory(@Query("country") @Nullable String country,
                                                      @Query("category") @Nullable String category,
                                                      @Query("q") @Nullable String query,
                                                      @Query("page") @NonNull Integer page,
                                                      @Query("apiKey") @NonNull String apiKey,
                                                      @Query("pageSize") @NonNull Integer pageSize);

    @GET("/v2/everything")
    Observable<ArticleResponse> getEverythingArticles(@Query("q") @Nullable String query,
                                                      @Query("sources") @NonNull String sources,
                                                      @Query("page") @NonNull Integer page,
                                                      @Query("apiKey") @NonNull String apiKey,
                                                      @Query("pageSize") @NonNull Integer pageSize);

    @GET("/v2/sources")
    Observable<SourceResponse> getSources(@Query("q") @Nullable String query,
                                          @Query("country") @NonNull String country,
                                          @Query("apiKey") @NonNull String apiKey);
}
