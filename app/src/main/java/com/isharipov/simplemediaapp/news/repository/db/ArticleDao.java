package com.isharipov.simplemediaapp.news.repository.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.isharipov.simplemediaapp.news.model.Article;

import java.util.List;

import io.reactivex.Single;

/**
 * 14.06.2018.
 */
@Dao
public interface ArticleDao {
    @Query("SELECT * FROM articles WHERE category=:category")
    Single<List<Article>> getArticles(String category);

    @Insert
    void insertAll(List<Article> articles);
}
