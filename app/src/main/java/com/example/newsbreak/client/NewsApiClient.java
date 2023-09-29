package com.example.newsbreak.client;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.newsbreak.executors.AppExecutors;
import com.example.newsbreak.models.NewsModel;
import com.example.newsbreak.request.Servicey;
import com.example.newsbreak.response.NewsSearchResponse;
import com.example.newsbreak.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class NewsApiClient {

    private MutableLiveData<List<NewsModel>> mArticles;
    private static NewsApiClient instance;


    //making global request
    private RetrieveNewsRunnable retrieveNewsRunnable;
    private RetrieveNewsRunnable1 retrieveNewsRunnable1;

    public static NewsApiClient getInstance(){
        if (instance==null){
            instance=new NewsApiClient();
        }
        return instance;

    }
    private NewsApiClient(){
        mArticles=new MutableLiveData<>();
    }


    public LiveData<List<NewsModel>>getArticles(){
        return mArticles;
    }

//this method we are going to call through classes
    public void searchArticlesApi(String query) {

        if (retrieveNewsRunnable != null)
        {
            retrieveNewsRunnable=null;
        }
        retrieveNewsRunnable=new RetrieveNewsRunnable(query);
        final Future myHandler = AppExecutors.getInstance().NetworkIO().submit(retrieveNewsRunnable);
        AppExecutors.getInstance().NetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //cancelling call
                myHandler.cancel(true);

            }
        }, 3000, TimeUnit.MILLISECONDS);

    }
    public void displayArticlesApi() {

        if (retrieveNewsRunnable1 != null)
        {
            retrieveNewsRunnable1=null;
        }
        retrieveNewsRunnable1=new RetrieveNewsRunnable1();
        final Future myHandler = AppExecutors.getInstance().NetworkIO().submit(retrieveNewsRunnable1);
        AppExecutors.getInstance().NetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //cancelling call
                myHandler.cancel(true);

            }
        }, 3000, TimeUnit.MILLISECONDS);

    }

        // Retreiving data from api by runnable class

        private class RetrieveNewsRunnable implements Runnable {

private  String query;
boolean cancelRequest;

            public RetrieveNewsRunnable(String query) {
                this.query = query;
                cancelRequest=false;
            }

            @Override
            public void run() {

                //getting response body

                try{
                    Response response=getArticles(query).execute();
                    if (cancelRequest) {
                        return;

                    }
                    if (response.code() == 200){
                        List<NewsModel> list=new ArrayList<>(((NewsSearchResponse)response.body()).getnewsArticles());

                        if (list==null){
                            mArticles.postValue(null);
                        }
                        else {
                            mArticles.postValue(list);
                        }


                    }
                    else {
                        String error = response.errorBody().string();
                        Log.v("tag ",error);
                        mArticles.postValue(null);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    mArticles.postValue(null);
                }



            }

            private Call<NewsSearchResponse> getArticles(String query){
                return Servicey.getNewsApi().getSearchHeadlines(Credentials.API_KEY,
                        "en",query);
            }

            private void CancelRequest(){
                Log.v("Tag", "Cancelling the request");
                cancelRequest=true;
            }

    }
    private class RetrieveNewsRunnable1 implements Runnable {


        boolean cancelRequest;

        public RetrieveNewsRunnable1() {

            cancelRequest=false;
        }

        @Override
        public void run() {

            //getting response body

            try{
                Response response=getArticles().execute();
                if (cancelRequest) {
                    return;

                }
                if (response.code() == 200){
                    List<NewsModel> list=new ArrayList<>(((NewsSearchResponse)response.body()).getnewsArticles());

                    if (list==null){
                        mArticles.postValue(null);
                    }
                    else {
                        mArticles.postValue(list);
                    }


                }
                else {
                    String error = response.errorBody().string();
                    Log.v("tag ",error);
                    mArticles.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                mArticles.postValue(null);
            }



        }

//        private Call<NewsSearchResponse> getArticles(){
//            return Servicey.getNewsApi().getHeadlines(Credentials.API_KEY,
//                    "en","General");
//        }
        private Call<NewsSearchResponse> getArticles(){
            return Servicey.getNewsApi().getHeadlines(Credentials.API_KEY,
                    "Technology");
        }

        private void CancelRequest(){
            Log.v("Tag", "Cancelling the request");
            cancelRequest=true;
        }

    }








}
