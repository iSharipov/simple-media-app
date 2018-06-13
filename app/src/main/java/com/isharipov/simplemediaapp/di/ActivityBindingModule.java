package com.isharipov.simplemediaapp.di;

import com.isharipov.simplemediaapp.MainActivity;
import com.isharipov.simplemediaapp.news.ui.NewsContract;
import com.isharipov.simplemediaapp.news.ui.NewsFragment;
import com.isharipov.simplemediaapp.news.ui.NewsPresenter;
import com.isharipov.simplemediaapp.news.ui.category.BusinessFragment;
import com.isharipov.simplemediaapp.news.ui.category.EntertainmentFragment;
import com.isharipov.simplemediaapp.news.ui.category.GeneralFragment;
import com.isharipov.simplemediaapp.news.ui.category.HealthFragment;
import com.isharipov.simplemediaapp.news.ui.category.ScienceFragment;
import com.isharipov.simplemediaapp.news.ui.category.SportsFragment;
import com.isharipov.simplemediaapp.news.ui.category.TechnologyFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract NewsFragment newsFragment();

    @ContributesAndroidInjector
    abstract BusinessFragment businessFragment();

    @ContributesAndroidInjector
    abstract EntertainmentFragment entertainmentFragment();

    @ContributesAndroidInjector
    abstract GeneralFragment generalFragment();

    @ContributesAndroidInjector
    abstract HealthFragment healthFragment();

    @ContributesAndroidInjector
    abstract ScienceFragment scienceFragment();

    @ContributesAndroidInjector
    abstract SportsFragment sportsFragment();

    @ContributesAndroidInjector
    abstract TechnologyFragment technologyFragment();

    @Binds
    abstract NewsContract.Presenter newsPresenter(NewsPresenter presenter);
}