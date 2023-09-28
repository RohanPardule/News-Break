package com.example.newsbreak.response;

import com.example.newsbreak.models.NewsModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsSearchResponse {
//    @SerializedName("totalResults")
//
//    private int total_count;

    @SerializedName("articles")
    private List<NewsModel> newsArticles;

//    public int getTotal_count()
//    {
//        return total_count;
//    }

    public List<NewsModel> getnewsArticles() {
        return newsArticles;
    }

    @Override
    public String toString() {
        return "NewsSearchResponse{" +
                "newsArticles=" + newsArticles +
                '}';
    }
}
