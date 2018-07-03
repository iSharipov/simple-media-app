package com.isharipov.simplemediaapp.music.ui.artist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;

import com.isharipov.simplemediaapp.R;
import com.isharipov.simplemediaapp.music.model.artist.Artist;
import com.isharipov.simplemediaapp.music.ui.MusicContract;
import com.isharipov.simplemediaapp.news.model.QueryParam;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

/**
 * 01.07.2018.
 */
public class ArtistsFragment extends DaggerFragment implements MusicContract.View<Artist>, SwipeRefreshLayout.OnRefreshListener {

    private static final String LIMIT = "LIMIT";
    private int limit = 5;
    private ArtistsAdapter artistsAdapter;
    @BindView(R.id.artists_recycler_layout)
    RecyclerView artistsRecyclerLayout;
    @BindView(R.id.artists_refresh_layout)
    SwipeRefreshLayout artistsRefreshLayout;
    @BindView(R.id.progressBarHolder)
    FrameLayout progressBarHolderLayout;
    @Inject
    ArtistsPresenter presenter;
    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;

    @Inject
    public ArtistsFragment() {

    }

    public static ArtistsFragment newInstance(int position) {
        Bundle args = new Bundle();
        ArtistsFragment fragment = new ArtistsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && !savedInstanceState.isEmpty()) {
            limit = savedInstanceState.getInt(LIMIT);
        }
        artistsAdapter = new ArtistsAdapter(new ArrayList<>(0));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.music_artists_fragment, container, false);
        ButterKnife.bind(this, root);
        setupUI();
        return root;
    }

    private void setupUI() {
        artistsRecyclerLayout.setLayoutManager(new LinearLayoutManager(getContext()));
        artistsRecyclerLayout.setAdapter(artistsAdapter);
        artistsRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void showContent() {

    }

    @Override
    public void showProgress() {
        inAnimation = new AlphaAnimation(0f, 1f);
        inAnimation.setDuration(200);
        progressBarHolderLayout.setAnimation(inAnimation);
        progressBarHolderLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        outAnimation = new AlphaAnimation(1f, 0f);
        outAnimation.setDuration(200);
        progressBarHolderLayout.setAnimation(outAnimation);
        progressBarHolderLayout.setVisibility(View.GONE);
    }

    @Override
    public void onItemsLoadComplete() {

    }

    @Override
    public void setData(List<Artist> artists) {
        artistsAdapter.appendData(artists);
    }

    @Override
    public void onRefresh() {
        artistsAdapter.clearArticles();
        QueryParam queryParam = new QueryParam(1);
        queryParam.setPageSize(limit);
        presenter.loadFromApi(queryParam);
    }

    @Override
    public void onResume() {
        super.onResume();
        artistsAdapter.clearArticles();
        presenter.attachView(this);
        QueryParam queryParam = new QueryParam(1);
        queryParam.setPageSize(limit);
        presenter.loadFromApi(queryParam);
    }
}
