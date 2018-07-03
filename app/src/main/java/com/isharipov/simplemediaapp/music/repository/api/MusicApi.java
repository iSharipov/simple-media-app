package com.isharipov.simplemediaapp.music.repository.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.isharipov.simplemediaapp.music.model.artist.ArtistsResponse;
import com.isharipov.simplemediaapp.music.model.track.TracksResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 29.06.2018.
 */
public interface MusicApi {
    @GET("/2.0/?method=chart.gettopartists")
    Observable<ArtistsResponse> getTopArtists(@Query("limit") @Nullable Integer limit,
                                              @Query("page") @Nullable Integer page,
                                              @Query("api_key") @NonNull String apiKey,
                                              @Query("format") @NonNull String format);

    @GET("/2.0/?method=chart.gettoptracks")
    Observable<TracksResponse> getTopTracks(@Query("limit") @Nullable Integer limit,
                                            @Query("page") @Nullable Integer page,
                                            @Query("api_key") @NonNull String apiKey,
                                            @Query("format") @NonNull String format);
}
