package com.isharipov.simplemediaapp.movie.widget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.isharipov.simplemediaapp.movie.model.Movie;

import java.util.ArrayList;

/**
 * 10.06.2018.
 */
public class UpcomingMovieUpdateService extends IntentService {

    public static String FROM_MOVIE_CATEGORY_FRAGMENT = "FROM_MOVIE_CATEGORY_FRAGMENT";

    public UpcomingMovieUpdateService() {
        super("UpcomingMovieUpdateService");
    }

    public static void startMovieUpcomingServiceService(Context context, ArrayList<Movie> upcomingMovies) {
        Intent intent = new Intent(context, UpcomingMovieUpdateService.class);
        intent.putExtra(FROM_MOVIE_CATEGORY_FRAGMENT, upcomingMovies);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            ArrayList<Movie> moviesUpcomingList = (ArrayList<Movie>)intent.getExtras().getSerializable(FROM_MOVIE_CATEGORY_FRAGMENT);
            handleActionUpdateBakingWidgets(moviesUpcomingList);

        }
    }


    private void handleActionUpdateBakingWidgets(ArrayList<Movie> fromActivityIngredientsList) {
        Intent intent = new Intent("android.appwidget.action.APPWIDGET_UPDATE2");
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE2");
        intent.putExtra(FROM_MOVIE_CATEGORY_FRAGMENT, fromActivityIngredientsList);
        sendBroadcast(intent);
    }

}
