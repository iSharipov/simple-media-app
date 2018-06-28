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

    public static String getDefaultCountryFromLocale(String[] prefCountryValues) {
        List<String> listValues = Arrays.asList(prefCountryValues);
        Locale defaultLocale = Locale.getDefault();
        String country = defaultLocale.getCountry();
        if (country != null && listValues.contains(country.toLowerCase())) {
            return country.toLowerCase();
        }
        return "en";
    }
}
