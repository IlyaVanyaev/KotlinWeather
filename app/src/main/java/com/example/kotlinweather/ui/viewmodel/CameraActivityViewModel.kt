package com.example.kotlinweather.ui.viewmodel

import android.app.Application
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.kotlinweather.data.Constants
import com.example.kotlinweather.ui.view.activities.CameraActivity

class CameraActivityViewModel (application: Application) : AndroidViewModel(application) {


    fun setMessage(message:String){
        Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show()
    }


    fun permissionGranted() = Constants.REQUIRED_PERMISSIONS.all { ContextCompat.checkSelfPermission(getApplication(), it) == PackageManager.PERMISSION_GRANTED}



}