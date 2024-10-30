package com.example.numbers.data.dataSource

import com.example.numbers.data.database.FactEntity
import com.example.numbers.data.database.NumbersDatabase
import kotlinx.coroutines.flow.Flow

class RoomDataSource(
    private val database: NumbersDatabase
) {

    suspend fun insertFact(fact: FactEntity) {
        database.getFactDao()
            .insertFact(fact)
    }

    fun getAllFacts() : Flow<List<FactEntity>> {
        return database.getFactDao().getAllFacts()
    }
}