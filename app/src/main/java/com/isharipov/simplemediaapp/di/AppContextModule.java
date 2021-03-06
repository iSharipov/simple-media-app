package com.isharipov.simplemediaapp.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

@Module
abstract class AppContextModule {
    @Binds
    abstract Context bindContext(Application application);
}