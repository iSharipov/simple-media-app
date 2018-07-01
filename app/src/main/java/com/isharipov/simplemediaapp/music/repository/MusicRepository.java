package com.isharipov.simplemediaapp.music.repository;

import com.isharipov.simplemediaapp.music.model.artist.ArtistsReponse;
import com.isharipov.simplemediaapp.music.model.track.TracksResponse;
import com.isharipov.simplemediaapp.news.model.QueryParam;

import io.reactivex.Observable;

/**
 * 30.06.2018.
 */
public interface MusicRepository {
    Observable<ArtistsReponse> getArtistsFromApi(QueryParam queryParam);

    Observable<TracksResponse> getTracksFromApi(QueryParam queryParam);
}
