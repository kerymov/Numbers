package com.example.numbers.data.repository

import android.util.Log
import com.example.numbers.data.dataSource.RetrofitDataSource
import com.example.numbers.data.dataSource.RoomDataSource
import com.example.numbers.data.database.FactEntity
import com.example.numbers.data.mappers.mapToFact
import com.example.numbers.data.models.Fact
import com.example.numbers.data.network.NetworkResult
import com.example.numbers.data.network.models.FactResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FactsRepository(
    private val roomDataSource: RoomDataSource,
    private val retrofitDataSource: RetrofitDataSource
) {

    suspend fun getFactAboutNumber(number: Int) {
        val result = retrofitDataSource.getFactAboutNumber(number)
        handleNetworkResult(result)
    }

    suspend fun getFactAboutRandomNumber() {
        val result = retrofitDataSource.getFactAboutRandomNumber()
        handleNetworkResult(result)
    }

    fun getAllFacts(): Flow<List<Fact>> {
        return roomDataSource.getAllFacts()
            .map { facts ->
                facts.map { it.mapToFact() }
            }
    }

    private suspend fun handleNetworkResult(result: NetworkResult<FactResponse>) {
        when (result) {
            is NetworkResult.Success -> {
                val factResponse = result.data
                saveFact(
                    fact = FactEntity(
                        number = factResponse.number,
                        fact = factResponse.text
                    )
                )
            }
            is NetworkResult.Error -> {
                Log.e("Network", "Network exception: ${result.e.message}")
                throw result.e
            }
        }
    }

    private suspend fun saveFact(fact: FactEntity) {
        roomDataSource.insertFact(fact)
    }
}