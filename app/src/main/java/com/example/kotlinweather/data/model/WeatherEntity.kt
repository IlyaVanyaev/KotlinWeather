package com.example.kotlinweather.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "temperature")
    var temperature: String,
    @ColumnInfo(name = "location")
    var location: String,
    @ColumnInfo(name = "condition")
    var condition: String,
    @ColumnInfo(name = "max_temperature")
    var maxTemperature: String,
    @ColumnInfo(name = "min_temperature")
    var minTemperature: String,
)
