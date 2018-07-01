package com.isharipov.simplemediaapp.news.repository.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.isharipov.simplemediaapp.music.repository.db.MusicDao;
import com.isharipov.simplemediaapp.news.model.Article;
import com.isharipov.simplemediaapp.news.model.source.Source;

@Database(entities = {Article.class, Source.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NewsDao newsDao();

    public abstract MusicDao musicDao();
}