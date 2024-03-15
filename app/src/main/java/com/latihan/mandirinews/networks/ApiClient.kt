package com.latihan.mandirinews.networks

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.latihan.mandirinews.util.AppConstant.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("Test-App-Version", "1.0")
                    builder.header("X-Platform", "Android")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }.build()
    }

    private fun provideRetrofit(): Retrofit {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setLenient()
            .setPrettyPrinting()
            .create()

        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(provideOkHttpClient())
        return builder.build()
    }

    fun getApiServices(): ApiServices = provideRetrofit().create(ApiServices::class.java)

}