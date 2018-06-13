package com.isharipov.simplemediaapp.news.ui.category;

import android.os.Bundle;

import dagger.android.support.DaggerFragment;

/**
 * 12.06.2018.
 */
public class TechnologyFragment extends DaggerFragment {
    public static TechnologyFragment newInstance() {
        Bundle args = new Bundle();
        TechnologyFragment fragment = new TechnologyFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
