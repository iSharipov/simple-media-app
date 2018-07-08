package com.isharipov.simplemediaapp.news.ui.news.category;

import com.isharipov.simplemediaapp.news.model.Article;
import com.isharipov.simplemediaapp.news.model.ArticleResponse;
import com.isharipov.simplemediaapp.news.model.QueryCategoryParam;
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
 * 12.06.2018.
 */
public class NewsCategoryPresenter implements CategoryContract.Presenter {

    private CategoryContract.View view;
    private final NewsRepository newsRepository;
    private final CompositeDisposable compositeDisposable;

    @Inject
    NewsCategoryPresenter(NewsRepository newsRepository, CompositeDisposable compositeDisposable) {
        this.newsRepository = newsRepository;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void attachView(CategoryContract.View view) {
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


    // TODO: 15.06.2018 Реализовать работу с CompositeDisposable
    @Override
    public void
    loadFromApi(QueryParam queryParam) {
        view.showProgress();
        QueryCategoryParam queryCategoryParam = (QueryCategoryParam) queryParam;
        Observable<ArticleResponse> articlesFromApi = newsRepository.getArticlesByCategoryFromApi(queryCategoryParam);
        articlesFromApi.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArticleResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ArticleResponse articleResponse) {
                        List<Article> articles = articleResponse.getArticles();
                        view.setData(articles);
                        view.hideProgress();
                        view.setMoreLoaded(false);
                        for (Article article : articles) {
                            article.setCategory(queryCategoryParam.getCategory());
                        }
                        newsRepository.storeArticlesInDb(articles);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onItemsLoadComplete();
                        view.hideProgress();
                        view.setMoreLoaded(false);
                    }

                    @Override
                    public void onComplete() {
                        view.onItemsLoadComplete();
                        view.hideProgress();
                        view.setMoreLoaded(false);
                    }
                });
    }
}
