package com.isharipov.simplemediaapp.movie.ui.category;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.isharipov.simplemediaapp.R;
import com.isharipov.simplemediaapp.glide.GlideApp;
import com.isharipov.simplemediaapp.movie.model.Movie;
import com.isharipov.simplemediaapp.ui.OnLoadMoreListener;

import java.util.List;

import static com.bumptech.glide.util.Preconditions.checkNotNull;

/**
 * 01.05.2018.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/";
    private final List<Movie> movies;
    private final MovieViewClickListener clickListener;
    private final OnLoadMoreListener onLoadMoreListener;
    private boolean loading;

    public MovieAdapter(List<Movie> movies, MovieViewClickListener clickListener, OnLoadMoreListener onLoadMoreListener) {
        this.movies = movies;
        this.clickListener = clickListener;
        this.onLoadMoreListener = onLoadMoreListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item_row, parent, false);
        return new MovieViewHolder(layoutView, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        GlideApp.with(holder.itemView)
                .load(IMAGE_BASE_URL + "w342" + movies.get(position).getPosterPath())
                .into(holder.getGridItemImage());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        private final ImageView gridItemImage;

        MovieViewHolder(View itemView, MovieViewClickListener clickListener) {
            super(itemView);
            gridItemImage = itemView.findViewById(R.id.grid_item_image);

            itemView.setOnClickListener(v -> clickListener.onClick(itemView, getAdapterPosition()));
        }

        ImageView getGridItemImage() {
            return gridItemImage;
        }
    }

    public void replaceData(List<Movie> movies) {
        checkNotNull(movies);
        this.movies.clear();
        setList(movies);
    }

    public void appendData(List<Movie> movies) {
        checkNotNull(movies);
        setList(movies);
    }

    private void setList(List<Movie> nextArticles) {
        this.movies.addAll(nextArticles);
        notifyDataSetChanged();
    }

    public void clearMovies() {
        this.movies.clear();
        notifyDataSetChanged();
    }

    public void setLoaded(boolean loading) {
        this.loading = loading;
    }

    interface MovieViewClickListener {

        void onClick(View view, int position);
    }

}