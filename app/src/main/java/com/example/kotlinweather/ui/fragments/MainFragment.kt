package com.example.kotlinweather.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.kotlinweather.R
import com.example.kotlinweather.databinding.FragmentMainBinding
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.mainBackground.setImageResource(R.drawable._0492524)

        binding.mainInfo.setImageResource(R.drawable.info)
        binding.mainSettings.setImageResource(R.drawable.settings)

        val nav = findNavController()
        binding.mainInfo.setOnClickListener { nav.navigate(R.id.action_mainFragment_to_infoFragment) }
        binding.mainSettings.setOnClickListener { nav.navigate(R.id.action_mainFragment_to_settingsFragment) }

        val timer = object : CountDownTimer(1000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.mainTime.text = getTime()
                binding.mainDate.text = getDate()

            }

            override fun onFinish() {
                this.start()
            }
        }

        timer.start()

        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    private fun getTime(): String{
        val time = SimpleDateFormat("HH:mm")
        return time.format(Date())
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDate(): String{
        val date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM, dd yyy"))
        return date
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}