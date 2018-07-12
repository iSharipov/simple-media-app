package com.isharipov.simplemediaapp.movie.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 11.07.2018.
 */
public class GenreResponse implements Serializable {
    private List<Genre> genres;

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreResponse that = (GenreResponse) o;
        return Objects.equals(genres, that.genres);
    }

    @Override
    public int hashCode() {

        return Objects.hash(genres);
    }
}
