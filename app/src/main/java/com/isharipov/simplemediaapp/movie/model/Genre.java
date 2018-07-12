package com.isharipov.simplemediaapp.movie.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

/**
 * 11.07.2018.
 */
@Entity(tableName = "genres", indices = @Index(value = {"name", "language"}, unique = true))
public class Genre implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @SerializedName("genreId")
    private long genreId;
    private String name;
    private String language;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGenreId() {
        return genreId;
    }

    public void setGenreId(long genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return genreId == genre.genreId &&
                Objects.equals(name, genre.name) &&
                Objects.equals(language, genre.language);
    }

    @Override
    public int hashCode() {

        return Objects.hash(genreId, name, language);
    }
}
