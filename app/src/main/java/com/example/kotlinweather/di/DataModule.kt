package com.example.kotlinweather.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlinweather.data.Constants
import com.example.kotlinweather.data.database.WeatherDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application) = Room.databaseBuilder(application, WeatherDataBase::class.java, Constants.DATABASE_NAME).build()

    @Provides
    fun provideWeatherDao(dataBase: WeatherDataBase) = dataBase.weatherDao()
}