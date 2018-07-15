package com.isharipov.simplemediaapp.movie.ui.category;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.isharipov.simplemediaapp.R;
import com.isharipov.simplemediaapp.movie.model.Review;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.util.Preconditions.checkNotNull;

/**
 * 02.05.2018.
 */
public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private final List<Review> reviews;

    public ReviewAdapter(List<Review> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_review_list_item, parent, false);
        return new ReviewViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        holder.reviewAuthor.setText(reviews.get(position).getAuthor());
        holder.reviewContent.setText(reviews.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.review_author)
        TextView reviewAuthor;
        @BindView(R.id.review_content)
        TextView reviewContent;

        ReviewViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void replaceData(List<Review> reviews) {
        checkNotNull(reviews);
        this.reviews.clear();
        setList(reviews);
    }

    public void appendData(List<Review> reviews) {
        checkNotNull(reviews);
        setList(reviews);
    }

    private void setList(List<Review> nextReviews) {
        this.reviews.addAll(nextReviews);
        notifyDataSetChanged();
    }

    public void clearReviews() {
        this.reviews.clear();
        notifyDataSetChanged();
    }
}