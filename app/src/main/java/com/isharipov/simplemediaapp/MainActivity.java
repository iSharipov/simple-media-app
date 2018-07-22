package com.isharipov.simplemediaapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;

import com.isharipov.simplemediaapp.di.Analytics;
import com.isharipov.simplemediaapp.movie.ui.MovieFragment;
import com.isharipov.simplemediaapp.music.ui.MusicFragment;
import com.isharipov.simplemediaapp.news.ui.NewsFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

import static java.util.Collections.singletonMap;

public class MainActivity extends DaggerAppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String navigationPage = "NAVIGATION_PAGE";
    private int navigationId = R.id.navigation_news;
    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;
    @Inject
    Analytics analytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            navigationId = savedInstanceState.getInt(navigationPage);
        }
        initBottomNavigationView();
    }

    private void initBottomNavigationView() {
        bottomNavigationView.setSelectedItemId(navigationId);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        View navigationViewViewById = bottomNavigationView.findViewById(navigationId);
        if (navigationViewViewById != null) {
            bottomNavigationView.findViewById(navigationId).performClick();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (navigationId = item.getItemId()) {
            case R.id.navigation_music:
                analytics.send(singletonMap(Analytics.CATEGORY_TABBAR_PRESSED, Analytics.ACTION_MUSIC_TABBAR_PRESSED));
                return openFragment(MusicFragment.newInstance());
            case R.id.navigation_news:
                analytics.send(singletonMap(Analytics.CATEGORY_TABBAR_PRESSED, Analytics.ACTION_NEWS_TABBAR_PRESSED));
                return openFragment(NewsFragment.newInstance());
            case R.id.navigation_movies:
                analytics.send(singletonMap(Analytics.CATEGORY_TABBAR_PRESSED, Analytics.ACTION_MOVIE_TABBAR_PRESSED));
                return openFragment(MovieFragment.newInstance());
        }
        return false;
    }

    private boolean openFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        return fragmentTransaction.commit() >= 0;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle bundle = new Bundle();
        bundle.putInt(navigationPage, navigationId);
    }
}
