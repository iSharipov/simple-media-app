package com.isharipov.simplemediaapp.music.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.isharipov.simplemediaapp.R;
import com.isharipov.simplemediaapp.music.preference.PreferenceMusicActivity;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

/**
 * 01.07.2018.
 */
public class MusicFragment extends DaggerFragment {

    @BindView(R.id.music_tabs)
    TabLayout tabLayout;
    @BindView(R.id.music_view_pager)
    ViewPager viewPager;
    @BindArray(R.array.music_tab_label)
    String[] musicTabLabel;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindString(R.string.title_music)
    String titleMusic;

    public static MusicFragment newInstance() {
        Bundle args = new Bundle();
        MusicFragment fragment = new MusicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    public MusicFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.music_fragment_music, container, false);
        ButterKnife.bind(this, root);
        initToolbar((AppCompatActivity) Objects.requireNonNull(getActivity()));
        initTabPagerAdapter();
        initTabLayout();
        return root;
    }

    @SuppressLint("RestrictedApi")
    private void initToolbar(AppCompatActivity activity) {
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setHomeButtonEnabled(false);
        activity.getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
        activity.getSupportActionBar().setTitle(titleMusic);
    }

    private void initTabLayout() {
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initTabPagerAdapter() {
        viewPager.setAdapter(new MusicTabsPagerAdapter(getChildFragmentManager(), musicTabLabel));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.music_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.music_menu:
                PreferenceMusicActivity.start(getActivity());
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
