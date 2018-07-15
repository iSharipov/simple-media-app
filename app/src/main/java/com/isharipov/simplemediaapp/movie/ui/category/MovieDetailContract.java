package com.isharipov.simplemediaapp.movie.ui.category;

import com.isharipov.simplemediaapp.BasePresenter;
import com.isharipov.simplemediaapp.movie.model.Review;
import com.isharipov.simplemediaapp.movie.model.Trailer;

import java.util.List;

/**
 * 15.07.2018.
 */
public interface MovieDetailContract {
    interface View<T> {

        void setTrailers(List<Trailer> trailers);

        void setReviews(List<Review> reviews);

        void showProgress();

        void hideProgress();

        void onItemsLoadComplete();
    }

    interface Presenter extends BasePresenter<MovieDetailContract.View> {
        void loadReviewsFromApi(String movieId);

        void loadTrailersFromApi(String movieId);
    }
}
