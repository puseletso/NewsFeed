package com.puseletsomaraba.newsfeed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.puseletsomaraba.newsfeed.data.Article;
import com.puseletsomaraba.newsfeed.data.ArticleAdapter;
import com.puseletsomaraba.newsfeed.data.ArticleData;
import com.puseletsomaraba.newsfeed.data.ArticleLsitAsynResponse;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArticleAdapter articleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ArticleData().getNewsList(new ArticleLsitAsynResponse() {
            @Override
            public void processFinish(ArrayList<Article> articles) {

                //instantiate view
                recyclerView = findViewById(R.id.recyclerView);

                //instantiate recycler view
                articleAdapter = new ArticleAdapter(articles, getApplicationContext());

                //set recycler layoutmanager to set up the views
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(articleAdapter);

                articleAdapter.setOnClickListener(new ArticleAdapter.OnItemClickListner() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
    }
}
