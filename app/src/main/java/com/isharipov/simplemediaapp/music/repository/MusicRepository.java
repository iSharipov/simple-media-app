package com.isharipov.simplemediaapp.music.repository;

import com.isharipov.simplemediaapp.music.model.artist.Artist;
import com.isharipov.simplemediaapp.music.model.artist.Artists;
import com.isharipov.simplemediaapp.music.model.artist.ArtistsResponse;
import com.isharipov.simplemediaapp.music.model.track.TracksResponse;
import com.isharipov.simplemediaapp.news.model.QueryParam;

import java.util.List;

import io.reactivex.Observable;

/**
 * 30.06.2018.
 */
public interface MusicRepository {
    Observable<ArtistsResponse> getArtistsFromApi(QueryParam queryParam);

    Observable<TracksResponse> getTracksFromApi(QueryParam queryParam);

    void storeArtistsInDb(List<Artist> artists);
}
