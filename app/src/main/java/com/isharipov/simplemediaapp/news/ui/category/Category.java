package com.isharipov.simplemediaapp.news.ui.category;

/**
 * 13.06.2018.
 */
public enum Category {
    BUSINESS("Business", "business"),
    ENTERTAINMENT("Entertainment", "entertainment"),
    GENERAL("General", "general"),
    HEALTH("Health", "health"),
    SCIENCE("Science", "science"),
    SPORST("Sports", "sporst"),
    TECHNOLOGY("Technology", "technology");

    private String pageTitle;
    private String categoryParam;

    Category(String pageTitle, String categoryParam) {
        this.pageTitle = pageTitle;
        this.categoryParam = categoryParam;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public String getCategoryParam() {
        return categoryParam;
    }
}
