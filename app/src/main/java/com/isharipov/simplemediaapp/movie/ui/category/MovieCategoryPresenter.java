package com.isharipov.simplemediaapp.movie.ui.category;

import com.isharipov.simplemediaapp.movie.model.Movie;
import com.isharipov.simplemediaapp.movie.model.MovieResponse;
import com.isharipov.simplemediaapp.movie.repository.MovieRepository;
import com.isharipov.simplemediaapp.news.model.Article;
import com.isharipov.simplemediaapp.news.model.ArticleResponse;
import com.isharipov.simplemediaapp.news.model.QueryCategoryParam;
import com.isharipov.simplemediaapp.news.model.QueryParam;
import com.isharipov.simplemediaapp.news.ui.news.category.CategoryContract;

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

    @Inject
    MovieCategoryPresenter(MovieRepository movieRepository, CompositeDisposable compositeDisposable) {
        this.movieRepository = movieRepository;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void loadFromApi(QueryParam queryParam) {
        view.showProgress();
        QueryCategoryParam queryCategoryParam = (QueryCategoryParam) queryParam;
        Observable<MovieResponse> moviesFromApi = movieRepository.getMoviesByCategoryFromApi(queryCategoryParam);
        moviesFromApi.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(MovieResponse movieResponse) {
                        List<Movie> movies = movieResponse.getResults();
                        view.setData(movies);
                        view.hideProgress();
                        view.setMoreLoaded(false);
//                        for (Article article : articles) {
//                            article.setCategory(queryCategoryParam.getCategory());
//                        }
//                        movieRepository.storeArticlesInDb(articles);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onItemsLoadComplete();
                        view.hideProgress();
                        view.setMoreLoaded(false);
                    }

                    @Override
                    public void onComplete() {
                        view.onItemsLoadComplete();
                        view.hideProgress();
                        view.setMoreLoaded(false);
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
