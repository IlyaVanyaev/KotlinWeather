package com.example.kotlinweather.ui.view.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieDrawable
import com.example.kotlinweather.R
import com.example.kotlinweather.databinding.FragmentSettingsBinding
import com.example.kotlinweather.ui.viewmodel.WeatherViewModel


class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var weatherViewModel: WeatherViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.settingsBackground.setImageResource(R.drawable._0492524)
        binding.settingsTitle.text = "Settings"
        //binding.settingsDelete.setImageResource(R.drawable.delete)
        binding.settingsTextToDelete.text = "Delete all weather info"

        binding.settingsDelete.setOnClickListener {
            playAnimation(0.0f, 0.3f, 1.0f, 0, LottieDrawable.RESTART)
            deleteAllFromDataBase()
        }

    }

    private fun deleteAllFromDataBase(){
        val dialog = AlertDialog.Builder(activity)
        dialog.setPositiveButton("Yeah buddy!") {_,_ ->
            playAnimation(0.3f, 1.0f, 1.0f, 0, LottieDrawable.RESTART)
            weatherViewModel.deleteAll()
            Toast.makeText(activity, "Database cleared", Toast.LENGTH_SHORT).show()
        }
        dialog.setNegativeButton("Negative"){_,_ ->
            playAnimation(0.0f, 0.3f, -1.0f, 0, LottieDrawable.RESTART)
        }
        dialog.setTitle("Delete")
        dialog.setMessage("Are you sure you want to DELETE all weather information from the Database?")
        dialog.setIcon(R.drawable.delete)
        dialog.create().show()
    }



    private fun playAnimation(min:Float, max: Float, speed: Float, repeat: Int, mode:Int){
        binding.settingsDelete.setMinAndMaxProgress(min, max)
        binding.settingsDelete.repeatCount = repeat
        binding.settingsDelete.repeatMode = mode
        binding.settingsDelete.speed = speed
        binding.settingsDelete.playAnimation()
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}