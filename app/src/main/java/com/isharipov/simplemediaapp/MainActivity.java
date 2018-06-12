package com.isharipov.simplemediaapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.isharipov.simplemediaapp.news.ui.NewsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String navigationPage = "NAVIGATION_PAGE";
    private int navigationId = R.id.navigation_news;
    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            navigationId = savedInstanceState.getInt(navigationPage);
        }
        initBottomNavigationView();
    }

    private void initBottomNavigationView() {
        bottomNavigationView.setSelectedItemId(navigationId);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.findViewById(navigationId).performClick();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (navigationId = item.getItemId()) {
            case R.id.navigation_music:
                return true;
            case R.id.navigation_news:
                openFragment(NewsFragment.newInstance());
                return true;
            case R.id.navigation_movies:
                return true;
        }
        return false;
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle bundle = new Bundle();
        bundle.putInt(navigationPage, navigationId);
    }
}
