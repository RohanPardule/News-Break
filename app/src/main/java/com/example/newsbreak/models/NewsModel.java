package com.example.newsbreak.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class NewsModel {
    @SerializedName("title")
    private String title;
    @SerializedName("url")
    private String url;
    @SerializedName("urlToImage")
    private String urlToImage;

    public String getTitle() {
        return title;
    }



    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }
}
