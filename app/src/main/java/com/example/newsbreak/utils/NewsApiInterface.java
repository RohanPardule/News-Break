package com.example.newsbreak.utils;

import com.example.newsbreak.response.NewsSearchResponse;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiInterface {
//    @GET("top-headlines")
//    Call<NewsSearchResponse> getHeadlines(
//            @Query("apiKey") String key,
//            @Query("language") String language,
//            @Query("category") String category
//
//    );
    @GET("everything")
    Call<NewsSearchResponse> getHeadlines(
            @Query("apiKey") String apiKey,
            @Query("q") String query);

    @GET("top-headlines")
    Call<NewsSearchResponse> getSearchHeadlines(
            @Query("apiKey") String key,
            @Query("language") String language,
            @Query("q") String query

    );

}
