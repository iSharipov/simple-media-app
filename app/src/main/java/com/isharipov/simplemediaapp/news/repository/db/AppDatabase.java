package com.isharipov.simplemediaapp.news.repository.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.isharipov.simplemediaapp.news.model.Article;
import com.isharipov.simplemediaapp.news.model.source.Source;

@Database(entities = {Article.class, Source.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NewsDao articleDao();
}