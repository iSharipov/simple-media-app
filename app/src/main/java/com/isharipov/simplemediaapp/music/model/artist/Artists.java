package com.isharipov.simplemediaapp.music.model.artist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.isharipov.simplemediaapp.music.model.Attr;

import java.io.Serializable;
import java.util.List;

/**
 * 30.06.2018.
 */
public class Artists implements Serializable {

    @SerializedName("artist")
    @Expose
    private List<Artist> artist;
    @SerializedName("@attr")
    @Expose
    private Attr attr;

    public List<Artist> getArtist() {
        return artist;
    }

    public void setArtist(List<Artist> artist) {
        this.artist = artist;
    }

    public Attr getAttr() {
        return attr;
    }

    public void setAttr(Attr attr) {
        this.attr = attr;
    }

}
