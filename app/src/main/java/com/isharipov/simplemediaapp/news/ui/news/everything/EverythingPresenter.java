package com.isharipov.simplemediaapp.news.ui.news.everything;

import com.isharipov.simplemediaapp.news.model.Article;
import com.isharipov.simplemediaapp.news.model.ArticleResponse;
import com.isharipov.simplemediaapp.news.model.QueryEverythingParam;
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
 * 22.06.2018.
 */
public class EverythingPresenter implements EverythingContract.Presenter {

    private EverythingContract.View view;
    private final ArticleRepository articleRepository;

    @Inject
    EverythingPresenter(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void attachView(EverythingContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void loadArticlesFromApi(QueryParam queryParam) {
        QueryEverythingParam queryEverythingParam = (QueryEverythingParam) queryParam;
        Observable<ArticleResponse> articlesFromApi = articleRepository.getArticlesEverythingFromApi(queryEverythingParam);
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
