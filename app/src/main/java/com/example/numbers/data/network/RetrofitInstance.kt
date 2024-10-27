package com.example.numbers.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val httpLoggingInterceptor =  HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient().newBuilder()
        .addInterceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            requestBuilder
                .addHeader("Content-Type", "application/json")
                .addHeader("accept", "*/*")
            chain.proceed(requestBuilder.build())
        }
        .addInterceptor(httpLoggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://numbersapi.com")
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val numbersApi = retrofit.create(NumbersApi::class.java)
}