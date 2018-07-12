package com.isharipov.simplemediaapp.util;

/**
 * 12.07.2018.
 */
public class CountryLanguageWrapper {
    private final String country;
    private final String language;

    public CountryLanguageWrapper(String country, String language) {
        this.country = country;
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public String getLanguage() {
        return language;
    }
}
