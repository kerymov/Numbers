package com.example.numbers.data.network

import com.example.numbers.data.network.models.FactResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApi {

    @GET("/{username}")
    suspend fun getFactAboutNumber(@Path("username") number: Number): FactResponse

    @GET("/random/math")
    suspend fun getFactAboutRandomNumber(): FactResponse
}