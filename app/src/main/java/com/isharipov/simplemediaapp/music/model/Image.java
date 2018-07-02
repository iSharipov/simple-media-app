package com.isharipov.simplemediaapp.music.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.isharipov.simplemediaapp.music.model.artist.Artist;

import java.io.Serializable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * 30.06.2018.
 */
@Entity(tableName = "images",
        indices = @Index(value = {"text", "size", "mbid"}, unique = true),
        foreignKeys = @ForeignKey(
                entity = Artist.class,
                parentColumns = "mbid",
                childColumns = "mbid",
                onDelete = CASCADE))
public class Image implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @SerializedName("#text")
    @Expose
    @ColumnInfo(name = "text")
    private String text;
    @SerializedName("size")
    @Expose
    @ColumnInfo(name = "size")
    private String size;
    @ColumnInfo(name = "mbid")
    private String mbid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }
}
