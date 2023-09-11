package com.example.kotlinweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.main_container, MainFragment.newInstance()).commit()
    }

    override fun onBackPressed() {
        supportFragmentManager.beginTransaction().replace(R.id.main_container, MainFragment.newInstance()).commit()
    }
}