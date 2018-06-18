package com.isharipov.simplemediaapp.news.model;

import android.arch.persistence.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

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
}
