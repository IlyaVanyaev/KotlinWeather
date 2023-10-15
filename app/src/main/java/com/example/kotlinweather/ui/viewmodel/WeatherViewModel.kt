package com.example.kotlinweather.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinweather.data.database.WeatherDataBase
import com.example.kotlinweather.data.model.WeatherEntity
import com.example.kotlinweather.data.repositories.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(application: Application): AndroidViewModel(application) {

    private val getAll: LiveData<List<WeatherEntity>>
    private val repository: WeatherRepository

    init {
        val weatherDao = WeatherDataBase.getDataBase(application).weatherDao()
        repository = WeatherRepository(weatherDao)
        getAll = repository.getAll
    }

    fun insertWeather(weather: WeatherEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertWeather(weather)
        }
    }
}