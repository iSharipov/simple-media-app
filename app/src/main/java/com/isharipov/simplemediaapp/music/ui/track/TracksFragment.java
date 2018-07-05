package com.isharipov.simplemediaapp.music.ui.track;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.isharipov.simplemediaapp.music.model.track.Track;
import com.isharipov.simplemediaapp.music.ui.MusicContract;
import com.isharipov.simplemediaapp.news.model.QueryParam;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

/**
 * 01.07.2018.
 */
public class TracksFragment extends DaggerFragment implements MusicContract.View<Track>, SwipeRefreshLayout.OnRefreshListener {

    private static final String LIMIT = "LIMIT";
    private int limit;
    private TracksAdapter tracksAdapter;
    @BindView(R.id.tracks_recycler_layout)
    RecyclerView tracksRecyclerLayout;
    @BindView(R.id.tracks_refresh_layout)
    SwipeRefreshLayout tracksRefreshLayout;
    @BindView(R.id.progressBarHolder)
    FrameLayout progressBarHolderLayout;
    @BindString(R.string.pref_music_key)
    String prefMusicKey;
    @Inject
    TracksPresenter presenter;
    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;


    public static TracksFragment newInstance(int position) {
        Bundle args = new Bundle();
        TracksFragment fragment = new TracksFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    public TracksFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && !savedInstanceState.isEmpty()) {
            limit = savedInstanceState.getInt(LIMIT);
        }
        tracksAdapter = new TracksAdapter(new ArrayList<>(0));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.music_tracks_fragment, container, false);
        ButterKnife.bind(this, root);
        setupUI();
        return root;
    }

    private void setupUI() {
        tracksRecyclerLayout.setLayoutManager(new LinearLayoutManager(getContext()));
        tracksRecyclerLayout.setAdapter(tracksAdapter);
        tracksRefreshLayout.setOnRefreshListener(this);
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
    public void setData(List<Track> tracks) {
        tracksAdapter.appendData(tracks);
    }

    @Override
    public void onRefresh() {
        tracksAdapter.clearArticles();
        QueryParam queryParam = new QueryParam(1);
        queryParam.setPageSize(limit);
        presenter.loadFromApi(queryParam);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
        initLimitParam();
        QueryParam queryParam = new QueryParam(1);
        queryParam.setPageSize(limit);
        tracksAdapter.clearArticles();

        presenter.loadFromApi(queryParam);
    }

    private void initLimitParam() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        limit = Integer.valueOf(preferences.getString(prefMusicKey, "10"));
    }
}
