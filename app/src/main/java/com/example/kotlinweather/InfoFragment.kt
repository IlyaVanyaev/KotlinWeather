package com.example.kotlinweather

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinweather.databinding.FragmentInfoBinding
import com.example.kotlinweather.databinding.FragmentMainBinding


class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding.inflate(inflater, container, false)

        binding.infoBackground.setImageResource(R.drawable._0492524)
        binding.infoTitle.text = "KotlinWeather"
        binding.infoDescription.text = "This app was made by MIREA student, Ilya Vanyaev, IKBO-07-21"

        return binding.root
    }


}