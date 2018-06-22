package com.isharipov.simplemediaapp.news.repository.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.isharipov.simplemediaapp.news.model.ArticleResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 13.06.2018.
 */
public interface ArticleApi {
    @GET("/v2/top-headlines")
    Observable<ArticleResponse> getArticles(@Query("country") @Nullable String country,
                                            @Query("category") @Nullable String category,
                                            @Query("q") @Nullable String query,
                                            @Query("page") @NonNull Integer page,
                                            @Query("apiKey") @NonNull String apiKey,
                                            @Query("pageSize") @NonNull String pageSize);
}
