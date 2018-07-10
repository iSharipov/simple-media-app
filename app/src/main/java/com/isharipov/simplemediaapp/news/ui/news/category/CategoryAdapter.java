package com.isharipov.simplemediaapp.news.ui.news.category;

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
import com.isharipov.simplemediaapp.news.model.Article;
import com.isharipov.simplemediaapp.ui.OnLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.util.Preconditions.checkNotNull;

/**
 * 13.06.2018.
 */
public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_ITEM = 1;
    private static final int VIEW_PROG = 0;

    private final List<Article> articles;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;

    public CategoryAdapter(List<Article> articles) {
        this.articles = articles;
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
                    .inflate(R.layout.news_article_item_row, parent, false);
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
            Article article = articles.get(position);
            normalViewHolder.articleDescription.setText(article.getDescription());
            normalViewHolder.articleTitle.setText(article.getTitle());

            GlideApp
                    .with(holder.itemView)
                    .load(article.getUrlToImage())
                    .into(normalViewHolder.articleImage);
        } else if (holder instanceof ProgressViewHolder) {
            ProgressViewHolder progressViewHolder = (ProgressViewHolder) holder;
            progressViewHolder.loadMoreProgressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    @Override
    public int getItemViewType(int position) {
        return articles.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.article_image)
        ImageView articleImage;
        @BindView(R.id.article_description)
        TextView articleDescription;
        @BindView(R.id.article_title)
        TextView articleTitle;

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

    public void replaceData(List<Article> articles) {
        checkNotNull(articles);
        this.articles.clear();
        setList(articles);
    }

    public void appendData(List<Article> articles) {
        checkNotNull(articles);
        setList(articles);
    }

    public void clearArticles() {
        this.articles.clear();
        notifyDataSetChanged();
    }

    private void setList(List<Article> nextArticles) {
        this.articles.addAll(nextArticles);
        notifyDataSetChanged();
    }

    public void setLoaded(boolean loading) {
        this.loading = loading;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }
}
