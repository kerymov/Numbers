package com.example.numbers.data.repository

import com.example.numbers.data.database.FactEntity
import com.example.numbers.data.database.NumbersDatabase
import com.example.numbers.data.mappers.mapToFact
import com.example.numbers.data.models.Fact
import com.example.numbers.data.network.NumbersApi
import com.example.numbers.data.network.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FactsRepository(
    private val database: NumbersDatabase,
    private val api: NumbersApi = RetrofitInstance.numbersApi
) {

    suspend fun getFactAboutNumber(number: Int) {
        val factResponse = api.getFactAboutNumber(number)
        saveFact(
            fact = FactEntity(
                number = factResponse.number,
                fact = factResponse.text
            )
        )
    }

    suspend fun getFactAboutRandomNumber() {
        val fact = api.getFactAboutRandomNumber()
        saveFact(
            fact = FactEntity(
                number = fact.number,
                fact = fact.text
            )
        )
    }

    suspend fun getFactById(id: Long) { }

    fun getAllFacts(): Flow<List<Fact>> {
        return database.getFactDao()
            .getAllFacts()
            .map { facts ->
                facts.map { it.mapToFact() }
            }
    }

    private suspend fun saveFact(fact: FactEntity) {
        database.getFactDao()
            .insertFact(fact)
    }
}