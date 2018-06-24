package com.isharipov.simplemediaapp.news.di;

import com.isharipov.simplemediaapp.news.repository.ArticleRepository;
import com.isharipov.simplemediaapp.news.repository.ArticleRepositoryImpl;
import com.isharipov.simplemediaapp.news.repository.api.ArticleApi;
import com.isharipov.simplemediaapp.news.repository.db.ArticleDao;

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
    ArticleRepository provideArticleRepository(ArticleApi articleApi, ArticleDao articleDao) {
        return new ArticleRepositoryImpl(articleApi, articleDao);
    }
}
