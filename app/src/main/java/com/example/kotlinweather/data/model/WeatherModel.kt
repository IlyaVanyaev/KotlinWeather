package com.example.kotlinweather.data.model

data class WeatherModel(
    val city:String,
    val condition:String,
    val recentTemperature:String,
    val weatherImage:String,
    val maxTemperature:String,
    val minTemperature:String,
    val date:String,
    val hours:String
)
