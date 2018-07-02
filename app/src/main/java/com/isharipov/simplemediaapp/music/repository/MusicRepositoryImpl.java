package com.isharipov.simplemediaapp.music.repository;

import com.isharipov.simplemediaapp.BuildConfig;
import com.isharipov.simplemediaapp.music.model.artist.Artist;
import com.isharipov.simplemediaapp.music.model.artist.ArtistsResponse;
import com.isharipov.simplemediaapp.music.model.track.TracksResponse;
import com.isharipov.simplemediaapp.music.repository.api.MusicApi;
import com.isharipov.simplemediaapp.music.repository.db.MusicDao;
import com.isharipov.simplemediaapp.news.model.QueryParam;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

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
    public Observable<ArtistsResponse> getArtistsFromApi(QueryParam queryParam) {
        return musicApi
                .getTopArtists(
                        queryParam.getPageSize(),
                        queryParam.getPage(),
                        BuildConfig.LASTFM_API_KEY,
                        "json");
    }

    @Override
    public Observable<TracksResponse> getTracksFromApi(QueryParam queryParam) {
        return null;
    }

    @Override
    public void storeArtistsInDb(List<Artist> artists) {
        Completable.fromAction(() -> musicDao.insertAllArtists(artists))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                Timber.d("Inserted artists from API to DB...");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
