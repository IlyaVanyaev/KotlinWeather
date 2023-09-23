package com.example.kotlinweather.ui.viewmodel

import android.annotation.SuppressLint
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainFragmentViewModel : ViewModel() {

    private lateinit var timer: CountDownTimer

    private var time = MutableLiveData<String>()
    val getTime : LiveData<String> = time

    private var date = MutableLiveData<String>()
    val getDate : LiveData<String> = date

    private var day = MutableLiveData<String>()
    val getDay : LiveData<String> = day


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
        time.value = SimpleDateFormat("HH:mm ").format(Date())
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDate(){
        date.value = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMM, dd"))
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDay(){
        day.value = SimpleDateFormat("EEEE").format(Date())
    }

}