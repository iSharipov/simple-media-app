package com.isharipov.simplemediaapp.movie.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.isharipov.simplemediaapp.R;
import com.isharipov.simplemediaapp.movie.model.Movie;

import java.util.List;

import static com.isharipov.simplemediaapp.movie.widget.UpcomingMovieWidgetProvider.moviesUpcomingList;

/**
 * 10.06.2018.
 */
public class UpcomingMovieWidgetRemoteViewsService extends RemoteViewsService {
    List<Movie> remoteViewIngredientsList;
    Context context;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {
            @Override
            public void onCreate() {
                context = getApplicationContext();
            }

            @Override
            public void onDataSetChanged() {
                remoteViewIngredientsList = moviesUpcomingList;
            }

            @Override
            public void onDestroy() {

            }

            @Override
            public int getCount() {
                return remoteViewIngredientsList.size();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.movie_upcoming_widget_item);
                remoteViews.setTextViewText(R.id.movie_upcoming_widget_item, remoteViewIngredientsList.get(position).getOriginalTitle());
                Intent fillInIntent = new Intent();
                remoteViews.setOnClickFillInIntent(R.id.movie_upcoming_widget_item, fillInIntent);

                return remoteViews;
            }

            @Override
            public RemoteViews getLoadingView() {
                return null;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }
        };
    }
}
