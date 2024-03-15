package com.latihan.mandirinews.networks

import com.latihan.mandirinews.models.AllNewsResponse
import com.latihan.mandirinews.models.HeadlineNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("headline-news")
    fun getHeadlineNews(
        @Query("country") country: String?,
        @Query("apikey") apiKey: String?
    ): Call<HeadlineNewsResponse>

    @GET("all-news")
    fun getAllNews(
        @Query("country") country: String?,
        @Query("apikey") apiKey: String?
    ): Call<AllNewsResponse>

}