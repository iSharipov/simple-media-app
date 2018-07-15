package com.isharipov.simplemediaapp.movie.repository;

import com.isharipov.simplemediaapp.movie.model.Genre;
import com.isharipov.simplemediaapp.movie.model.GenreResponse;
import com.isharipov.simplemediaapp.movie.model.Movie;
import com.isharipov.simplemediaapp.movie.model.MovieResponse;
import com.isharipov.simplemediaapp.movie.model.ReviewResponse;
import com.isharipov.simplemediaapp.movie.model.TrailerResponse;
import com.isharipov.simplemediaapp.movie.util.QueryMovieParam;

import java.util.List;

import io.reactivex.Observable;

/**
 * 06.07.2018.
 */
public interface MovieRepository {
    Observable<MovieResponse> getMoviesByCategoryFromApi(QueryMovieParam queryParam);

    Observable<GenreResponse> getGenresFromApi(String language);

    Observable<ReviewResponse> getReviewsFromApi(String movieId);

    Observable<TrailerResponse> getTrailersFromApi(String movieId);

    void storeMoviesInDb(List<Movie> movies);

    void storeGenresInDb(List<Genre> genres);
}
