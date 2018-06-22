package com.isharipov.simplemediaapp.news.model;

import android.arch.persistence.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

/**
 * 13.06.2018.
 */
public class Source implements Serializable {
    @SerializedName("id")
    @ColumnInfo(name = "sourceId")
    private String sourceId;
    @ColumnInfo(name = "name")
    private String name;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String id) {
        this.sourceId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Source source = (Source) o;
        return Objects.equals(sourceId, source.sourceId) &&
                Objects.equals(name, source.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sourceId, name);
    }
}
