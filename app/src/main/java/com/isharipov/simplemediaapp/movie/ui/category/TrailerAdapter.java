package com.isharipov.simplemediaapp.movie.ui.category;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.isharipov.simplemediaapp.R;
import com.isharipov.simplemediaapp.movie.model.Trailer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.util.Preconditions.checkNotNull;

/**
 * 02.05.2018.
 */
public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {

    private final List<Trailer> trailers;
    private final TrailerClickListener clickListener;

    public TrailerAdapter(List<Trailer> trailers, TrailerClickListener clickListener) {
        this.trailers = trailers;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_trailer_list_item, parent, false);
        return new TrailerViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        holder.trailerTitle.setText(trailers.get(position).getName());
        holder.itemView.setOnClickListener(v -> clickListener.onClick(trailers.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    static class TrailerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.trailer_start_btn)
        ImageView trailerStartBtn;
        @BindView(R.id.trailer_title)
        TextView trailerTitle;

        TrailerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void replaceData(List<Trailer> trailers) {
        checkNotNull(trailers);
        this.trailers.clear();
        setList(trailers);
    }

    public void appendData(List<Trailer> trailers) {
        checkNotNull(trailers);
        setList(trailers);
    }

    private void setList(List<Trailer> nextTrailers) {
        this.trailers.addAll(nextTrailers);
        notifyDataSetChanged();
    }

    public void clearTrailers() {
        this.trailers.clear();
        notifyDataSetChanged();
    }
}
