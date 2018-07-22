package com.isharipov.simplemediaapp.news.ui;

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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.isharipov.simplemediaapp.ui.PagerContract;
import com.isharipov.simplemediaapp.R;
import com.isharipov.simplemediaapp.news.preference.PreferenceNewsActivity;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

/**
 * 12.06.2018.
 */
public class NewsFragment extends DaggerFragment {

    @BindView(R.id.news_category_tabs)
    TabLayout tabLayout;
    @BindView(R.id.news_view_pager)
    ViewPager viewPager;
    @BindArray(R.array.news_tab_label)
    String[] categoryTabLabel;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindString(R.string.title_news)
    String titleNews;
    @BindView(R.id.adView)
    AdView adView;
    PagerContract pagerContract;

    public static NewsFragment newInstance() {
        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    public NewsFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.news_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.news_menu:
                PreferenceNewsActivity.start(getActivity());
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.news_fragment_news, container, false);
        ButterKnife.bind(this, root);
        initToolbar((AppCompatActivity) Objects.requireNonNull(getActivity()));
        initTabPagerAdapter();
        initTabLayout();
        initAdv();
        return root;
    }

    private void initAdv() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);
    }

    @SuppressLint("RestrictedApi")
    private void initToolbar(AppCompatActivity activity) {
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setHomeButtonEnabled(false);
        activity.getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
        activity.getSupportActionBar().setTitle(titleNews);
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
        pagerContract = new NewsTabsPagerAdapter(getChildFragmentManager(), categoryTabLabel);
        viewPager.setAdapter(pagerContract);
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
}
