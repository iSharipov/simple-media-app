package com.isharipov.simplemediaapp.di;

import com.isharipov.simplemediaapp.MainActivity;
import com.isharipov.simplemediaapp.movie.ui.category.MovieCategoryFragment;
import com.isharipov.simplemediaapp.movie.ui.category.MovieCategoryPresenter;
import com.isharipov.simplemediaapp.movie.ui.MovieFragment;
import com.isharipov.simplemediaapp.music.preference.PreferenceMusicActivity;
import com.isharipov.simplemediaapp.music.preference.PreferenceMusicFragment;
import com.isharipov.simplemediaapp.music.ui.MusicFragment;
import com.isharipov.simplemediaapp.music.ui.artist.ArtistsFragment;
import com.isharipov.simplemediaapp.music.ui.track.TracksFragment;
import com.isharipov.simplemediaapp.news.preference.PreferenceNewsActivity;
import com.isharipov.simplemediaapp.news.preference.PreferenceNewsFragment;
import com.isharipov.simplemediaapp.news.ui.NewsFragment;
import com.isharipov.simplemediaapp.news.ui.news.category.CategoryContract;
import com.isharipov.simplemediaapp.news.ui.news.category.NewsCategoryFragment;
import com.isharipov.simplemediaapp.news.ui.news.category.NewsCategoryPresenter;
import com.isharipov.simplemediaapp.news.ui.news.everything.EverythingContract;
import com.isharipov.simplemediaapp.news.ui.news.everything.EverythingFragment;
import com.isharipov.simplemediaapp.news.ui.news.everything.EverythingPresenter;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract PreferenceNewsActivity preferenceNewsActivity();

    @ContributesAndroidInjector
    abstract PreferenceMusicActivity preferenceMusicActivity();

    @ContributesAndroidInjector
    abstract NewsFragment newsFragment();

    @ContributesAndroidInjector
    abstract NewsCategoryFragment categoryFragment();

    @ContributesAndroidInjector
    abstract EverythingFragment everythingFragment();

    @ContributesAndroidInjector
    abstract PreferenceNewsFragment preferenceNewsFragment();

    @ContributesAndroidInjector
    abstract PreferenceMusicFragment preferenceMusicFragment();

    @Binds
    @Named("NewsCategory")
    abstract CategoryContract.Presenter newsCategoryPresenter(NewsCategoryPresenter presenter);

    @Binds
    @Named("MovieCategory")
    abstract CategoryContract.Presenter movieCategoryPresenter(MovieCategoryPresenter presenter);

    @Binds
    abstract EverythingContract.Presenter everythingPresenter(EverythingPresenter presenter);

    @ContributesAndroidInjector
    abstract MusicFragment musicFragment();

    @ContributesAndroidInjector
    abstract ArtistsFragment artistsFragment();

    @ContributesAndroidInjector
    abstract TracksFragment tracksFragment();

    @ContributesAndroidInjector
    abstract MovieFragment movieFragment();

    @ContributesAndroidInjector
    abstract MovieCategoryFragment movieCategoryFragment();
}