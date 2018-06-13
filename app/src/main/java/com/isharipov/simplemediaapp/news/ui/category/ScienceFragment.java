package com.isharipov.simplemediaapp.news.ui.category;

import android.os.Bundle;

import dagger.android.support.DaggerFragment;

/**
 * 12.06.2018.
 */
public class ScienceFragment extends DaggerFragment {
    public static ScienceFragment newInstance() {
        Bundle args = new Bundle();
        ScienceFragment fragment = new ScienceFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
