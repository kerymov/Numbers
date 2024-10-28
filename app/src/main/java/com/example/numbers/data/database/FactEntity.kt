package com.example.numbers.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.numbers.data.network.models.Fact
import com.example.numbers.data.network.models.Number

@Entity(tableName = "search_history")
data class FactEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    val number: Int,
    val fact: String
)
