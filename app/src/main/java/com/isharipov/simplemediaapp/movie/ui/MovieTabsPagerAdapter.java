package com.isharipov.simplemediaapp.movie.ui;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.isharipov.simplemediaapp.movie.ui.category.MovieCategoryFragment;

/**
 * 12.06.2018.
 */
public class MovieTabsPagerAdapter extends FragmentPagerAdapter {

    private final SparseArray<Fragment> registeredFragments = new SparseArray<>();
    private final String[] movieTabLabel;

    public MovieTabsPagerAdapter(FragmentManager fragmentManager, String[] movieTabLabel) {
        super(fragmentManager);
        this.movieTabLabel = movieTabLabel;
    }

    @Override
    public int getCount() {
        return movieTabLabel.length;
    }

    @Override
    public Fragment getItem(int position) {
        return MovieCategoryFragment.newInstance(position);
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

    @Override
    public CharSequence getPageTitle(int position) {
        return movieTabLabel[position];
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }
}
