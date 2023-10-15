package com.example.kotlinweather.ui.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
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
        binding.settingsDelete.setImageResource(R.drawable.delete)
        binding.settingsTextToDelete.text = "Delete all weather info"

        binding.settingsDelete.setOnClickListener {
            deleteAllFromDataBase()
        }

    }

    private fun deleteAllFromDataBase(){
        weatherViewModel.deleteAll()
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}