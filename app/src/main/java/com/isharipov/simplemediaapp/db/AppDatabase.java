package com.isharipov.simplemediaapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.isharipov.simplemediaapp.movie.model.Genre;
import com.isharipov.simplemediaapp.movie.model.Movie;
import com.isharipov.simplemediaapp.movie.repository.db.MovieDao;
import com.isharipov.simplemediaapp.music.model.Image;
import com.isharipov.simplemediaapp.music.model.artist.Artist;
import com.isharipov.simplemediaapp.music.model.track.Track;
import com.isharipov.simplemediaapp.music.repository.db.MusicDao;
import com.isharipov.simplemediaapp.news.model.Article;
import com.isharipov.simplemediaapp.news.model.source.Source;
import com.isharipov.simplemediaapp.news.repository.db.NewsDao;

@Database(entities = {
        Article.class,
        Source.class,
        Artist.class,
        Image.class,
        Track.class,
        Movie.class,
        Genre.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NewsDao newsDao();

    public abstract MusicDao musicDao();

    public abstract MovieDao movieDao();


}