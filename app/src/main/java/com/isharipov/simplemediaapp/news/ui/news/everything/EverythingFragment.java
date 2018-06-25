package com.isharipov.simplemediaapp.news.ui.news.everything;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.isharipov.simplemediaapp.R;
import com.isharipov.simplemediaapp.news.model.Article;
import com.isharipov.simplemediaapp.news.model.QueryEverythingParam;
import com.isharipov.simplemediaapp.news.ui.news.category.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

/**
 * 22.06.2018.
 */
public class EverythingFragment extends DaggerFragment implements EverythingContract.View, SwipeRefreshLayout.OnRefreshListener {
    private static final String POSITION = "POSITION";
    private static final String PAGE = "PAGE";

    private int position;
    private int page = 1;
    private CategoryAdapter categoryAdapter;
    @Inject
    EverythingContract.Presenter presenter;
    @BindView(R.id.category_recycler_layout)
    RecyclerView categoryRecyclerLayout;
    @BindView(R.id.category_refresh_layout)
    SwipeRefreshLayout categoryRefreshLayout;
    @BindArray(R.array.query_param)
    String[] categoryQueryParam;

    public static EverythingFragment newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        EverythingFragment fragment = new EverythingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    public EverythingFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null && !args.isEmpty()) {
            position = args.getInt(POSITION);
        }
        if (savedInstanceState != null && !savedInstanceState.isEmpty()) {
            position = savedInstanceState.getInt(POSITION);
            page = savedInstanceState.getInt(PAGE);
        }
        categoryAdapter = new CategoryAdapter(new ArrayList<>(0));
        categoryAdapter.setOnLoadMoreListener(() -> {
            presenter.loadArticlesFromApi(new QueryEverythingParam("ru", ++page));
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, root);
        setupUI();
        return root;
    }

    private void setupUI() {
        categoryRecyclerLayout.setLayoutManager(new GridLayoutManager(getContext(), 1));
        categoryRecyclerLayout.setAdapter(categoryAdapter);
        categoryRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.loadArticlesFromApi(new QueryEverythingParam("ru", page));
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PAGE, page);
        outState.putInt(POSITION, position);
    }

    @Override
    public void setData(List<Article> articles) {
        categoryAdapter.appendData(articles);
    }

    @Override
    public void showContent() {
        presenter.loadArticlesFromApi(new QueryEverythingParam("ru", page));
    }

    @Override
    public void onRefresh() {
        page = 1;
        categoryAdapter.clearArticles();
        presenter.loadArticlesFromApi(new QueryEverythingParam("ru", page));
    }

    @Override
    public void onItemsLoadComplete() {
        categoryRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setMoreLoaded(boolean moreLoaded) {
        categoryAdapter.setLoaded(false);
    }
}
