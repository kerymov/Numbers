package com.example.numbers.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_history")
data class FactEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val number: Int,
    val text: String,
    val found: Boolean
)
