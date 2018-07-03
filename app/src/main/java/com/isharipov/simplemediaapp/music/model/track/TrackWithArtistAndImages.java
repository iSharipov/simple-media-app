package com.isharipov.simplemediaapp.music.model.track;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.isharipov.simplemediaapp.music.model.Image;

import java.util.List;

/**
 * 02.07.2018.
 */
public class TrackWithArtistAndImages {
    @Embedded
    private Track track;
    @Relation(parentColumn = "mbid", entityColumn = "mbid")
    private List<Image> images;

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
