package com.isharipov.simplemediaapp.di;

import com.isharipov.simplemediaapp.movie.repository.MovieRepository;
import com.isharipov.simplemediaapp.movie.repository.MovieRepositoryImpl;
import com.isharipov.simplemediaapp.movie.repository.api.MovieApi;
import com.isharipov.simplemediaapp.music.repository.MusicRepository;
import com.isharipov.simplemediaapp.music.repository.MusicRepositoryImpl;
import com.isharipov.simplemediaapp.music.repository.api.MusicApi;
import com.isharipov.simplemediaapp.music.repository.db.MusicDao;
import com.isharipov.simplemediaapp.news.repository.NewsRepository;
import com.isharipov.simplemediaapp.news.repository.NewsRepositoryImpl;
import com.isharipov.simplemediaapp.news.repository.api.NewsApi;
import com.isharipov.simplemediaapp.news.repository.db.NewsDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * 13.06.2018.
 */
@Module
class RepositoryModule {

    @Provides
    @Singleton
    NewsRepository provideArticleRepository(NewsApi newsApi, NewsDao newsDao) {
        return new NewsRepositoryImpl(newsApi, newsDao);
    }

    @Provides
    @Singleton
    MusicRepository provideMusicRepository(MusicApi musicApi, MusicDao musicDao, CompositeDisposable compositeDisposable) {
        return new MusicRepositoryImpl(musicApi, musicDao, compositeDisposable);
    }

    @Provides
    @Singleton
    MovieRepository provideMovieRepository(MovieApi movieApi) {
        return new MovieRepositoryImpl(movieApi);
    }
}
