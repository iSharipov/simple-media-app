package com.isharipov.simplemediaapp.news.repository.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.isharipov.simplemediaapp.news.model.Article;
import com.isharipov.simplemediaapp.news.model.source.Source;

import java.util.List;

import io.reactivex.Single;

/**
 * 14.06.2018.
 */
@Dao
public interface NewsDao {
    @Query("SELECT * FROM articles WHERE category=:category")
    Single<List<Article>> getArticlesByCategory(String category);

    @Query("SELECT * FROM articles WHERE category IS NULL OR category = ''")
    Single<List<Article>> getArticlesWhereNoCategory();

    @Query("SELECT * FROM sources WHERE country=:country")
    Single<List<Source>> getSourcesByCountry(String country);

    @Insert
    void insertArticles(List<Article> articles);

    @Insert
    void insertSources(List<Source> sources);
}
