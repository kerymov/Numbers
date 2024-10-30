package com.example.numbers.data.dataSource

import com.example.numbers.data.network.NetworkResult
import com.example.numbers.data.network.NumbersApi
import com.example.numbers.data.network.handleApi
import com.example.numbers.data.network.models.FactResponse

class RetrofitDataSource(
    private val api: NumbersApi
) {

    suspend fun getFactAboutNumber(number: Long): NetworkResult<FactResponse> {
        return handleApi { api.getFactAboutNumber(number) }
    }

    suspend fun getFactAboutRandomNumber(): NetworkResult<FactResponse> {
        return handleApi { api.getFactAboutRandomNumber() }
    }
}