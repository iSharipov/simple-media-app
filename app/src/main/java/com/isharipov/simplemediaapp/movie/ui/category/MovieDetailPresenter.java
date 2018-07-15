package com.isharipov.simplemediaapp.movie.ui.category;

import com.isharipov.simplemediaapp.movie.model.Review;
import com.isharipov.simplemediaapp.movie.model.ReviewResponse;
import com.isharipov.simplemediaapp.movie.model.Trailer;
import com.isharipov.simplemediaapp.movie.model.TrailerResponse;
import com.isharipov.simplemediaapp.movie.repository.MovieRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 15.07.2018.
 */
public class MovieDetailPresenter implements MovieDetailContract.Presenter {

    private MovieDetailContract.View view;
    private final MovieRepository repository;
    private final CompositeDisposable compositeDisposable;

    @Inject
    MovieDetailPresenter(MovieRepository repository, CompositeDisposable compositeDisposable) {
        this.repository = repository;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void loadReviewsFromApi(String movieId) {
        view.showProgress();
        Observable<ReviewResponse> reviewResponse = repository.getReviewsFromApi(movieId);
        reviewResponse.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReviewResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    // TODO: 15.07.2018 Сохранять в базу
                    @Override
                    public void onNext(ReviewResponse reviewResponse) {
                        if (view != null) {
                            List<Review> reviews = reviewResponse.getResults();
                            view.setReviews(reviews);
                            view.hideProgress();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.onItemsLoadComplete();
                            view.hideProgress();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (view != null) {
                            view.onItemsLoadComplete();
                            view.hideProgress();
                        }
                    }
                });
    }

    @Override
    public void loadTrailersFromApi(String movieId) {
        view.showProgress();
        Observable<TrailerResponse> trailersResponse = repository.getTrailersFromApi(movieId);
        trailersResponse.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TrailerResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    // TODO: 15.07.2018 Сохранять в базу
                    @Override
                    public void onNext(TrailerResponse trailerResponse) {
                        if (view != null) {
                            List<Trailer> trailers = trailerResponse.getResults();
                            view.setTrailers(trailers);
                            view.hideProgress();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.onItemsLoadComplete();
                            view.hideProgress();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (view != null) {
                            view.onItemsLoadComplete();
                            view.hideProgress();
                        }
                    }
                });
    }

    @Override
    public void attachView(MovieDetailContract.View view) {
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
