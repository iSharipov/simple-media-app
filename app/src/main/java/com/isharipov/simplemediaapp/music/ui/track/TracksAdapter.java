package com.isharipov.simplemediaapp.music.ui.track;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.isharipov.simplemediaapp.R;
import com.isharipov.simplemediaapp.glide.GlideApp;
import com.isharipov.simplemediaapp.music.model.Image;
import com.isharipov.simplemediaapp.music.model.track.Track;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.util.Preconditions.checkNotNull;
import static com.isharipov.simplemediaapp.music.model.ImageSize.EXTRALARGE;

/**
 * 02.07.2018.
 */
public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.ViewHolder> {

    private List<Track> tracks;

    public TracksAdapter(List<Track> tracks) {
        this.tracks = tracks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.music_track_item_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Track track = tracks.get(position);
        holder.artistName.setText(track.getArtist().getName());
        holder.trackName.setText(track.getName());
        holder.trackPlaycount.setText(track.getPlaycount());
        holder.trackListeners.setText(track.getListeners());
        Image imageBySize = track.getImageBySize(EXTRALARGE);
        GlideApp
                .with(holder.itemView)
                .load(imageBySize != null ? imageBySize.getText() : R.drawable.track_default)
                .centerCrop()
                .into(holder.trackImage);
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.track_image)
        ImageView trackImage;
        @BindView(R.id.artist_name)
        TextView artistName;
        @BindView(R.id.track_name)
        TextView trackName;
        @BindView(R.id.track_playcount)
        TextView trackPlaycount;
        @BindView(R.id.track_listeners)
        TextView trackListeners;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void replaceData(List<Track> tracks) {
        checkNotNull(tracks);
        this.tracks.clear();
        setList(tracks);
    }

    public void appendData(List<Track> tracks) {
        checkNotNull(tracks);
        setList(tracks);
    }

    public void clearArticles() {
        this.tracks.clear();
        notifyDataSetChanged();
    }

    private void setList(List<Track> nextTrack) {
        this.tracks.addAll(nextTrack);
        notifyDataSetChanged();
    }
}
