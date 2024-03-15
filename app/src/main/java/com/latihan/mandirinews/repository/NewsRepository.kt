package com.latihan.mandirinews.repository

import com.latihan.mandirinews.models.AllNewsResponse
import com.latihan.mandirinews.models.HeadlineNewsResponse
import retrofit2.Call

interface NewsRepository {

    fun requestHeadlineNews(
        country: String?,
        apiKey: String?
    ): Call<HeadlineNewsResponse>

    fun requestAllNews(
        q: String?,
        apiKey: String?
    ): Call<AllNewsResponse>

}