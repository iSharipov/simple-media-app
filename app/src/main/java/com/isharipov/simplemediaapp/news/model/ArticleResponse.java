package com.isharipov.simplemediaapp.news.model;

import java.io.Serializable;
import java.util.List;

/**
 * 13.06.2018.
 */
public class ArticleResponse implements Serializable {
    private String status;
    private Long totalResults;
    private List<Article> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
