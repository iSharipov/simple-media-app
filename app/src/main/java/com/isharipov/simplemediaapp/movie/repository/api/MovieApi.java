package com.isharipov.simplemediaapp.movie.repository.api;

import com.isharipov.simplemediaapp.movie.model.Movie;
import com.isharipov.simplemediaapp.movie.model.MovieResponse;
import com.isharipov.simplemediaapp.movie.model.ReviewResponse;
import com.isharipov.simplemediaapp.movie.model.TrailerResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 04.07.2018.
 */
public interface MovieApi {
    String API_KEY = "api_key";
    String CATEGORY = "category";
    String ID = "id";
    String LANGUAGE = "language";

    @GET("/3/movie/{id}")
    Observable<Movie> getMovieById(@Path(ID) Long id, @Query(API_KEY) String apiKey);

    @GET("/3/movie/{category}")
    Observable<MovieResponse> getMoviesByCategory(
            @Path(CATEGORY) String category,
            @Query("language") String language,
            @Query(API_KEY) String apiKey);

    @GET("/3/movie/{id}/videos")
    Observable<TrailerResponse> getMovieTrailers(@Path(ID) String id, @Query(API_KEY) String apiKey);

    @GET("/3/movie/{id}/reviews")
    Observable<ReviewResponse> getMovieReviews(@Path(ID) String id, @Query(API_KEY) String apiKey);
}
