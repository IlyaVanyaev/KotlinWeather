package com.example.kotlinweather.ui.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel

class CameraActivityViewModel (application: Application) : AndroidViewModel(application) {

    fun getMessage(){
        Toast.makeText(getApplication(), "work", Toast.LENGTH_SHORT).show()
    }

}