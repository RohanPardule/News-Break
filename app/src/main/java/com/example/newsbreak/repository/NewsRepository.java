package com.example.newsbreak.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.newsbreak.client.NewsApiClient;
import com.example.newsbreak.models.NewsModel;

import java.util.List;

public class NewsRepository {
    // this class acting as repository
    private  static NewsRepository instance;
 private NewsApiClient newsApiClient;
    public static NewsRepository getInstance(){

        if (instance==null){
            instance=new NewsRepository();
        }
        return instance;

    }

    private NewsRepository(){
      newsApiClient=NewsApiClient.getInstance();

    }

    public LiveData<List<NewsModel>> getArticles(){
        return newsApiClient.getArticles();
    }
//calling method from api client
    public  void searchNewsApi(String query){
        newsApiClient.searchArticlesApi(query);
    }

}
