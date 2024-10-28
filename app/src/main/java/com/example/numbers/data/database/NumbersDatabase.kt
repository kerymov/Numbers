package com.example.numbers.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FactEntity::class],
    version = 1
)
abstract class NumbersDatabase : RoomDatabase() {

    companion object {
        const val NAME = "Numbers_DB"
    }

    abstract fun getFactDao(): FactDao
}