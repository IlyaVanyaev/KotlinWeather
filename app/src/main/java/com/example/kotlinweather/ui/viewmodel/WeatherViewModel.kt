package com.example.kotlinweather.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinweather.data.database.WeatherDataBase
import com.example.kotlinweather.data.model.WeatherEntity
import com.example.kotlinweather.data.repositories.WeatherRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor (application: Application, private val repository: WeatherRepository): AndroidViewModel(application) {

    val getAll: LiveData<List<WeatherEntity>> = repository.getAll

    fun insertWeather(weather: WeatherEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertWeather(weather)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAll()
        }
    }
}