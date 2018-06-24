package com.isharipov.simplemediaapp.news.ui.news.souce;

import android.os.Bundle;

import dagger.android.support.DaggerFragment;

/**
 * 24.06.2018.
 */
public class SourceFragment extends DaggerFragment {

    public static SourceFragment newInstance() {
        Bundle args = new Bundle();
        SourceFragment fragment = new SourceFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
