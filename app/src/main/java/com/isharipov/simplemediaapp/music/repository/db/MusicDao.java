package com.isharipov.simplemediaapp.music.repository.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.isharipov.simplemediaapp.music.model.Image;
import com.isharipov.simplemediaapp.music.model.artist.Artist;
import com.isharipov.simplemediaapp.music.model.artist.ArtistWithImages;

import java.util.ArrayList;
import java.util.List;

/**
 * 01.07.2018.
 */
@Dao
public abstract class MusicDao {

    public void insertAllArtists(List<Artist> artists) {
        _insertAllArtists(artists);
        for (Artist artist : artists) {
            if (artist.getImages() != null) {
                insertImagesForArtist(artist, artist.getImages());
            }
        }
    }

    private void insertImagesForArtist(Artist artist, List<Image> images) {
        for (Image image : images) {
            image.setMbid(artist.getMbid());
        }
        _insertAllImages(images);
    }

    public List<Artist> getArtistsWithImagesEagerlyLoaded() {
        List<ArtistWithImages> artistsAndImages = _loadArtistsWithImages();
        List<Artist> artists = new ArrayList<>(artistsAndImages.size());
        for (ArtistWithImages artistWithImages : artistsAndImages) {
            artistWithImages.getArtist().setImages(artistWithImages.getImages());
            artists.add(artistWithImages.getArtist());
        }
        return artists;
    }

    @Insert
    abstract void _insertAllImages(List<Image> images);

    @Insert
    abstract void _insertAllArtists(List<Artist> artists);

    @Transaction
    @Query("SELECT * FROM artists")
    abstract List<ArtistWithImages> _loadArtistsWithImages();

}
