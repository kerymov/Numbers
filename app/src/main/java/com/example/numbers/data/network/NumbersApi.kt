package com.example.numbers.data.network

import com.example.numbers.data.network.models.FactDto
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApi {

    @GET("/{username}")
    suspend fun getFactAboutNumber(@Path("username") number: Number): FactDto

    @GET("/random/math")
    suspend fun getFactAboutRandomNumber(): FactDto
}