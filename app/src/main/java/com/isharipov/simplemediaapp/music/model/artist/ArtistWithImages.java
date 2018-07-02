package com.isharipov.simplemediaapp.music.model.artist;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.isharipov.simplemediaapp.music.model.Image;

import java.util.List;

/**
 * 02.07.2018.
 */
public class ArtistWithImages {
    @Embedded
    private Artist artist;
    @Relation(parentColumn = "mbid", entityColumn = "mbid")
    private List<Image> images;

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
