package com.isharipov.simplemediaapp.news.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.isharipov.simplemediaapp.PagerContract;
import com.isharipov.simplemediaapp.news.ui.news.category.NewsCategoryFragment;
import com.isharipov.simplemediaapp.news.ui.news.everything.EverythingFragment;

/**
 * 12.06.2018.
 */
public class NewsTabsPagerAdapter extends PagerContract {

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
        return NewsCategoryFragment.newInstance(position);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return categoryTabLabel[position];
    }
}
