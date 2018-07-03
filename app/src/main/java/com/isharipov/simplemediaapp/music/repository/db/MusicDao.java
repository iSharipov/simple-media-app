package com.isharipov.simplemediaapp.music.repository.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.isharipov.simplemediaapp.music.model.Image;
import com.isharipov.simplemediaapp.music.model.artist.Artist;
import com.isharipov.simplemediaapp.music.model.artist.ArtistWithImages;
import com.isharipov.simplemediaapp.music.model.track.Track;
import com.isharipov.simplemediaapp.music.model.track.TrackWithArtistAndImages;

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

    public void insertAllTracks(List<Track> tracks) {
        for (Track track : tracks) {
            Artist artist = track.getArtist();
            track.setArtistName(artist.getName());
            track.setArtistImdb(artist.getMbid());
            track.setArtistUrl(artist.getUrl());
        }
        _insertAllTracks(tracks);
        for (Track track : tracks) {
            if (track.getImages() != null) {
                insertImagesForTracks(track, track.getImages());
            }
        }
    }

    // TODO: 03.07.2018 В TRACK нет MBID, придумать другой идентификатор
    private void insertImagesForTracks(Track track, List<Image> images) {
        for (Image image : images) {
            image.setMbid(track.getMbid());
        }
        _insertAllImages(images);
    }

    @Insert
    abstract void _insertAllArtists(List<Artist> artists);

    @Insert
    abstract void _insertAllTracks(List<Track> tracks);

    @Insert
    abstract void _insertAllImages(List<Image> images);

    public List<Artist> getArtistsWithImagesEagerlyLoaded() {
        List<ArtistWithImages> artistsAndImages = _loadArtistsWithImages();
        List<Artist> artists = new ArrayList<>(artistsAndImages.size());
        for (ArtistWithImages artistWithImages : artistsAndImages) {
            artistWithImages.getArtist().setImages(artistWithImages.getImages());
            artists.add(artistWithImages.getArtist());
        }
        return artists;
    }

    @Transaction
    @Query("SELECT * FROM artists")
    abstract List<ArtistWithImages> _loadArtistsWithImages();

    public List<Track> getTracksWithImagesEagerlyLoaded() {
        List<TrackWithArtistAndImages> tracksWithImages = _loadTracksWithArtistAndImages();
        List<Track> tracks = new ArrayList<>(tracksWithImages.size());
        for (TrackWithArtistAndImages trackWithArtistAndImages : tracksWithImages) {
            trackWithArtistAndImages.getTrack().setImages(trackWithArtistAndImages.getImages());
            tracks.add(trackWithArtistAndImages.getTrack());
        }
        return tracks;
    }

    @Transaction
    @Query("SELECT * FROM tracks")
    abstract List<TrackWithArtistAndImages> _loadTracksWithArtistAndImages();

}
