package com.puseletsomaraba.newsfeed.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.puseletsomaraba.newsfeed.controller.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ArticleData {

    ArrayList<Article> articles = new ArrayList<>();
    String url = "https://newsapi.org/v2/everything?q=bitcoin&apiKey=ceab81f667224435a9b02c4dc193d557";


    public void getNewsList(final ArticleLsitAsynResponse callback) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray articleArray = response.getJSONArray("articles");

                    for (int i = 0; i < articleArray.length(); i++) {
                        //create article object
                        JSONObject articleObject = articleArray.getJSONObject(i);
                        Article article = new Article();

                        article.setAuthor(articleObject.getString("author"));
                        article.setTitle(articleObject.getString("title"));
                        article.setDescription(articleObject.getString("description"));
                        article.setImageUrl(articleObject.getString("urlToImage"));
                        article.setPublishDate(articleObject.getString("publishedAt"));
                        article.setNewsUrl(articleObject.getString("url"));


                        //add article to arraylist
                        articles.add(article);




                    }

                    if (null != callback) callback.processFinish(articles);


                    //check array ,is  getting info from api
                    Log.v("maraba article object: ", articles.toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

}
