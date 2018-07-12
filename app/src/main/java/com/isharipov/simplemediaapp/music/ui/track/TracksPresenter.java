package com.isharipov.simplemediaapp.music.ui.track;

import com.isharipov.simplemediaapp.music.model.track.Track;
import com.isharipov.simplemediaapp.music.model.track.TracksResponse;
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
public class TracksPresenter extends MusicPresenter {

    private final MusicRepository repository;

    @Inject
    TracksPresenter(MusicRepository repository, CompositeDisposable compositeDisposable) {
        super(compositeDisposable);
        this.repository = repository;
    }

    @Override
    public void loadFromApi(QueryParam queryParam) {
        view.showProgress();
        Observable<TracksResponse> tracks = repository.getTracksFromApi(queryParam);
        tracks.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TracksResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(TracksResponse tracksResponse) {
                        if (view != null) {
                            List<Track> track = tracksResponse.getTracks().getTrack();
                            view.setData(track);
                            view.hideProgress();
                            repository.storeTracksInDb(track);
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
