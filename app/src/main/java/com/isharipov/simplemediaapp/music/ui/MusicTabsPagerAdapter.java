package com.isharipov.simplemediaapp.music.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.isharipov.simplemediaapp.music.ui.artist.ArtistsFragment;
import com.isharipov.simplemediaapp.music.ui.track.TracksFragment;

/**
 * 01.07.2018.
 */
public class MusicTabsPagerAdapter extends FragmentPagerAdapter {

    private final SparseArray<Fragment> registeredFragments = new SparseArray<>();
    private final String[] musicTabLabel;

    public MusicTabsPagerAdapter(FragmentManager fragmentManager, String[] musicTabLabel) {
        super(fragmentManager);
        this.musicTabLabel = musicTabLabel;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return ArtistsFragment.newInstance(position);
        } else if (position == 1) {
            return TracksFragment.newInstance(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return musicTabLabel.length;
    }
}
