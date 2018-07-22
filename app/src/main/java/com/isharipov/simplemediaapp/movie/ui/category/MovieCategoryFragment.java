package com.isharipov.simplemediaapp.movie.ui.category;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
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
import com.isharipov.simplemediaapp.movie.util.QueryMovieParam;
import com.isharipov.simplemediaapp.news.ui.news.category.CategoryContract;
import com.isharipov.simplemediaapp.util.CountryLanguageWrapper;
import com.isharipov.simplemediaapp.util.PrefUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

/**
 * 05.07.2018.
 */
public class MovieCategoryFragment extends DaggerFragment implements CategoryContract.View<Movie>, SwipeRefreshLayout.OnRefreshListener {

    private static final String PAGE = "PAGE";
    private static final String POSITION = "POSITION";
    private int position;
    private int page = 1;
    private String region;
    private String language;
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
    @BindArray(R.array.pref_language_value)
    String[] prefLanguageValue;
    @BindString(R.string.pref_movie_region_key)
    String prefMovieRegionKey;
    @BindString(R.string.pref_movie_language_key)
    String prefMovieLanguageKey;
    @Inject
    @Named("MovieCategory")
    CategoryContract.Presenter presenter;
    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;
    Unbinder unbinder;

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
                () -> presenter.loadFromApi(new QueryMovieParam(region, categoryQueryParam[position], ++page, language)),
                (movie) -> {
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container, MovieDetailFragment.newInstance(movie));
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.movie_fragment_category, container, false);
        unbinder = ButterKnife.bind(this, root);
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
        initRegionAndLanguageParam();
        movieAdapter.clearMovies();
        presenter.attachView(this);
        presenter.loadFromApi(new QueryMovieParam(region, categoryQueryParam[position], page, language));
    }

    private void initRegionAndLanguageParam() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        if (preferences.contains(prefMovieRegionKey)) {
            region = preferences.getString(prefMovieRegionKey, "en");
            language = preferences.getString(prefMovieLanguageKey, "en");
        } else {
            CountryLanguageWrapper fromLocale = PrefUtils.getDefaultCountryFromLocale(prefCountryValue, prefLanguageValue);
            preferences.edit().putString(prefMovieRegionKey, region = fromLocale.getCountry()).apply();
            preferences.edit().putString(prefMovieLanguageKey, language = fromLocale.getLanguage()).apply();
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
        unbinder.unbind();
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
        presenter.loadFromApi(new QueryMovieParam(region, categoryQueryParam[position], page, language));
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
