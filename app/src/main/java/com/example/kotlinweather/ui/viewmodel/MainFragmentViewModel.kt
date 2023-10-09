package com.example.kotlinweather.ui.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.os.CountDownTimer
import android.provider.SyncStateContract.Constants
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.kotlinweather.data.model.WeatherModel
import com.example.kotlinweather.ui.view.fragments.MainFragment
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var timer: CountDownTimer

    private var time = MutableLiveData<String>()
    val getTime : LiveData<String> = time

    private var date = MutableLiveData<String>()
    val getDate : LiveData<String> = date

    private var day = MutableLiveData<String>()
    val getDay : LiveData<String> = day

    private var weather = MutableLiveData<WeatherModel>()
    val getWeather : LiveData<WeatherModel> = weather


    fun getTimeAndDate(){
        timer = object : CountDownTimer(1000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                getTime()
                getDate()
                getDay()
            }

            override fun onFinish() {}
        }.start()
    }


    @SuppressLint("SimpleDateFormat")
    private fun getTime(){
        time.value = SimpleDateFormat("HH:mm").format(Date())
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDate(){
        date.value = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMM, dd"))
    }

    @SuppressLint("SimpleDateFormat")
    fun getDay(){
        day.value = SimpleDateFormat("EEEE").format(Date())
    }


    fun getWeather(api_key:String,city:String, days:String){
        val url = "https://api.weatherapi.com/v1/forecast.json?key=$api_key&q=$city&days=$days&aqi=no&alerts=no"
        val queue = Volley.newRequestQueue(getApplication())
        val request = StringRequest(Request.Method.GET, url, {response -> parseWeather(response)}, {error -> Log.d("Error response", error.toString())})

        queue.add(request)
    }

    private fun parseWeather(response: String){
        val json = JSONObject(response)
        val weatherModel = WeatherModel(
            json.getJSONObject("location").getString("name"),
            json.getJSONObject("current").getJSONObject("condition").getString("text"),
            json.getJSONObject("current").getString("temp_c"),
            json.getJSONObject("current").getJSONObject("condition").getString("icon")
        )
        println(response)
        weather.value = weatherModel
    }

}