package com.isharipov.simplemediaapp.movie.di;

import com.isharipov.simplemediaapp.movie.repository.api.MovieApi;

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
public class MovieApiModule {
    public static final String BASE_URL = "http://api.themoviedb.org/";

    @Provides
    @Singleton
    MovieApi provideMovieApiService(OkHttpClient client, GsonConverterFactory gson, RxJava2CallAdapterFactory rxAdapter) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(gson)
                .addCallAdapterFactory(rxAdapter)
                .build();
        return retrofit.create(MovieApi.class);
    }
}
