package com.isharipov.simplemediaapp.movie.ui.category;

import android.content.Context;

import com.isharipov.simplemediaapp.movie.model.Genre;
import com.isharipov.simplemediaapp.movie.model.GenreResponse;
import com.isharipov.simplemediaapp.movie.model.Movie;
import com.isharipov.simplemediaapp.movie.model.MovieResponse;
import com.isharipov.simplemediaapp.movie.repository.MovieRepository;
import com.isharipov.simplemediaapp.movie.util.QueryMovieParam;
import com.isharipov.simplemediaapp.movie.widget.UpcomingMovieUpdateService;
import com.isharipov.simplemediaapp.news.model.QueryParam;
import com.isharipov.simplemediaapp.news.ui.news.category.CategoryContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 06.07.2018.
 */
public class MovieCategoryPresenter implements CategoryContract.Presenter {

    private CategoryContract.View view;
    private final MovieRepository movieRepository;
    private final CompositeDisposable compositeDisposable;
    private Context context;

    @Inject
    MovieCategoryPresenter(MovieRepository movieRepository, CompositeDisposable compositeDisposable, Context context) {
        this.movieRepository = movieRepository;
        this.compositeDisposable = compositeDisposable;
        this.context = context;
    }

    @Override
    public void loadFromApi(QueryParam queryParam) {
        view.showProgress();
        QueryMovieParam queryMovieParam = (QueryMovieParam) queryParam;
        Observable<MovieResponse> moviesFromApi = movieRepository.getMoviesByCategoryFromApi(queryMovieParam);
        Observable<GenreResponse> genresFromApi = movieRepository.getGenresFromApi(queryMovieParam.getLanguage());
        Observable<MovieResponse> combined = Observable.zip(moviesFromApi, genresFromApi, (movieResponse, genreResponse) -> {
            List<Movie> movies = movieResponse.getResults();
            for (Movie movie : movies) {
                List<Long> genreIds = movie.getGenreIds();
                List<Genre> genres = genreResponse.getGenres();
                for (Long genreId : genreIds) {
                    for (Genre genre : genres) {
                        genre.setLanguage(queryMovieParam.getLanguage());
                        if (genreId.equals(genre.getId())) {
                            movie.getGenres().add(genre.getName());
                        }
                    }
                    movieRepository.storeGenresInDb(genres);
                }
            }
            movieRepository.storeMoviesInDb(movies);
            return movieResponse;
        });
        combined.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<MovieResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(MovieResponse movieResponse) {
                if (view != null) {
                    List<Movie> movies = movieResponse.getResults();
                    view.setData(movies);
                    view.hideProgress();
                    view.setMoreLoaded(false);
                    if (queryMovieParam.getCategory().equals("upcoming")){
                        UpcomingMovieUpdateService.startMovieUpcomingServiceService(context, new ArrayList<>(movies));
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                if (view != null) {
                    view.onItemsLoadComplete();
                    view.hideProgress();
                    view.setMoreLoaded(false);
                }
            }

            @Override
            public void onComplete() {
                if (view != null) {
                    view.onItemsLoadComplete();
                    view.hideProgress();
                    view.setMoreLoaded(false);
                }
            }
        });
    }

    @Override
    public void attachView(CategoryContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.dispose();
    }
}
