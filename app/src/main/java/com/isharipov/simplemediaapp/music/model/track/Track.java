package com.isharipov.simplemediaapp.music.model.track;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.isharipov.simplemediaapp.music.model.Image;
import com.isharipov.simplemediaapp.music.model.ImageSize;
import com.isharipov.simplemediaapp.music.model.artist.Artist;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "tracks",
        indices = @Index(value = {"name", "artistImdb"}, unique = true))
public class Track implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("playcount")
    @Expose
    private String playcount;
    @SerializedName("listeners")
    @Expose
    private String listeners;
    @SerializedName("mbid")
    @Expose
    private String mbid;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("streamable")
    @Expose
    @Embedded
    private Streamable streamable;
    @SerializedName("artist")
    @Expose
    @Ignore
    private Artist artist;
    @SerializedName("image")
    @Expose
    @Ignore
    private List<Image> images;
    private String artistName;
    private String artistImdb;
    private String artistUrl;

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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

    public Streamable getStreamable() {
        return streamable;
    }

    public void setStreamable(Streamable streamable) {
        this.streamable = streamable;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Image> getImages() {
        return images;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistImdb() {
        return artistImdb;
    }

    public void setArtistImdb(String artistImdb) {
        this.artistImdb = artistImdb;
    }

    public String getArtistUrl() {
        return artistUrl;
    }

    public void setArtistUrl(String artistUrl) {
        this.artistUrl = artistUrl;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Image getImageBySize(ImageSize imageSize) {
        for (Image image : images) {
            if (imageSize.name().equalsIgnoreCase(image.getSize().toLowerCase())) {
                return image;
            }
        }
        return null;
    }

}