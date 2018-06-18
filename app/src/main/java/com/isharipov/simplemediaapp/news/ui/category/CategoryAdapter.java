package com.isharipov.simplemediaapp.news.ui.category;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.isharipov.simplemediaapp.R;
import com.isharipov.simplemediaapp.glide.GlideApp;
import com.isharipov.simplemediaapp.news.model.Article;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.util.Preconditions.checkNotNull;

/**
 * 13.06.2018.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private final List<Article> articles;

    public CategoryAdapter(List<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_item_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.articleDescription.setText(article.getDescription());
        holder.articleTitle.setText(article.getTitle());

        GlideApp
                .with(holder.itemView)
                .load(article.getUrlToImage())
                .into(holder.articleImage);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

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

    public void replaceData(List<Article> recipes) {
        setList(recipes);
    }

    private void setList(List<Article> nextArticles) {
        checkNotNull(nextArticles);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ArticleAdapterDiffCallback(articles, nextArticles), true);
        this.articles.clear();
        this.articles.addAll(nextArticles);
        diffResult.dispatchUpdatesTo(this);
    }

    private static class ArticleAdapterDiffCallback extends DiffUtil.Callback {

        private List<Article> current;
        private List<Article> next;

        public ArticleAdapterDiffCallback(List<Article> current, List<Article> next) {
            this.current = current;
            this.next = next;
        }

        @Override
        public int getOldListSize() {
            return current.size();
        }

        @Override
        public int getNewListSize() {
            return next.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            Article currentArticle = current.get(oldItemPosition);
            Article nextArticle = next.get(newItemPosition);
            return currentArticle.getId() == nextArticle.getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            Article currentArticle = current.get(oldItemPosition);
            Article nextArticle = next.get(newItemPosition);
            return currentArticle.equals(nextArticle);
        }
    }
}
