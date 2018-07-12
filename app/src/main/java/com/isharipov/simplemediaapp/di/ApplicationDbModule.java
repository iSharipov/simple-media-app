package com.isharipov.simplemediaapp.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.isharipov.simplemediaapp.db.AppDatabase;
import com.isharipov.simplemediaapp.movie.repository.db.MovieDao;
import com.isharipov.simplemediaapp.music.repository.db.MusicDao;
import com.isharipov.simplemediaapp.news.repository.db.NewsDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 15.06.2018.
 */
@Module
public class ApplicationDbModule {

    @Singleton
    @Provides
    AppDatabase providesAppDatabase(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, "demo-db").build();
    }

    @Singleton
    @Provides
    NewsDao providesArticleDao(AppDatabase appDatabase) {
        return appDatabase.newsDao();
    }

    @Singleton
    @Provides
    MusicDao providesMusicDao(AppDatabase appDatabase) {
        return appDatabase.musicDao();
    }

    @Singleton
    @Provides
    MovieDao providesMovieDao(AppDatabase appDatabase) {
        return appDatabase.movieDao();
    }
}
