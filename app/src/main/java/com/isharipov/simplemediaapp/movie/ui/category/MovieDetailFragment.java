package com.isharipov.simplemediaapp.movie.ui.category;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.isharipov.simplemediaapp.R;
import com.isharipov.simplemediaapp.glide.GlideApp;
import com.isharipov.simplemediaapp.movie.model.Movie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

/**
 * 15.07.2018.
 */
public class MovieDetailFragment extends DaggerFragment implements MovieDetailContract.View {

    private static final String MOVIE = "MOVIE";
    @BindView(R.id.movie_title)
    TextView movieTitle;
    @BindView(R.id.movie_detail_logo)
    ImageView movieDetailLogo;
    @BindView(R.id.release_date)
    TextView releaseDate;
    @BindView(R.id.movie_overview)
    TextView movieOverview;
    @BindView(R.id.rating_bar)
    RatingBar ratingBar;
    @BindView(R.id.favorite_button)
    ImageView favoriteButton;
    @BindView(R.id.trailerView)
    RecyclerView trailerView;
    @BindView(R.id.reviewView)
    RecyclerView reviewView;
    private Movie movie;
    private ReviewAdapter reviewAdapter;
    private TrailerAdapter trailerAdapter;
    @Inject
    MovieDetailContract.Presenter presenter;

    @Inject
    public MovieDetailFragment() {

    }

    public static MovieDetailFragment newInstance(Movie movie) {
        Bundle args = new Bundle();
        MovieDetailFragment fragment = new MovieDetailFragment();
        args.putSerializable(MOVIE, movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null && !args.isEmpty()) {
            movie = (Movie) args.getSerializable(MOVIE);
            trailerAdapter = new TrailerAdapter(
                    new ArrayList<>(0),
                    (trailer -> {
                        watchYoutubeVideo(getContext(), trailer.getKey());
                    })
            );
            reviewAdapter = new ReviewAdapter(new ArrayList<>(0));
        }
    }

    private void watchYoutubeVideo(Context context, String key) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + key));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + key));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.loadReviewsFromApi(String.valueOf(movie.getId()));
        presenter.loadTrailersFromApi(String.valueOf(movie.getId()));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.movie_fragment_detail, container, false);
        ButterKnife.bind(this, root);
        initTrailerView();
        initReviewView();
        setupUI(movie);
        return root;
    }

    private void initTrailerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        trailerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                trailerView.getContext(),
                linearLayoutManager.getOrientation()
        );
        trailerView.addItemDecoration(dividerItemDecoration);
        trailerView.setAdapter(trailerAdapter);
    }

    private void initReviewView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        reviewView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                reviewView.getContext(),
                linearLayoutManager.getOrientation()
        );
        reviewView.addItemDecoration(dividerItemDecoration);
        reviewView.setAdapter(reviewAdapter);
    }

    private void setupUI(Movie movie) {
        String theMovieDbImgUrl = getString(R.string.themoviedb_img_url);
        GlideApp.with(this)
                .load(theMovieDbImgUrl + "w342" + movie.getPosterPath())
                .into(movieDetailLogo);
        movieTitle.setText(movie.getTitle());
        releaseDate.setText(movie.getReleaseDate());
        ratingBar.setRating(movie.getVoteAverage().floatValue());
        movieOverview.setText(movie.getOverview());
    }

    @Override
    public void setTrailers(List trailers) {
        trailerAdapter.appendData(trailers);
    }

    @Override
    public void setReviews(List reviews) {
        reviewAdapter.appendData(reviews);
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onItemsLoadComplete() {

    }


}
