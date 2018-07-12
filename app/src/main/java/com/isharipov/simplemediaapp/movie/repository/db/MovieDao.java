package com.isharipov.simplemediaapp.movie.repository.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.isharipov.simplemediaapp.movie.model.Genre;
import com.isharipov.simplemediaapp.movie.model.Movie;

import java.util.List;

/**
 * 10.07.2018.
 */
@Dao
public interface MovieDao {
    @Insert
    void insertMovies(List<Movie> movies);

    @Insert
    void insertGenres(List<Genre> genres);

}
