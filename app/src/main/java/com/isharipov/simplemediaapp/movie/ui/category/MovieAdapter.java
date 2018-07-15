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

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.util.Preconditions.checkNotNull;

/**
 * 01.05.2018.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/";
    private final List<Movie> movies;
    private final MovieClickListener clickListener;
    private final OnLoadMoreListener onLoadMoreListener;
    private boolean loading;

    public MovieAdapter(List<Movie> movies, OnLoadMoreListener onLoadMoreListener, MovieClickListener clickListener) {
        this.movies = movies;
        this.onLoadMoreListener = onLoadMoreListener;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item_row, parent, false);
        return new MovieViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        GlideApp.with(holder.itemView)
                .load(IMAGE_BASE_URL + "w342" + movies.get(position).getPosterPath())
                .into(holder.gridItemImage);
        holder.itemView.setOnClickListener(v -> clickListener.onClick(movies.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.grid_item_image)
        ImageView gridItemImage;

        MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
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

}