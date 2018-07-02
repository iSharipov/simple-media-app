package com.isharipov.simplemediaapp.music.model.artist;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.isharipov.simplemediaapp.music.model.Image;
import com.isharipov.simplemediaapp.music.model.Size;

import java.io.Serializable;
import java.util.List;

/**
 * 30.06.2018.
 */
@Entity(tableName = "artists",
        indices = @Index(value = {"mbid"}, unique = true))
public class Artist implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    private String name;
    @SerializedName("playcount")
    @Expose
    @ColumnInfo(name = "playcount")
    private String playcount;
    @SerializedName("listeners")
    @Expose
    @ColumnInfo(name = "listeners")
    private String listeners;
    @SerializedName("mbid")
    @Expose
    private String mbid;
    @SerializedName("url")
    @Expose
    @ColumnInfo(name = "url")
    private String url;
    @SerializedName("streamable")
    @Expose
    @ColumnInfo(name = "streamable")
    private String streamable;
    @SerializedName("image")
    @Expose
    @Ignore
    private List<Image> images;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaycount() {
        return playcount;
    }

    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }

    public String getListeners() {
        return listeners;
    }

    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStreamable() {
        return streamable;
    }

    public void setStreamable(String streamable) {
        this.streamable = streamable;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Image getImageBySize(Size size) {
        for (Image image : images) {
            if (size.name().equalsIgnoreCase(image.getSize().toLowerCase())) {
                return image;
            }
        }
        return null;
    }
}
