package com.isharipov.simplemediaapp.util;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * 27.06.2018.
 */
public final class PrefUtils {
    private PrefUtils() {

    }

    public static CountryLanguageWrapper getDefaultCountryFromLocale(String[] prefCountryValues, String[] prefLanguageValues) {
        Locale defaultLocale = Locale.getDefault();
        String country = defaultLocale.getCountry();
        List<String> countryValues = Arrays.asList(prefCountryValues != null ? prefCountryValues : new String[0]);
        if (country != null && countryValues.contains(country.toLowerCase())) {
            country = country.toLowerCase();
        } else {
            country = "en";
        }
        List<String> languageValues = Arrays.asList(prefLanguageValues != null ? prefLanguageValues : new String[0]);
        String language = defaultLocale.getLanguage();
        if (language != null && languageValues.contains(language.toLowerCase())) {
            language = language.toLowerCase();
        } else {
            language = "en";
        }
        return new CountryLanguageWrapper(country, language);
    }
}
