package com.example.newsbreak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.newsbreak.models.NewsModel;
import com.example.newsbreak.request.Servicey;
import com.example.newsbreak.response.NewsSearchResponse;
import com.example.newsbreak.utils.Credentials;
import com.example.newsbreak.utils.NewsApiInterface;
import com.example.newsbreak.viewmodel.NewsListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btn;
    Servicey servicey;
    //View Model
    private NewsListViewModel newsListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.button);
        newsListViewModel= new ViewModelProvider(this).get(NewsListViewModel.class);

        ObserveAnyChange();


//Testing
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Tag","Button clicked");
                searchNewsApi("apple");
            }
        });




//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                GetSearchResponse("modi");
////                GetRetrofitResponse();
//            }
//        });

    }

    //observing any data change
    private  void ObserveAnyChange(){
        newsListViewModel.getArticles().observe(this, new Observer<List<NewsModel>>() {
            @Override
            public void onChanged(List<NewsModel> newsModels) {
                //observing for any data change
                if(newsModels != null){
                    for (NewsModel newsModel: newsModels){
                        Log.v("Tag","on change :"+ newsModel.getTitle());
                    }

                }  else{
                    Log.v("Tag","Error");
                }
            }
        });
    }


    //calling from viewmodel
    private void searchNewsApi(String query)
    {
        newsListViewModel.searchNewApi(query);
    }

    private void GetSearchResponse(String query)
    {
        NewsApiInterface newsApiInterface=Servicey.getNewsApi();
        Call<NewsSearchResponse> responseCall= newsApiInterface.getSearchHeadlines(Credentials.API_KEY,
                "en",query);
        responseCall.enqueue(new Callback<NewsSearchResponse>() {
            @Override
            public void onResponse(Call<NewsSearchResponse> call, Response<NewsSearchResponse> response) {
                if (response.code() == 200){
                    Log.v("Tag","the response"+ response.body().toString());
                    List<NewsModel> articles=new ArrayList<>(response.body().getnewsArticles());
                    for (NewsModel news: articles){
                        Log.v("Tag","list"+ news.getTitle());
                    }
                }
                else{
                    try {
                        Log.v("tag","Error" );
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsSearchResponse> call, Throwable t) {

            }
        });
    }
    private void GetRetrofitResponse() {


      NewsApiInterface newsApiInterface=Servicey.getNewsApi();
      Call<NewsSearchResponse> responseCall=newsApiInterface.getHeadlines(Credentials.API_KEY,"en","general");
responseCall.enqueue(new Callback<NewsSearchResponse>() {
    @Override
    public void onResponse(Call<NewsSearchResponse> call, Response<NewsSearchResponse> response) {
        if (response.code() == 200){
            Log.v("Tag","the response"+ response.body().toString());
            List<NewsModel> articles=new ArrayList<>(response.body().getnewsArticles());
            for (NewsModel news: articles){
                Log.v("Tag","T list"+ news.getTitle());
            }
        }
        else{
            try {
                Log.v("tag","Error" );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onFailure(Call<NewsSearchResponse> call, Throwable t) {

    }
});

}

}
