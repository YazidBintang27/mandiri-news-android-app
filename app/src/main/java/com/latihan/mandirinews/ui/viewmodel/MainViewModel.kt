package com.latihan.mandirinews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.latihan.mandirinews.models.AllNewsResponse
import com.latihan.mandirinews.models.HeadlineNewsResponse
import com.latihan.mandirinews.repository.NewsRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    private val repository = NewsRepositoryImpl()
    var status = MutableLiveData<Boolean?>()

    val headlineNewsObserver: MutableLiveData<HeadlineNewsResponse> = MutableLiveData()
    val allNewsObserver: MutableLiveData<AllNewsResponse> = MutableLiveData()

    fun requestHeadlineNews(
        country: String?,
        apiKey: String?
    ): LiveData<HeadlineNewsResponse> {
        repository.requestHeadlineNews(country, apiKey).enqueue(object :
            Callback<HeadlineNewsResponse> {
            override fun onResponse(
                call: Call<HeadlineNewsResponse>,
                response: Response<HeadlineNewsResponse>
            ) {
                if (response.isSuccessful) headlineNewsObserver.value = response.body()
                else status.value = true
            }

            override fun onFailure(call: Call<HeadlineNewsResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })
        return headlineNewsObserver
    }

    fun requestAllNews(
        q: String?,
        apiKey: String?
    ): LiveData<AllNewsResponse> {
        repository.requestAllNews(q, apiKey).enqueue(object :
            Callback<AllNewsResponse> {
            override fun onResponse(
                call: Call<AllNewsResponse>,
                response: Response<AllNewsResponse>
            ) {
                if (response.isSuccessful) allNewsObserver.value = response.body()
                else status.value = true
            }

            override fun onFailure(call: Call<AllNewsResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })
        return allNewsObserver
    }

}