package com.isharipov.simplemediaapp.music.ui;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

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
    public CharSequence getPageTitle(int position) {
        return musicTabLabel[position];
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

    @Override
    public int getCount() {
        return musicTabLabel.length;
    }
}
