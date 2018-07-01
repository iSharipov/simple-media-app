package com.isharipov.simplemediaapp.music.model.track;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.isharipov.simplemediaapp.music.model.Attr;

import java.util.List;

public class TracksResponse {

    @SerializedName("track")
    @Expose
    private List<Track> track;
    @SerializedName("@attr")
    @Expose
    private Attr attr;

    public List<Track> getTrack() {
        return track;
    }

    public void setTrack(List<Track> track) {
        this.track = track;
    }

    public Attr getAttr() {
        return attr;
    }

    public void setAttr(Attr attr) {
        this.attr = attr;
    }

}