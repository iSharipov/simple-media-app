package com.isharipov.simplemediaapp.news.ui.category;

import android.os.Bundle;

import dagger.android.support.DaggerFragment;

/**
 * 12.06.2018.
 */
public class HealthFragment extends DaggerFragment {
    public static HealthFragment newInstance() {
        Bundle args = new Bundle();
        HealthFragment fragment = new HealthFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
