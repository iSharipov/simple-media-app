package com.isharipov.simplemediaapp.di;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.isharipov.simplemediaapp.BuildConfig;
import com.isharipov.simplemediaapp.R;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

@Module
public class GoogleAnalyticsModule {
    @Provides
    @Singleton
    static Analytics provideAnalytics(Application app) {
        if (BuildConfig.DEBUG) {
            return params -> Timber.tag("Analytics").d(String.valueOf(params));
        }

        GoogleAnalytics googleAnalytics = GoogleAnalytics.getInstance(app);
        Tracker tracker = googleAnalytics.newTracker(R.xml.global_tracker);
        tracker.setSessionTimeout(300);
        return new Analytics.GoogleAnalytics(tracker);
    }
}
