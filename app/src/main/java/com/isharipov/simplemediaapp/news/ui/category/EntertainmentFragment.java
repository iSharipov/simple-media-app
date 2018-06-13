package com.isharipov.simplemediaapp.news.ui.category;

import android.os.Bundle;

import dagger.android.support.DaggerFragment;

/**
 * 12.06.2018.
 */
public class EntertainmentFragment extends DaggerFragment {
    public static EntertainmentFragment newInstance() {
        Bundle args = new Bundle();
        EntertainmentFragment fragment = new EntertainmentFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
