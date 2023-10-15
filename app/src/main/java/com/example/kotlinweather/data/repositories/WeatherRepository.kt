package com.example.kotlinweather.data.repositories

import androidx.lifecycle.LiveData
import com.example.kotlinweather.data.model.WeatherDao
import com.example.kotlinweather.data.model.WeatherEntity

class WeatherRepository(private val weatherDao: WeatherDao) {
    val getAll: LiveData<List<WeatherEntity>> = weatherDao.getAll()

    suspend fun insertWeather(weather: WeatherEntity){
        weatherDao.insertWeather(weather)
    }

    suspend fun deleteAll(){
        weatherDao.deleteAll()
    }
}