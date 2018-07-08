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

import java.util.List;

import static com.isharipov.simplemediaapp.movie.di.MovieApiModule.BASE_URL;

/**
 * 01.05.2018.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final List<Movie> movies;
    private final MovieViewClickListener clickListener;

    public MovieAdapter(List<Movie> movies, MovieViewClickListener clickListener) {
        this.movies = movies;
        this.clickListener = clickListener;
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
                .load(BASE_URL + "w342" + movies.get(position).getPosterPath())
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

    interface MovieViewClickListener {

        void onClick(View view, int position);
    }

}