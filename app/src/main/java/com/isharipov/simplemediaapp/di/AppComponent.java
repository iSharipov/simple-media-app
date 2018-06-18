package com.isharipov.simplemediaapp.di;

import android.app.Application;

import com.isharipov.simplemediaapp.app.MediaApp;
import com.isharipov.simplemediaapp.glide.GlideConfiguration;
import com.isharipov.simplemediaapp.news.di.ArticleApiModule;
import com.isharipov.simplemediaapp.news.di.ArticleDbModule;
import com.isharipov.simplemediaapp.news.di.RepositoryModule;
import com.isharipov.simplemediaapp.news.repository.ArticleRepository;
import com.isharipov.simplemediaapp.news.repository.db.AppDatabase;
import com.isharipov.simplemediaapp.news.repository.db.ArticleDao;

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
        ArticleApiModule.class,
        ArticleDbModule.class,
        AndroidSupportInjectionModule.class
})
public interface AppComponent extends AndroidInjector<MediaApp> {

    void inject(GlideConfiguration glideConfiguration);

    ArticleDao articleDao();

    AppDatabase appDatabase();

    ArticleRepository articleRepository();

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
