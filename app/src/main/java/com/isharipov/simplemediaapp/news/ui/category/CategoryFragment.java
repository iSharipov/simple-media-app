package com.isharipov.simplemediaapp.news.ui.category;

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
import com.isharipov.simplemediaapp.news.model.QueryParam;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

/**
 * 13.06.2018.
 */
public class CategoryFragment extends DaggerFragment implements CategoryContract.View, SwipeRefreshLayout.OnRefreshListener {

    private static final String POSITION = "POSITION";
    private static final String PAGE = "PAGE";

    private int position;
    private int page = 1;
    private CategoryAdapter categoryAdapter;
    @Inject
    CategoryContract.Presenter presenter;
    @BindView(R.id.category_recycler_layout)
    RecyclerView categoryRecyclerLayout;
    @BindView(R.id.category_refresh_layout)
    SwipeRefreshLayout categoryRefreshLayout;
    @BindArray(R.array.category_query_param)
    String[] categoryQueryParam;

    @Inject
    public CategoryFragment() {

    }

    public static CategoryFragment newInstance(int position) {
        Bundle args = new Bundle();
        CategoryFragment fragment = new CategoryFragment();
        args.putInt(POSITION, position);
        fragment.setArguments(args);
        return fragment;
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
            presenter.loadArticlesFromApi(new QueryParam("ru", categoryQueryParam[position], ++page));
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
        presenter.loadArticlesFromApi(new QueryParam("ru", categoryQueryParam[position], page));
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
        presenter.loadArticlesFromApi(new QueryParam("ru", categoryQueryParam[position], page));
    }

    @Override
    public void onRefresh() {
        page = 1;
        categoryAdapter.clearArticles();
        presenter.loadArticlesFromApi(new QueryParam("ru", categoryQueryParam[position], page));
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
