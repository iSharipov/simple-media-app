package com.isharipov.simplemediaapp.movie.util;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * 11.07.2018.
 */
public class GenreListTypeConverter {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static List<String> stringToGenreIds(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<String>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String GenreIdsToString(List<String> genres) {
        return gson.toJson(genres);
    }
}
