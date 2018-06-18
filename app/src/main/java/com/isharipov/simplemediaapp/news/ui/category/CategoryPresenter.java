package com.isharipov.simplemediaapp.news.ui.category;

import com.isharipov.simplemediaapp.news.model.ArticleResponse;
import com.isharipov.simplemediaapp.news.model.QueryParam;
import com.isharipov.simplemediaapp.news.repository.ArticleRepository;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

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
    public void loadArticles(QueryParam queryParam) {
        articleRepository.getArticleResponse(queryParam)
                .debounce(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<ArticleResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                System.out.println(d);
                            }

                            @Override
                            public void onNext(ArticleResponse articleResponse) {
                                view.setData(articleResponse.getArticles());
                            }

                            @Override
                            public void onError(Throwable e) {
                                System.out.println(e);
                            }

                            @Override
                            public void onComplete() {
                                System.out.println("blabla");
                            }
                        }
                );
    }
}
