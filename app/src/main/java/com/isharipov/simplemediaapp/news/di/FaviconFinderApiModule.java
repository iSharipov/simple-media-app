package com.isharipov.simplemediaapp.news.di;

import com.isharipov.simplemediaapp.news.repository.api.FaviconFinderApi;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 24.06.2018.
 */
@Module
public class FaviconFinderApiModule {
    private static final String BASE_URL = "https://besticon-demo.herokuapp.com";

    @Provides
    FaviconFinderApi provideFaviconFinderApiService(OkHttpClient client, GsonConverterFactory gson, RxJava2CallAdapterFactory rxAdapter) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(gson)
                .addCallAdapterFactory(rxAdapter)
                .build();
        return retrofit.create(FaviconFinderApi.class);
    }
}
