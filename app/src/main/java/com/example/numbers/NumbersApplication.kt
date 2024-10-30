package com.example.numbers

import android.app.Application
import androidx.room.Room
import com.example.numbers.data.dataSource.RetrofitDataSource
import com.example.numbers.data.dataSource.RoomDataSource
import com.example.numbers.data.database.NumbersDatabase
import com.example.numbers.data.network.NumbersApi
import com.example.numbers.data.network.RetrofitInstance

class NumbersApplication : Application() {

    private lateinit var database: NumbersDatabase
    private lateinit var api: NumbersApi

    lateinit var roomDataSource: RoomDataSource
    lateinit var retrofitDataSource: RetrofitDataSource

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            NumbersDatabase::class.java,
            NumbersDatabase.NAME
        ).build()

        api = RetrofitInstance.numbersApi

        roomDataSource = RoomDataSource(database)
        retrofitDataSource = RetrofitDataSource(api)
    }
}