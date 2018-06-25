package com.isharipov.simplemediaapp.news.repository.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.isharipov.simplemediaapp.news.model.Article;

@Database(entities = {Article.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NewsDao articleDao();
}