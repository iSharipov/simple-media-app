package com.isharipov.simplemediaapp.di;

import com.isharipov.simplemediaapp.MainActivity;
import com.isharipov.simplemediaapp.news.ui.NewsFragment;
import com.isharipov.simplemediaapp.news.ui.news.category.CategoryContract;
import com.isharipov.simplemediaapp.news.ui.news.category.CategoryFragment;
import com.isharipov.simplemediaapp.news.ui.news.category.CategoryPresenter;
import com.isharipov.simplemediaapp.news.ui.news.everything.EverythingContract;
import com.isharipov.simplemediaapp.news.ui.news.everything.EverythingFragment;
import com.isharipov.simplemediaapp.news.ui.news.everything.EverythingPresenter;

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

    @ContributesAndroidInjector
    abstract EverythingFragment everythingFragment();

    @Binds
    abstract CategoryContract.Presenter categoryPresenter(CategoryPresenter presenter);

    @Binds
    abstract EverythingContract.Presenter everythingPresenter(EverythingPresenter presenter);
}