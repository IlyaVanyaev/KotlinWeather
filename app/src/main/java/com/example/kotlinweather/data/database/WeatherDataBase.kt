package com.example.kotlinweather.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlinweather.data.Constants
import com.example.kotlinweather.data.model.WeatherDao
import com.example.kotlinweather.data.model.WeatherEntity

@Database(entities = [WeatherEntity::class], version = 1, exportSchema = false)
abstract class WeatherDataBase: RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object{
        private var INSTANCE: WeatherDataBase? = null

        fun getDataBase(context: Context): WeatherDataBase{
            val instance = INSTANCE
            if (instance != null) return instance

            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, WeatherDataBase::class.java, Constants.DATABASE_NAME).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}