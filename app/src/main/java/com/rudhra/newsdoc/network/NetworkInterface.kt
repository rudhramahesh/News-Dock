package com.rudhra.newsdoc.network

import com.rudhra.newsdoc.ui.model.Articles
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkInterface {

    @GET("top-headlines")
     fun getNewsArticles(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): Call<Articles>
}