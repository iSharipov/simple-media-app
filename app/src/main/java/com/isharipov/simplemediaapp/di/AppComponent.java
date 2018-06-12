package com.isharipov.simplemediaapp.di;

import android.app.Application;

import com.isharipov.simplemediaapp.app.MediaApp;
import com.isharipov.simplemediaapp.glide.GlideConfiguration;

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
        NetworkModule.class,
        AndroidSupportInjectionModule.class
})
public interface AppComponent extends AndroidInjector<MediaApp> {

    void inject(GlideConfiguration glideConfiguration);

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
