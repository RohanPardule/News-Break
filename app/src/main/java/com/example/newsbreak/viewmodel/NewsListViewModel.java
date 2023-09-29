package com.example.newsbreak.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsbreak.models.NewsModel;
import com.example.newsbreak.repository.NewsRepository;

import java.util.List;

public class NewsListViewModel extends ViewModel {

private NewsRepository newsRepository;

    public NewsListViewModel() {
        //instance of repository
        newsRepository=NewsRepository.getInstance();
    }

    public LiveData<List<NewsModel>> getArticles(){
        //getting data from repository
        return newsRepository.getArticles() ;
    }


    //calling from repository
    public void searchNewApi(String query)
    {
        newsRepository.searchNewsApi(query);
    }
    public void displayNewsApi(){
        newsRepository.displayNewsApi();
    }

}
