package com.example.kotlinweather.data.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: WeatherEntity)

    @Query("SELECT * FROM weather_table")
    fun getAll(): LiveData<List<WeatherEntity>>

    @Query("DELETE FROM weather_table")
    suspend fun deleteAll()
}