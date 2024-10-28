package com.example.numbers.data.network

import com.example.numbers.data.network.models.Fact
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApi {

    @GET("/{username}")
    suspend fun getFactAboutNumber(@Path("username") number: Number): Fact

    @GET("/random/math")
    suspend fun getFactAboutRandomNumber(): Fact
}