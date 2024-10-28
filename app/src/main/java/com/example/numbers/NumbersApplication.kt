package com.example.numbers

import android.app.Application
import androidx.room.Room
import com.example.numbers.data.database.NumbersDatabase

class NumbersApplication : Application() {

    lateinit var database: NumbersDatabase

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            NumbersDatabase::class.java,
            NumbersDatabase.NAME
        ).build()
    }
}