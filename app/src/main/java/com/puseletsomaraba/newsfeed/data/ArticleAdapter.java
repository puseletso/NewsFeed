package com.puseletsomaraba.newsfeed.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.puseletsomaraba.newsfeed.R;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    ArrayList<Article> articles = new ArrayList<>();
    Context context;


    public ArticleAdapter(ArrayList<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        //inflate news rows
        View articleRow = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_rows, parent, false);


        return new ViewHolder(articleRow);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //passing data to  views from selected titles
        //putting data together

        Article article = articles.get(position);

        holder.title.setText(article.getTitle());
        holder.description.setText(article.getDescription());
        holder.date.setText(article.getPublishDate());
        holder.author.setText(article.getAuthor());


    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //instantiate views
        public ImageView articleImage;
        public TextView author, description, title, date;

        public ViewHolder(View itemView) {
            super(itemView);

            articleImage = itemView.findViewById(R.id.newsimageid);
            author = itemView.findViewById(R.id.author);
            description = itemView.findViewById(R.id.descriptionNews);
            title = itemView.findViewById(R.id.newsTitle);
            date = itemView.findViewById(R.id.date);

        }
    }
}
