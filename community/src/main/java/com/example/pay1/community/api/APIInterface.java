package com.example.pay1.community.api;


import com.example.pay1.community.api.response.FeedResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface APIInterface {

    @GET("/api/getFeed")
    Call<List<FeedResource>> getList();

//    @GET("/api/getFeed")
//    Call<List<FeedResource>> getComp(@Query("feed_type") Integer feedType);
//
//    @GET("/api/getFeed")
//    Call<List<FeedResource>> getSlider(@Query("resource_representation_type") Integer resType);

}