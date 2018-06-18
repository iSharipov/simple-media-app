package com.isharipov.simplemediaapp.news.ui.category;

import com.isharipov.simplemediaapp.BasePresenter;
import com.isharipov.simplemediaapp.BaseView;
import com.isharipov.simplemediaapp.news.model.Article;
import com.isharipov.simplemediaapp.news.model.QueryParam;

import java.util.List;

/**
 * 12.06.2018.
 */
public interface CategoryContract {

    interface View extends BaseView<CategoryContract.Presenter> {
        void setData(List<Article> articles);

        void showContent();
    }

    interface Presenter extends BasePresenter<CategoryContract.View> {

        void loadArticles(QueryParam queryParam);
    }
}
