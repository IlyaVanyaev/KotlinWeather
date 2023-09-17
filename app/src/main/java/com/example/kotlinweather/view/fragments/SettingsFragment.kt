package com.example.kotlinweather.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinweather.R
import com.example.kotlinweather.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    lateinit var binding: FragmentSettingsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.settingsBackground.setImageResource(R.drawable._0492524)
        binding.settingsTitle.text = "Settings"

        return binding.root
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}