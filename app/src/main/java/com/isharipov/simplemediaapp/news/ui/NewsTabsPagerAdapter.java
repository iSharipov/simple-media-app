package com.isharipov.simplemediaapp.news.ui;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.isharipov.simplemediaapp.news.ui.news.category.CategoryFragment;
import com.isharipov.simplemediaapp.news.ui.news.everything.EverythingFragment;

/**
 * 12.06.2018.
 */
public class NewsTabsPagerAdapter extends FragmentPagerAdapter {

    private final SparseArray<Fragment> registeredFragments = new SparseArray<>();
    private final String[] categoryTabLabel;

    public NewsTabsPagerAdapter(FragmentManager fragmentManager, String[] categoryTabLabel) {
        super(fragmentManager);
        this.categoryTabLabel = categoryTabLabel;
    }

    @Override
    public int getCount() {
        return categoryTabLabel.length;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return EverythingFragment.newInstance(position);
        }
        return CategoryFragment.newInstance(position);
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
        return categoryTabLabel[position];
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }
}
