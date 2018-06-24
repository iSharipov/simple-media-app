package com.isharipov.simplemediaapp.news.ui.news.souce;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.isharipov.simplemediaapp.R;
import com.isharipov.simplemediaapp.glide.GlideApp;
import com.isharipov.simplemediaapp.news.model.source.Source;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 24.06.2018.
 */
public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.SourceViewHolder> {

    private final List<Source> sources;

    public SourceAdapter(List<Source> sources) {
        this.sources = sources;
    }

    @NonNull
    @Override
    public SourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.source_item_row, parent, false);
        return new SourceViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull SourceViewHolder holder, int position) {
        final Source source = sources.get(position);
        GlideApp
                .with(holder.itemView)
                .load(source.getUrl())
                .into(holder.mediaSourceImage);
    }

    @Override
    public int getItemCount() {
        return sources.size();
    }

    static class SourceViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.media_source_img)
        ImageView mediaSourceImage;

        public SourceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
