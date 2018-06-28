package com.isharipov.simplemediaapp.news.ui.news.everything;

import com.isharipov.simplemediaapp.news.model.Article;
import com.isharipov.simplemediaapp.news.model.ArticleResponse;
import com.isharipov.simplemediaapp.news.model.QueryEverythingParam;
import com.isharipov.simplemediaapp.news.model.QueryParam;
import com.isharipov.simplemediaapp.news.repository.NewsRepository;

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
    private final NewsRepository newsRepository;

    @Inject
    EverythingPresenter(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
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
        view.showProgress();
        QueryEverythingParam queryEverythingParam = (QueryEverythingParam) queryParam;
        Observable<ArticleResponse> articlesFromApi = newsRepository.getArticlesEverythingFromApi(queryEverythingParam);
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
                        view.hideProgress();
                        newsRepository.storeArticlesInDb(articles);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onItemsLoadComplete();
                        view.hideProgress();
                    }

                    @Override
                    public void onComplete() {
                        view.onItemsLoadComplete();
                        view.hideProgress();
                    }
                });
    }

    @Override
    public void loadSources(QueryParam queryParam) {

    }
}
