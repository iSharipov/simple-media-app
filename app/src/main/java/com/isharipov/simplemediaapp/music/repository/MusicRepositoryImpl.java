package com.isharipov.simplemediaapp.music.repository;

import com.isharipov.simplemediaapp.music.model.artist.ArtistsReponse;
import com.isharipov.simplemediaapp.music.model.track.TracksResponse;
import com.isharipov.simplemediaapp.music.repository.api.MusicApi;
import com.isharipov.simplemediaapp.music.repository.db.MusicDao;
import com.isharipov.simplemediaapp.news.model.QueryParam;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * 01.07.2018.
 */
public class MusicRepositoryImpl implements MusicRepository {

    private final MusicApi musicApi;
    private final MusicDao musicDao;

    @Inject
    public MusicRepositoryImpl(MusicApi musicApi, MusicDao musicDao) {
        this.musicApi = musicApi;
        this.musicDao = musicDao;
    }

    @Override
    public Observable<ArtistsReponse> getArtistsFromApi(QueryParam queryParam) {
        return null;
    }

    @Override
    public Observable<TracksResponse> getTracksFromApi(QueryParam queryParam) {
        return null;
    }
}
