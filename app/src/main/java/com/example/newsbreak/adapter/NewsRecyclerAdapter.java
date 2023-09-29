package com.example.newsbreak.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsbreak.NewsFullActivity;
import com.example.newsbreak.R;
import com.example.newsbreak.models.NewsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder> {

    List<NewsModel> articleList=new ArrayList<>();

    public NewsRecyclerAdapter(){

    }
    public NewsRecyclerAdapter(List<NewsModel> articleList){
        this.articleList=articleList;
    }
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.new_recycler_row,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsModel article=articleList.get(position);
        holder.titleTextView.setText(article.getTitle());
        holder.sourceTextView.setText("source");
//        Picasso.get().load(article.getUrlToImage())
//                .error(R.drawable.baseline_image_not_supported_24)
//                .into(holder.imageView);
        Glide.with(holder.itemView.getContext())
                .load(article.getUrlToImage())
                .error(R.drawable.baseline_image_not_supported_24)
                .placeholder(R.drawable.baseline_image_24)
                .into(holder.imageView);


        holder.itemView.setOnClickListener(v -> {
            Intent intent=new Intent(v.getContext(), NewsFullActivity.class);
            intent.putExtra("url",article.getUrl());
            v.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }
    void updateData(List<NewsModel> data){
        articleList.clear();
        articleList.addAll(data);
    }
    public void setmNews(List<NewsModel> mNews) {
        this.articleList = mNews;
        notifyDataSetChanged();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextView,sourceTextView;
        ImageView imageView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView=itemView.findViewById(R.id.article_title);
            sourceTextView=itemView.findViewById(R.id.article_source);
            imageView=itemView.findViewById(R.id.article_image_view);
        }
    }
}
