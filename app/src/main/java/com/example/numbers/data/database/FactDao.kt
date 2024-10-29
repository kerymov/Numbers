package com.example.numbers.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FactDao {

    @Insert
    suspend fun insertFact(fact: FactEntity)

    @Query("SELECT * FROM search_history")
    fun getAllFacts(): Flow<List<FactEntity>>

    @Query("SELECT * FROM search_history WHERE id = :id")
    fun getFactById(id: Long): Flow<FactEntity>
}