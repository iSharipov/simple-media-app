package com.isharipov.simplemediaapp.movie.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.isharipov.simplemediaapp.MainActivity;
import com.isharipov.simplemediaapp.R;
import com.isharipov.simplemediaapp.movie.model.Movie;
import com.isharipov.simplemediaapp.movie.ui.MovieFragment;
import com.isharipov.simplemediaapp.movie.ui.category.MovieCategoryFragment;

import java.util.ArrayList;
import java.util.List;

import static com.isharipov.simplemediaapp.movie.widget.UpcomingMovieUpdateService.FROM_MOVIE_CATEGORY_FRAGMENT;

/**
 * 10.06.2018.
 */
public class UpcomingMovieWidgetProvider extends AppWidgetProvider {

    static List<Movie> moviesUpcomingList = new ArrayList<>();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.movie_upcoming_widget);

        Intent appIntent = new Intent(context, MovieFragment.class);
        appIntent.addCategory(Intent.ACTION_MAIN);
        appIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        appIntent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setPendingIntentTemplate(R.id.movie_upcoming_widget, appPendingIntent);

        Intent intent = new Intent(context, UpcomingMovieWidgetRemoteViewsService.class);
        remoteViews.setRemoteAdapter(R.id.movie_upcoming_widget, intent);
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    }

    public static void updateBakingWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        //
    }

    @Override
    public void onDisabled(Context context) {
        //
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, UpcomingMovieWidgetProvider.class));

        final String action = intent.getAction();

        if ("android.appwidget.action.APPWIDGET_UPDATE2".equals(action)) {
            moviesUpcomingList = (ArrayList<Movie>) intent.getExtras().getSerializable(FROM_MOVIE_CATEGORY_FRAGMENT);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.movie_upcoming_widget);
            UpcomingMovieWidgetProvider.updateBakingWidgets(context, appWidgetManager, appWidgetIds);
            super.onReceive(context, intent);
        }
    }

}
