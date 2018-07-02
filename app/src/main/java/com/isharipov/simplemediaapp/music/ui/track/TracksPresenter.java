package com.isharipov.simplemediaapp.music.ui.track;

import com.isharipov.simplemediaapp.music.repository.MusicRepository;
import com.isharipov.simplemediaapp.music.ui.MusicPresenter;
import com.isharipov.simplemediaapp.news.model.QueryParam;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

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

    }
}
