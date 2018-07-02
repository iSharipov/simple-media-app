package com.isharipov.simplemediaapp.music.ui.artist;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
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
import com.isharipov.simplemediaapp.music.model.Size;
import com.isharipov.simplemediaapp.music.model.artist.Artist;
import com.isharipov.simplemediaapp.news.ui.news.category.OnLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.util.Preconditions.checkNotNull;

/**
 * 01.07.2018.
 */
public class ArtistsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_ITEM = 1;
    private static final int VIEW_PROG = 0;

    private final List<Artist> artists;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;

    public ArtistsAdapter(List<Artist> artists) {
        this.artists = artists;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = recyclerView.getLayoutManager().getItemCount();
                lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                if (!loading && totalItemCount != 0 && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    loading = true;
                }
            }
        });
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.music_artist_item_row, parent, false);
            vh = new ViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.load_more_progressbar, parent, false);

            vh = new ProgressViewHolder(v);
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder normalViewHolder = (ViewHolder) holder;
            Artist artist = artists.get(position);
            normalViewHolder.artistName.setText(artist.getName());
            normalViewHolder.artistPlaycount.setText(artist.getPlaycount());
            normalViewHolder.artistListeners.setText(artist.getListeners());
            Image imageBySize = artist.getImageBySize(Size.MEDIUM);

            GlideApp
                    .with(holder.itemView)
                    .load(imageBySize != null ? imageBySize.getText() : R.drawable.ic_action_name)
                    .into(normalViewHolder.artistImage);
        } else if (holder instanceof ProgressViewHolder) {
            ProgressViewHolder progressViewHolder = (ProgressViewHolder) holder;
            progressViewHolder.loadMoreProgressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemViewType(int position) {
        return artists.get(position) != null ? VIEW_ITEM : VIEW_PROG;
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

    public void setLoaded(boolean loading) {
        this.loading = loading;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }
}
