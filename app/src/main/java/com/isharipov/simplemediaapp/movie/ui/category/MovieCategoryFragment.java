package com.isharipov.simplemediaapp.movie.ui.category;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import com.isharipov.simplemediaapp.news.model.Article;
import com.isharipov.simplemediaapp.news.ui.news.category.CategoryContract;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.DaggerFragment;

/**
 * 05.07.2018.
 */
public class MovieCategoryFragment extends DaggerFragment implements CategoryContract.View, SwipeRefreshLayout.OnRefreshListener {

    private static final String PAGE = "PAGE";
    private int page = 1;
    private String country;
    private MovieAdapter movieAdapter;
    @Inject
    @Named("MovieCategory")
    CategoryContract.Presenter presenter;

    @Inject
    public MovieCategoryFragment() {
    }

    public static MovieCategoryFragment newInstance(int position) {
        Bundle args = new Bundle();
        MovieCategoryFragment fragment = new MovieCategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onRefresh() {

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

    @Override
    public void setMoreLoaded(boolean moreLoaded) {

    }

    @Override
    public void setData(List<Article> articles) {

    }
}
