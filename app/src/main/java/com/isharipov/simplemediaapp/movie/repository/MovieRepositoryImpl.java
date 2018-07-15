package com.isharipov.simplemediaapp.movie.repository;

import com.isharipov.simplemediaapp.movie.model.Genre;
import com.isharipov.simplemediaapp.movie.model.GenreResponse;
import com.isharipov.simplemediaapp.movie.model.Movie;
import com.isharipov.simplemediaapp.movie.model.MovieResponse;
import com.isharipov.simplemediaapp.movie.model.ReviewResponse;
import com.isharipov.simplemediaapp.movie.model.TrailerResponse;
import com.isharipov.simplemediaapp.movie.repository.api.MovieApi;
import com.isharipov.simplemediaapp.movie.repository.db.MovieDao;
import com.isharipov.simplemediaapp.movie.util.QueryMovieParam;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

import static com.isharipov.simplemediaapp.BuildConfig.THEMOVIE_DB_API_KEY;

/**
 * 08.07.2018.
 */
public class MovieRepositoryImpl implements MovieRepository {

    private final MovieApi movieApi;
    private final MovieDao movieDao;

    @Inject
    public MovieRepositoryImpl(MovieApi movieApi, MovieDao movieDao) {
        this.movieApi = movieApi;
        this.movieDao = movieDao;
    }

    @Override
    public Observable<MovieResponse> getMoviesByCategoryFromApi(QueryMovieParam queryParam) {
        return movieApi.getMoviesByCategory(
                queryParam.getCategory(),
                queryParam.getLanguage(),
                queryParam.getCountry(),
                THEMOVIE_DB_API_KEY);
    }

    @Override
    public Observable<GenreResponse> getGenresFromApi(String language) {
        return movieApi.getGenres(
                language,
                THEMOVIE_DB_API_KEY);
    }

    @Override
    public Observable<ReviewResponse> getReviewsFromApi(String movieId) {
        return movieApi
                .getMovieReviews(
                        movieId,
                        THEMOVIE_DB_API_KEY);
    }

    @Override
    public Observable<TrailerResponse> getTrailersFromApi(String movieId) {
        return movieApi
                .getMovieTrailers(
                        movieId,
                        THEMOVIE_DB_API_KEY);
    }

    @Override
    public void storeMoviesInDb(List<Movie> movies) {
        Completable.fromAction(() -> movieDao.insertMovies(movies))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                Timber.d("Inserted movies from API to DB...");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void storeGenresInDb(List<Genre> genres) {
        Completable.fromAction(() -> movieDao.insertGenres(genres))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                Timber.d("Inserted genres from API to DB...");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
