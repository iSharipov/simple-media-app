package com.isharipov.simplemediaapp.music.model;

/**
 * 01.07.2018.
 */
public enum Size {
    SMALL,
    MEDIUM,
    LARGE,
    EXTRALARGE;

    public static boolean containsSize(String size) {
        for (Size s : Size.values()) {
            if (s.name().equalsIgnoreCase(size)) {
                return true;
            }
        }
        return false;
    }
}
