package com.example.newsbreak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.newsbreak.adapter.NewsRecyclerAdapter;
import com.example.newsbreak.models.NewsModel;
import com.example.newsbreak.request.Servicey;
import com.example.newsbreak.response.NewsSearchResponse;
import com.example.newsbreak.utils.Credentials;
import com.example.newsbreak.utils.NewsApiInterface;
import com.example.newsbreak.viewmodel.NewsListViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //View Model
    private NewsListViewModel newsListViewModel;
    RecyclerView recyclerView;
    List<NewsModel> articleList;
    NewsRecyclerAdapter adapter;

    private ShimmerFrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsListViewModel= new ViewModelProvider(this).get(NewsListViewModel.class);

        recyclerView=findViewById(R.id.news_recycler_view);
        mFrameLayout=findViewById(R.id.shimmerLayout);

        ObserveAnyChange();
         setUpRecyclerView();

      displayNewsApi();


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

                       adapter.setmNews(newsModels);
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

    //calling from viewmodel for displaying news
    private void displayNewsApi(){
        newsListViewModel.displayNewsApi();

    }





    void setUpRecyclerView(){

       adapter=new NewsRecyclerAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));


    }







//    private void GetSearchResponse(String query)
//    {
//        NewsApiInterface newsApiInterface=Servicey.getNewsApi();
//        Call<NewsSearchResponse> responseCall= newsApiInterface.getSearchHeadlines(Credentials.API_KEY,
//                "en",query);
//        responseCall.enqueue(new Callback<NewsSearchResponse>() {
//            @Override
//            public void onResponse(Call<NewsSearchResponse> call, Response<NewsSearchResponse> response) {
//                if (response.code() == 200){
//                    Log.v("Tag","the response"+ response.body().toString());
//                    List<NewsModel> articles=new ArrayList<>(response.body().getnewsArticles());
//                    for (NewsModel news: articles){
//                        Log.v("Tag","list"+ news.getTitle());
//                    }
//                }
//                else{
//                    try {
//                        Log.v("tag","Error" );
//                    } catch (Exception e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<NewsSearchResponse> call, Throwable t) {
//
//            }
//        });
//    }
//    private void GetRetrofitResponse() {
//
//
//      NewsApiInterface newsApiInterface=Servicey.getNewsApi();
//      Call<NewsSearchResponse> responseCall=newsApiInterface.getHeadlines(Credentials.API_KEY,"en","general");
//responseCall.enqueue(new Callback<NewsSearchResponse>() {
//    @Override
//    public void onResponse(Call<NewsSearchResponse> call, Response<NewsSearchResponse> response) {
//        if (response.code() == 200){
//            Log.v("Tag","the response"+ response.body().toString());
//            List<NewsModel> articles=new ArrayList<>(response.body().getnewsArticles());
//            for (NewsModel news: articles){
//                Log.v("Tag","T list"+ news.getTitle());
//            }
//        }
//        else{
//            try {
//                Log.v("tag","Error" );
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//    @Override
//    public void onFailure(Call<NewsSearchResponse> call, Throwable t) {
//
//    }
//});
//
//}

}
