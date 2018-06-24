package com.isharipov.simplemediaapp.news.repository.api;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 24.06.2018.
 */
public interface FaviconFinderApi {

    @GET("/icon")
    Observable<ResponseBody> getIcon(@Query("url") @NonNull String url,
                                     @Query("size") @NonNull String size);
}
