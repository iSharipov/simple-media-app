package com.isharipov.simplemediaapp.news.ui.category;

import android.os.Bundle;

import dagger.android.support.DaggerFragment;

/**
 * 12.06.2018.
 */
public class GeneralFragment extends DaggerFragment {
    public static GeneralFragment newInstance() {
        Bundle args = new Bundle();
        GeneralFragment fragment = new GeneralFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
