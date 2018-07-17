package com.isharipov.simplemediaapp.di;

import com.google.android.gms.analytics.Tracker;

import java.util.Map;

public interface Analytics {

    String CATEGORY_TABBAR_PRESSED = "Tab Bar Pressed";

    String ACTION_MUSIC_TABBAR_PRESSED = "Music Tab Bar Pressed";
    String ACTION_NEWS_TABBAR_PRESSED = "News Tab Bar Pressed";
    String ACTION_MOVIE_TABBAR_PRESSED = "Movie Tab Bar Pressed";

    /**
     * @see {@link Tracker#send(Map)} for usage.
     */
    void send(Map<String, String> params);

    class GoogleAnalytics implements Analytics {
        private final Tracker tracker;

        public GoogleAnalytics(Tracker tracker) {
            this.tracker = tracker;
        }

        @Override
        public void send(Map<String, String> params) {
            tracker.send(params);
        }
    }
}

