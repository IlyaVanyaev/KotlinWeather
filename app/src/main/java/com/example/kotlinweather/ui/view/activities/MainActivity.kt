package com.example.kotlinweather.ui.view.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.kotlinweather.R
import com.example.kotlinweather.databinding.ActivityMainBinding
import com.example.kotlinweather.ui.viewmodel.MainFragmentViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainFragmentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        viewModel = ViewModelProvider(this)[MainFragmentViewModel::class.java]

        viewModel.getBackgroundUri.observe(this){
            if (it != null) Glide.with(this).load(it).into(binding.activityMainBackground)
            else binding.activityMainBackground.setImageResource(R.drawable._0492524)
        }
    }

}