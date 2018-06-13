package com.isharipov.simplemediaapp.news.ui.category;

import android.os.Bundle;

import dagger.android.support.DaggerFragment;

/**
 * 12.06.2018.
 */
public class SportsFragment extends DaggerFragment {
    public static SportsFragment newInstance() {
        Bundle args = new Bundle();
        SportsFragment fragment = new SportsFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
