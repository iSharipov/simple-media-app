package com.isharipov.simplemediaapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;

import com.isharipov.simplemediaapp.R;
import com.isharipov.simplemediaapp.di.Analytics;
import com.isharipov.simplemediaapp.movie.ui.MovieFragment;
import com.isharipov.simplemediaapp.music.ui.MusicFragment;
import com.isharipov.simplemediaapp.news.ui.NewsFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

import static java.util.Collections.singletonMap;

/**
 * 12.06.2018.
 */
public interface BasePresenter<V> {

    void attachView(V v);

    void detachView();

    void unsubscribe();
}
