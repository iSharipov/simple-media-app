package com.isharipov.simplemediaapp.music.ui.artist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.isharipov.simplemediaapp.R;
import com.isharipov.simplemediaapp.glide.GlideApp;
import com.isharipov.simplemediaapp.music.model.Image;
import com.isharipov.simplemediaapp.music.model.artist.Artist;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.util.Preconditions.checkNotNull;
import static com.isharipov.simplemediaapp.music.model.ImageSize.EXTRALARGE;

/**
 * 01.07.2018.
 */
public class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.ViewHolder> {

    private final List<Artist> artists;

    public ArtistsAdapter(List<Artist> artists) {
        this.artists = artists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.music_artist_item_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Artist artist = artists.get(position);
        holder.artistName.setText(artist.getName());
        holder.artistPlaycount.setText(artist.getPlaycount());
        holder.artistListeners.setText(artist.getListeners());
        Image imageBySize = artist.getImageBySize(EXTRALARGE);
        GlideApp
                .with(holder.itemView)
                .load(imageBySize != null ? imageBySize.getText() : R.drawable.artist_default)
                .centerCrop()
                .into(holder.artistImage);
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.artist_image)
        ImageView artistImage;
        @BindView(R.id.artist_name)
        TextView artistName;
        @BindView(R.id.artist_playcount)
        TextView artistPlaycount;
        @BindView(R.id.artist_listeners)
        TextView artistListeners;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class ProgressViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.load_more_progressbar)
        ProgressBar loadMoreProgressBar;

        ProgressViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void replaceData(List<Artist> artists) {
        checkNotNull(artists);
        this.artists.clear();
        setList(artists);
    }

    public void appendData(List<Artist> artists) {
        checkNotNull(artists);
        setList(artists);
    }

    public void clearArticles() {
        this.artists.clear();
        notifyDataSetChanged();
    }

    private void setList(List<Artist> nextArtist) {
        this.artists.addAll(nextArtist);
        notifyDataSetChanged();
    }
}
