package com.isharipov.simplemediaapp.news.ui.news.category;

import com.isharipov.simplemediaapp.news.model.Article;
import com.isharipov.simplemediaapp.news.model.ArticleResponse;
import com.isharipov.simplemediaapp.news.model.QueryCategoryParam;
import com.isharipov.simplemediaapp.news.model.QueryParam;
import com.isharipov.simplemediaapp.news.repository.ArticleRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 12.06.2018.
 */
public class CategoryPresenter implements CategoryContract.Presenter {

    private CategoryContract.View view;
    private ArticleRepository articleRepository;

    @Inject
    CategoryPresenter(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void attachView(CategoryContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }


    // TODO: 15.06.2018 Реализовать работу с CompositeDisposable
    @Override
    public void loadArticlesFromApi(QueryParam queryParam) {
        QueryCategoryParam queryCategoryParam = (QueryCategoryParam) queryParam;
        Observable<ArticleResponse> articlesFromApi = articleRepository.getArticlesByCategoryFromApi(queryCategoryParam);
        articlesFromApi.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArticleResponse>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArticleResponse articleResponse) {
                        List<Article> articles = articleResponse.getArticles();
                        view.setData(articles);
                        for (Article article : articles) {
                            article.setCategory(queryCategoryParam.getCategory());
                        }
                        articleRepository.storeArticlesInDb(articles);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onItemsLoadComplete();
                    }

                    @Override
                    public void onComplete() {
                        view.onItemsLoadComplete();
                    }
                });
    }
}
