package com.example.newsbreak.request;

import com.example.newsbreak.response.NewsSearchResponse;
import com.example.newsbreak.utils.Credentials;
import com.example.newsbreak.utils.NewsApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Servicey {

    private static Retrofit.Builder retrofitBuilder=new Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL).
            addConverterFactory(GsonConverterFactory.create());


    private static  Retrofit retrofit=retrofitBuilder.build();

    private static NewsApiInterface newsApi=retrofit.create(NewsApiInterface.class);


    public static NewsApiInterface getNewsApi() {
        return newsApi;
    }
}
