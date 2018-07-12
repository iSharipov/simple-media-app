package com.isharipov.simplemediaapp.movie.preference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.View;
import android.widget.ListView;

import com.isharipov.simplemediaapp.R;

import javax.inject.Inject;

/**
 * 25.06.2018.
 */
public class PreferenceMovieFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Inject
    public PreferenceMovieFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.movie_prefs);
        Preference regionPreference = findPreference(getString(R.string.pref_movie_region_key));
        if (regionPreference instanceof ListPreference) {
            ListPreference listPref = (ListPreference) regionPreference;
            regionPreference.setSummary(listPref.getEntry());
        }
        Preference languagePreference = findPreference(getString(R.string.pref_movie_language_key));
        if (languagePreference instanceof ListPreference) {
            ListPreference listPref = (ListPreference) languagePreference;
            languagePreference.setSummary(listPref.getEntry());
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ListView root = view.findViewById(android.R.id.list);
        if (root != null) {
            ViewCompat.setNestedScrollingEnabled(root, true);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences preferences, String key) {
        if (key.equals(getString(R.string.pref_movie_region_key))) {
            Preference preference = findPreference(key);
            if (preference instanceof ListPreference) {
                ListPreference listPref = (ListPreference) preference;
                preference.setSummary(listPref.getEntry());
            }
        }
        if (key.equals(getString(R.string.pref_movie_language_key))) {
            Preference preference = findPreference(key);
            if (preference instanceof ListPreference) {
                ListPreference listPref = (ListPreference) preference;
                preference.setSummary(listPref.getEntry());
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

    @Override
    public void onStop() {
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onStop();
    }
}
