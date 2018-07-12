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
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 22.06.2018.
 */
public class EverythingPresenter implements EverythingContract.Presenter {

    private EverythingContract.View view;
    private final NewsRepository newsRepository;
    private final CompositeDisposable compositeDisposable;

    @Inject
    EverythingPresenter(NewsRepository newsRepository, CompositeDisposable compositeDisposable) {
        this.newsRepository = newsRepository;
        this.compositeDisposable = compositeDisposable;
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
    public void unsubscribe() {
        compositeDisposable.dispose();
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
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ArticleResponse articleResponse) {
                        if (view != null) {
                            List<Article> articles = articleResponse.getArticles();
                            view.setData(articles);
                            view.hideProgress();
                            view.setMoreLoaded(false);
                            for (Article article : articles) {
                                article.setCategory("everything");
                            }
                            newsRepository.storeArticlesInDb(articles);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.onItemsLoadComplete();
                            view.hideProgress();
                            view.setMoreLoaded(false);
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (view != null) {
                            view.onItemsLoadComplete();
                            view.hideProgress();
                            view.setMoreLoaded(false);
                        }
                    }
                });
    }

    @Override
    public void loadSources(QueryParam queryParam) {

    }
}
