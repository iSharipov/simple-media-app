package com.isharipov.simplemediaapp.news.ui.category;

import android.os.Bundle;

import dagger.android.support.DaggerFragment;

/**
 * 13.06.2018.
 */
public class CategoryFragment extends DaggerFragment {

    public static CategoryFragment newInstance(String categoryParam) {
        Bundle args = new Bundle();
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
