package com.isharipov.simplemediaapp.news.repository;

import com.isharipov.simplemediaapp.BuildConfig;
import com.isharipov.simplemediaapp.news.model.Article;
import com.isharipov.simplemediaapp.news.model.ArticleResponse;
import com.isharipov.simplemediaapp.news.model.QueryParam;
import com.isharipov.simplemediaapp.news.repository.api.ArticleApi;
import com.isharipov.simplemediaapp.news.repository.db.ArticleDao;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * 13.06.2018.
 */
public class ArticleRepositoryImpl implements ArticleRepository {

    private ArticleApi articleApi;
    private ArticleDao articleDao;

    @Inject
    public ArticleRepositoryImpl(ArticleApi articleApi, ArticleDao articleDao) {
        this.articleApi = articleApi;
        this.articleDao = articleDao;
    }

    public Observable<ArticleResponse> getArticleResponse(QueryParam queryParam) {
        return Observable.concatArray(
                getArticleFromDb(queryParam),
                getArticlesFromApi(queryParam))
                .materialize()
                .filter(it -> !it.isOnError())
                .dematerialize();
    }

    private Observable<ArticleResponse> getArticlesFromApi(QueryParam param) {
        return articleApi
                .getArticles(
                        param.getCountry(),
                        param.getCategory(),
                        param.getQuery(),
                        param.getPage(),
                        BuildConfig.NEWS_API_KEY)
                .doOnNext(it -> {
                    List<Article> articles = it.getArticles();
                    for (Article article : articles) {
                        article.setCategory(param.getCategory());
                    }
                    storeArticlesInDb(articles);
                });

    }

    private Observable<ArticleResponse> getArticleFromDb(QueryParam param) {
        return articleDao.getArticles(param.getCategory())
                .filter(articles -> !articles.isEmpty())
                .map(articles -> {
                    ArticleResponse articleResponse = new ArticleResponse();
                    articleResponse.setArticles(articles);
                    return articleResponse;
                }).toObservable();
    }

    private void storeArticlesInDb(List<Article> articles) {
        Completable.fromAction(() -> articleDao.insertAll(articles))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                Timber.d("Inserted users from API in DB...");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}