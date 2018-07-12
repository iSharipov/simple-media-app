package com.isharipov.simplemediaapp.di;

import android.app.Application;

import com.isharipov.simplemediaapp.app.MediaApp;
import com.isharipov.simplemediaapp.db.AppDatabase;
import com.isharipov.simplemediaapp.glide.GlideConfiguration;
import com.isharipov.simplemediaapp.movie.di.MovieApiModule;
import com.isharipov.simplemediaapp.movie.repository.MovieRepository;
import com.isharipov.simplemediaapp.movie.repository.api.MovieApi;
import com.isharipov.simplemediaapp.movie.repository.db.MovieDao;
import com.isharipov.simplemediaapp.music.di.MusicApiModule;
import com.isharipov.simplemediaapp.music.repository.MusicRepository;
import com.isharipov.simplemediaapp.music.repository.api.MusicApi;
import com.isharipov.simplemediaapp.music.repository.db.MusicDao;
import com.isharipov.simplemediaapp.news.di.FaviconFinderApiModule;
import com.isharipov.simplemediaapp.news.di.NewsApiModule;
import com.isharipov.simplemediaapp.news.repository.NewsRepository;
import com.isharipov.simplemediaapp.news.repository.api.NewsApi;
import com.isharipov.simplemediaapp.news.repository.db.NewsDao;
import com.isharipov.simplemediaapp.rx.RxModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * 24.05.2018.
 */
@Singleton
@Component(modules = {
        AppContextModule.class,
        ActivityBindingModule.class,
        NetworkModule.class,
        RxModule.class,
        RepositoryModule.class,
        NewsApiModule.class,
        MusicApiModule.class,
        MovieApiModule.class,
        FaviconFinderApiModule.class,
        ApplicationDbModule.class,
        AndroidSupportInjectionModule.class
})
public interface AppComponent extends AndroidInjector<MediaApp> {

    void inject(GlideConfiguration glideConfiguration);

    NewsApi provideNewsApiService();

    MusicApi provideMusicApiService();

    MovieApi provideMovieApiService();

    NewsDao articleDao();

    MusicDao musicDao();

    MovieDao movieDao();

    AppDatabase appDatabase();

    NewsRepository newsRepository();

    MusicRepository musicRepository();

    MovieRepository movieRepository();

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
