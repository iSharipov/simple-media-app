package com.isharipov.simplemediaapp.music.di;

import com.isharipov.simplemediaapp.music.repository.api.MusicApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 29.06.2018.
 */
@Module
public class MusicApiModule {
    private static final String BASE_URL = "http://ws.audioscrobbler.com/";

    @Provides
    @Singleton
    MusicApi provideMusicApiService(OkHttpClient client, GsonConverterFactory gson, RxJava2CallAdapterFactory rxAdapter) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(gson)
                .addCallAdapterFactory(rxAdapter)
                .build();
        return retrofit.create(MusicApi.class);
    }
}
