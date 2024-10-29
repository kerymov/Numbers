package com.example.numbers.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FactDao {

    @Insert
    fun insertFact(fact: FactEntity)

    @Query("SELECT * FROM search_history WHERE id = :id")
    fun getFactById(id: Long): FactEntity
}