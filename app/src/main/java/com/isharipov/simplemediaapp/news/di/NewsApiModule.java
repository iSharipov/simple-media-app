package com.isharipov.simplemediaapp.news.di;


import com.isharipov.simplemediaapp.news.repository.api.NewsApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NewsApiModule {
    private static final String BASE_URL = "https://newsapi.org";

    @Provides
    @Singleton
    NewsApi provideNewsApiService(OkHttpClient client, GsonConverterFactory gson, RxJava2CallAdapterFactory rxAdapter) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(gson)
                .addCallAdapterFactory(rxAdapter)
                .build();
        return retrofit.create(NewsApi.class);
    }
}