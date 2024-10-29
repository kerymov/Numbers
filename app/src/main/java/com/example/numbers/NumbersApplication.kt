package com.example.numbers

import android.app.Application
import androidx.room.Room
import com.example.numbers.data.database.NumbersDatabase
import com.example.numbers.data.network.NumbersApi
import com.example.numbers.data.network.RetrofitInstance

class NumbersApplication : Application() {

    lateinit var database: NumbersDatabase
    lateinit var api: NumbersApi

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            NumbersDatabase::class.java,
            NumbersDatabase.NAME
        ).build()

        api = RetrofitInstance.numbersApi
    }
}