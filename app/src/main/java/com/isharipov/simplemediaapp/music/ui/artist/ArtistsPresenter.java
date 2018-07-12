package com.isharipov.simplemediaapp.music.ui.artist;

import com.isharipov.simplemediaapp.music.model.artist.Artist;
import com.isharipov.simplemediaapp.music.model.artist.ArtistsResponse;
import com.isharipov.simplemediaapp.music.repository.MusicRepository;
import com.isharipov.simplemediaapp.music.ui.MusicPresenter;
import com.isharipov.simplemediaapp.news.model.QueryParam;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 01.07.2018.
 */
public class ArtistsPresenter extends MusicPresenter {

    private final MusicRepository repository;

    @Inject
    ArtistsPresenter(MusicRepository repository, CompositeDisposable compositeDisposable) {
        super(compositeDisposable);
        this.repository = repository;
    }

    @Override
    public void loadFromApi(QueryParam queryParam) {
        view.showProgress();
        Observable<ArtistsResponse> artists = repository.getArtistsFromApi(queryParam);
        artists.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArtistsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ArtistsResponse artistsResponse) {
                        if (view != null) {
                            List<Artist> artists = artistsResponse.getArtists().getArtist();
                            view.setData(artists);
                            view.hideProgress();
                            repository.storeArtistsInDb(artists);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.onItemsLoadComplete();
                            view.hideProgress();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (view != null) {
                            view.onItemsLoadComplete();
                            view.hideProgress();
                        }
                    }
                });
    }
}
