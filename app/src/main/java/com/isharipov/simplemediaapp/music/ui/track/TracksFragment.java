package com.isharipov.simplemediaapp.music.ui.track;

import android.os.Bundle;

import com.isharipov.simplemediaapp.music.model.track.Track;
import com.isharipov.simplemediaapp.music.ui.MusicContract;

import java.util.List;

import dagger.android.support.DaggerFragment;

/**
 * 01.07.2018.
 */
public class TracksFragment extends DaggerFragment implements MusicContract.View<Track> {

    public static TracksFragment newInstance(int position) {
        Bundle args = new Bundle();
        TracksFragment fragment = new TracksFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showContent() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onItemsLoadComplete() {

    }

    @Override
    public void setMoreLoaded(boolean moreLoaded) {

    }

    @Override
    public void setData(List<Track> tracks) {

    }
}
