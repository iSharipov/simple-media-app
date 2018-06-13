package com.isharipov.simplemediaapp.news.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.isharipov.simplemediaapp.R;
import com.isharipov.simplemediaapp.news.ui.category.BusinessFragment;
import com.isharipov.simplemediaapp.news.ui.category.EntertainmentFragment;
import com.isharipov.simplemediaapp.news.ui.category.GeneralFragment;
import com.isharipov.simplemediaapp.news.ui.category.HealthFragment;
import com.isharipov.simplemediaapp.news.ui.category.ScienceFragment;
import com.isharipov.simplemediaapp.news.ui.category.SportsFragment;
import com.isharipov.simplemediaapp.news.ui.category.TabsPagerAdapter;
import com.isharipov.simplemediaapp.news.ui.category.TechnologyFragment;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

/**
 * 12.06.2018.
 */
public class NewsFragment extends DaggerFragment implements NewsContract.View {

    @BindString(R.string.business)
    String business;
    @BindString(R.string.entertainment)
    String entertainment;
    @BindString(R.string.general)
    String general;
    @BindString(R.string.health)
    String health;
    @BindString(R.string.science)
    String science;
    @BindString(R.string.sports)
    String sports;
    @BindString(R.string.technology)
    String technology;
    @BindView(R.id.news_category_tabs)
    TabLayout tabLayout;
    @BindView(R.id.news_view_pager)
    ViewPager viewPager;

    public static NewsFragment newInstance() {
        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    NewsContract.Presenter presenter;

    @Inject
    public NewsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, root);
        initTabPagerAdapter();
        intitTabLayout();
        return root;
    }

    private void intitTabLayout() {
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

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.detachView();
    }

    private void initTabPagerAdapter() {
        TabsPagerAdapter tabsPagerAdapter = new TabsPagerAdapter(getChildFragmentManager());
        tabsPagerAdapter.addFragment(BusinessFragment.newInstance(), business);
        tabsPagerAdapter.addFragment(EntertainmentFragment.newInstance(), entertainment);
        tabsPagerAdapter.addFragment(GeneralFragment.newInstance(), general);
        tabsPagerAdapter.addFragment(HealthFragment.newInstance(), health);
        tabsPagerAdapter.addFragment(ScienceFragment.newInstance(), science);
        tabsPagerAdapter.addFragment(SportsFragment.newInstance(), sports);
        tabsPagerAdapter.addFragment(TechnologyFragment.newInstance(), technology);
        viewPager.setAdapter(tabsPagerAdapter);
    }
}
