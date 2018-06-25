package com.isharipov.simplemediaapp.news.di;

import com.isharipov.simplemediaapp.news.repository.NewsRepository;
import com.isharipov.simplemediaapp.news.repository.NewsRepositoryImpl;
import com.isharipov.simplemediaapp.news.repository.api.NewsApi;
import com.isharipov.simplemediaapp.news.repository.db.NewsDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 13.06.2018.
 */
@Module
public class RepositoryModule {

    @Provides
    @Singleton
    NewsRepository provideArticleRepository(NewsApi newsApi, NewsDao newsDao) {
        return new NewsRepositoryImpl(newsApi, newsDao);
    }
}
