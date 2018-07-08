package com.isharipov.simplemediaapp.movie.repository;

import com.isharipov.simplemediaapp.movie.model.MovieResponse;
import com.isharipov.simplemediaapp.news.model.QueryCategoryParam;

import io.reactivex.Observable;

/**
 * 06.07.2018.
 */
public interface MovieRepository {
    Observable<MovieResponse> getMoviesByCategoryFromApi(QueryCategoryParam queryParam);
}
