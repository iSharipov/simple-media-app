package com.isharipov.simplemediaapp.di;

import com.isharipov.simplemediaapp.MainActivity;
import com.isharipov.simplemediaapp.news.ui.category.CategoryContract;
import com.isharipov.simplemediaapp.news.ui.NewsFragment;
import com.isharipov.simplemediaapp.news.ui.category.CategoryPresenter;
import com.isharipov.simplemediaapp.news.ui.category.CategoryFragment;

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
    abstract CategoryFragment categoryFragment();

    @Binds
    abstract CategoryContract.Presenter newsPresenter(CategoryPresenter presenter);
}