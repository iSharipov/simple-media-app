package com.isharipov.simplemediaapp.news.model.source;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * 24.06.2018.
 */
@Entity(tableName = "sources",
        indices = @Index(
                value = {"id", "name", "description", "url", "category", "language", "country"},
                unique = true))
public class Source implements Serializable {
    @NonNull
    @PrimaryKey
    private String id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "url")
    private String url;
    @ColumnInfo(name = "category")
    private String category;
    @ColumnInfo(name = "language")
    private String language;
    @ColumnInfo(name = "country")
    private String country;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Source source = (Source) o;
        return Objects.equals(id, source.id) &&
                Objects.equals(name, source.name) &&
                Objects.equals(description, source.description) &&
                Objects.equals(url, source.url) &&
                Objects.equals(category, source.category) &&
                Objects.equals(language, source.language) &&
                Objects.equals(country, source.country);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, url, category, language, country);
    }
}
