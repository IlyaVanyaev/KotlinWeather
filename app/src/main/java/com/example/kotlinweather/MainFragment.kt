package com.example.kotlinweather

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinweather.databinding.FragmentMainBinding
import java.text.SimpleDateFormat
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
        binding.mainInfo.setOnClickListener { activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.main_container, InfoFragment.newInstance())?.commit() }

        binding.mainSettings.setImageResource(R.drawable.settings)
        binding.mainSettings.setOnClickListener { activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.main_container, SettingsFragment.newInstance())?.commit() }

        val timer = object : CountDownTimer(1000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.mainTime.text = getTime()
                //binding.mainDate.text = getDate()
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
        return ""
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}