package com.isharipov.simplemediaapp.movie.ui.category;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;

import com.isharipov.simplemediaapp.R;
import com.isharipov.simplemediaapp.movie.model.Movie;
import com.isharipov.simplemediaapp.news.model.Article;
import com.isharipov.simplemediaapp.news.model.QueryCategoryParam;
import com.isharipov.simplemediaapp.news.ui.news.category.CategoryContract;
import com.isharipov.simplemediaapp.util.PrefUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

/**
 * 05.07.2018.
 */
public class MovieCategoryFragment extends DaggerFragment implements CategoryContract.View<Movie>, SwipeRefreshLayout.OnRefreshListener {

    private static final String PAGE = "PAGE";
    private static final String POSITION = "POSITION";
    private int position;
    private int page = 1;
    private String country;
    private MovieAdapter movieAdapter;
    @BindView(R.id.movie_refresh_layout)
    SwipeRefreshLayout categoryRefreshLayout;
    @BindView(R.id.progressBarHolder)
    FrameLayout progressBarHolderLayout;
    @BindView(R.id.movie_recycler_layout)
    RecyclerView categoryRecyclerLayout;
    @BindArray(R.array.movie_query_param)
    String[] categoryQueryParam;
    @BindArray(R.array.pref_country_value)
    String[] prefCountryValue;
    @BindString(R.string.pref_news_key)
    String prefNewsKey;
    @Inject
    @Named("MovieCategory")
    CategoryContract.Presenter presenter;
    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;

    @Inject
    public MovieCategoryFragment() {
    }

    public static MovieCategoryFragment newInstance(int position) {
        Bundle args = new Bundle();
        MovieCategoryFragment fragment = new MovieCategoryFragment();
        args.putInt(POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null && !args.isEmpty()) {
            position = args.getInt(POSITION);
        }
        if (savedInstanceState != null && !savedInstanceState.isEmpty()) {
            position = savedInstanceState.getInt(POSITION);
            page = savedInstanceState.getInt(PAGE);
        }
        movieAdapter = new MovieAdapter(
                new ArrayList<>(0),
                null,
                () -> presenter.loadFromApi(new QueryCategoryParam(country, categoryQueryParam[position], ++page)));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.movie_fragment_category, container, false);
        ButterKnife.bind(this, root);
        setupUI();
        return root;
    }

    private void setupUI() {
        categoryRecyclerLayout.setLayoutManager(new GridLayoutManager(getContext(), 2));
        categoryRecyclerLayout.setAdapter(movieAdapter);
        categoryRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        initCountryParam();
        movieAdapter.clearMovies();
        presenter.attachView(this);
        presenter.loadFromApi(new QueryCategoryParam(country, categoryQueryParam[position], page));
    }

    private void initCountryParam() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        if (preferences.contains(prefNewsKey)) {
            country = preferences.getString(prefNewsKey, "en");
        } else {
            country = PrefUtils.getDefaultCountryFromLocale(prefCountryValue);
            preferences.edit().putString(prefNewsKey, country).apply();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.detachView();
        hideProgress();
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgress();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PAGE, page);
        outState.putInt(POSITION, position);
    }

    @Override
    public void setData(List<Movie> movies) {
        movieAdapter.appendData(movies);
    }

    @Override
    public void showProgress() {
        inAnimation = new AlphaAnimation(0f, 1f);
        inAnimation.setDuration(200);
        progressBarHolderLayout.setAnimation(inAnimation);
        progressBarHolderLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        outAnimation = new AlphaAnimation(1f, 0f);
        outAnimation.setDuration(200);
        progressBarHolderLayout.setAnimation(outAnimation);
        progressBarHolderLayout.setVisibility(View.GONE);
    }


    @Override
    public void onRefresh() {
        page = 1;
        movieAdapter.clearMovies();
        presenter.loadFromApi(new QueryCategoryParam(country, categoryQueryParam[position], page));
    }

    @Override
    public void onItemsLoadComplete() {
        categoryRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setMoreLoaded(boolean moreLoaded) {
        movieAdapter.setLoaded(false);
    }
}
