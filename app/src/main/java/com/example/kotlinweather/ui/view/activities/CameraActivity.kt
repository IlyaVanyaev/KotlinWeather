package com.example.kotlinweather.ui.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinweather.R
import com.example.kotlinweather.databinding.ActivityCameraBinding
import com.example.kotlinweather.databinding.ActivityMainBinding
import com.example.kotlinweather.ui.viewmodel.CameraActivityViewModel

class CameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding
    private lateinit var vm: CameraActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm = ViewModelProvider(this)[CameraActivityViewModel::class.java]

        binding.cameraBack.setImageResource(R.drawable.back)
        binding.cameraImages.setImageResource(R.drawable.image)
        binding.cameraTakePhoto.setImageResource(R.drawable.photo)

        binding.cameraBack.setOnClickListener { finish() }

        binding.cameraTakePhoto.setOnClickListener { vm.getMessage() }


    }

    override fun onBackPressed() {
        finish()
    }
}