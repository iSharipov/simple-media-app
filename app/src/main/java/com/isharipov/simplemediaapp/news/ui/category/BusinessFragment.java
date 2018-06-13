package com.isharipov.simplemediaapp.news.ui.category;

import android.os.Bundle;

import dagger.android.support.DaggerFragment;

/**
 * 12.06.2018.
 */
public class BusinessFragment extends DaggerFragment {

    public static BusinessFragment newInstance() {
        Bundle args = new Bundle();
        BusinessFragment fragment = new BusinessFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
