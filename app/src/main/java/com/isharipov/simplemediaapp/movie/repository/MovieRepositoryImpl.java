package com.isharipov.simplemediaapp.movie.repository;

import com.isharipov.simplemediaapp.BuildConfig;
import com.isharipov.simplemediaapp.movie.model.MovieResponse;
import com.isharipov.simplemediaapp.movie.repository.api.MovieApi;
import com.isharipov.simplemediaapp.news.model.QueryCategoryParam;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * 08.07.2018.
 */
public class MovieRepositoryImpl implements MovieRepository {

    private final MovieApi movieApi;

    @Inject
    public MovieRepositoryImpl(MovieApi movieApi) {
        this.movieApi = movieApi;
    }

    @Override
    public Observable<MovieResponse> getMoviesByCategoryFromApi(QueryCategoryParam queryParam) {
        return movieApi.getMoviesByCategory(
                queryParam.getCategory(),
                queryParam.getCountry(),
                BuildConfig.THEMOVIE_DB_API_KEY);
    }
}
