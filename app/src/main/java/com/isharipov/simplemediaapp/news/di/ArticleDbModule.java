package com.isharipov.simplemediaapp.news.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.isharipov.simplemediaapp.news.repository.NewsRepository;
import com.isharipov.simplemediaapp.news.repository.NewsRepositoryImpl;
import com.isharipov.simplemediaapp.news.repository.api.NewsApi;
import com.isharipov.simplemediaapp.news.repository.db.AppDatabase;
import com.isharipov.simplemediaapp.news.repository.db.ArticleDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 15.06.2018.
 */
@Module
public class ArticleDbModule {

    @Singleton
    @Provides
    AppDatabase providesAppDatabase(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, "demo-db").build();
    }

    @Singleton
    @Provides
    ArticleDao providesArticleDao(AppDatabase appDatabase) {
        return appDatabase.articleDao();
    }

    @Singleton
    @Provides
    NewsRepository productRepository(NewsApi newsApi, ArticleDao articleDao) {
        return new NewsRepositoryImpl(newsApi, articleDao);
    }
}
