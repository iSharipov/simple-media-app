package com.isharipov.simplemediaapp.movie.repository;

import com.isharipov.simplemediaapp.movie.model.Genre;
import com.isharipov.simplemediaapp.movie.model.GenreResponse;
import com.isharipov.simplemediaapp.movie.model.Movie;
import com.isharipov.simplemediaapp.movie.model.MovieResponse;
import com.isharipov.simplemediaapp.movie.util.QueryMovieParam;

import java.util.List;

import io.reactivex.Observable;

/**
 * 06.07.2018.
 */
public interface MovieRepository {
    Observable<MovieResponse> getMoviesByCategoryFromApi(QueryMovieParam queryParam);

    Observable<GenreResponse> getGenresFromApi(String language);

    void storeMoviesInDb(List<Movie> movies);

    void storeGenresInDb(List<Genre> genres);
}
