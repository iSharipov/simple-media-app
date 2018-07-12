package com.isharipov.simplemediaapp.news.repository;

import android.text.TextUtils;

import com.isharipov.simplemediaapp.BuildConfig;
import com.isharipov.simplemediaapp.news.model.Article;
import com.isharipov.simplemediaapp.news.model.ArticleResponse;
import com.isharipov.simplemediaapp.news.model.QueryCategoryParam;
import com.isharipov.simplemediaapp.news.model.QueryEverythingParam;
import com.isharipov.simplemediaapp.news.model.source.Source;
import com.isharipov.simplemediaapp.news.repository.api.NewsApi;
import com.isharipov.simplemediaapp.news.repository.db.NewsDao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public class NewsRepositoryImpl implements NewsRepository {

    private final NewsApi newsApi;
    private final NewsDao newsDao;

    @Inject
    public NewsRepositoryImpl(NewsApi newsApi, NewsDao newsDao) {
        this.newsApi = newsApi;
        this.newsDao = newsDao;
    }

    @Override
    public Observable<ArticleResponse> getArticlesByCategoryFromApi(QueryCategoryParam param) {
        return newsApi
                .getArticlesByCategory(
                        param.getCountry(),
                        param.getCategory(),
                        param.getQuery(),
                        param.getPage(),
                        BuildConfig.NEWS_API_KEY,
                        param.getPageSize());

    }

    @Override
    public Observable<ArticleResponse> getArticlesEverythingFromApi(QueryEverythingParam param) {
        return newsApi.getSources(param.getQuery(),
                param.getCountry(),
                BuildConfig.NEWS_API_KEY)
                .flatMap(sourceResponse -> {
                    Set<String> sourcesQuery = new HashSet<>();
                    List<Source> sources = sourceResponse.getSources();
                    storeSourcesInDb(sources);
                    for (Source source : sources) {
                        sourcesQuery.add(source.getName());
                    }
                    return newsApi.getEverythingArticles(
                            param.getQuery(),
                            TextUtils.join(",", sourcesQuery),
                            param.getPage(),
                            BuildConfig.NEWS_API_KEY,
                            param.getPageSize());
                });
    }

    @Override
    public Observable<ArticleResponse> getArticlesByCategoryFromDb(QueryCategoryParam param) {
        return newsDao.getArticlesByCategory(param.getCategory())
                .filter(articles -> !articles.isEmpty())
                .map(articles -> {
                    ArticleResponse articleResponse = new ArticleResponse();
                    articleResponse.setArticles(articles);
                    return articleResponse;
                }).toObservable();
    }

    @Override
    public void storeArticlesInDb(List<Article> articles) {
        Completable.fromAction(() -> newsDao.insertArticles(articles))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                Timber.d("Inserted articles from API to DB...");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void storeSourcesInDb(List<Source> sources) {
        Completable.fromAction(() -> newsDao.insertSources(sources))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                Timber.d("Inserted sources from API to DB...");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}