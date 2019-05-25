package com.puseletsomaraba.newsfeed.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.puseletsomaraba.newsfeed.R;
import com.puseletsomaraba.newsfeed.util.Util;
import com.squareup.picasso.Picasso;

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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //passing data to  views from selected titles
        //putting data together

        Article article = articles.get(position);

        holder.title.setText(article.getTitle());
        holder.description.setText(article.getDescription());
        holder.date.setText(Util.dateFormatted(article.getPublishDate()));
        holder.author.setText(article.getAuthor());

        BitmapDrawable bitmapDrawable = (BitmapDrawable) holder.articleImage.getDrawable();

        Bitmap photo = bitmapDrawable.getBitmap();
        Palette.from(photo).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {

                int bgColor = palette.getMutedColor(ContextCompat.getColor(context,
                        android.R.color.black));

                holder.date.setBackgroundColor(bgColor);
                holder.author.setTextColor(bgColor);

            }
        });
        //get image
//        Glide.with(context)
//                .load(article.getImageUrl()).
//                into(holder.articleImage);
        Picasso.get()
                .load(article.getImageUrl())
                .into(holder.articleImage);


    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    //ViewHolder initialises the views and set the click listner ,when article is clicked,it takes to to full article
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //instantiate views
        public ImageView articleImage;
        public TextView author, description, title, date;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);//registers my views to receive clicks

            articleImage = itemView.findViewById(R.id.newsimageid);
            author = itemView.findViewById(R.id.author);
            description = itemView.findViewById(R.id.descriptionNews);
            title = itemView.findViewById(R.id.newsTitle);
            date = itemView.findViewById(R.id.date);

        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Redirect me to full article", Toast.LENGTH_LONG).show();


        }
    }
}
