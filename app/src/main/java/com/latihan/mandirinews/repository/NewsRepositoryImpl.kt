package com.latihan.mandirinews.repository

import com.latihan.mandirinews.models.AllNewsResponse
import com.latihan.mandirinews.models.HeadlineNewsResponse
import com.latihan.mandirinews.networks.ApiClient
import retrofit2.Call

class NewsRepositoryImpl: NewsRepository {

    override fun requestHeadlineNews(
        country: String?,
        apiKey: String?
    ): Call<HeadlineNewsResponse> {
        return ApiClient.getApiServices().getHeadlineNews(country, apiKey)
    }

    override fun requestAllNews(
        q: String?, apiKey: String?
    ): Call<AllNewsResponse> {
        return ApiClient.getApiServices().getAllNews(q, apiKey)
    }

}