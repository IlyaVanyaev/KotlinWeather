package com.example.kotlinweather.ui.view.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kotlinweather.R
import com.example.kotlinweather.data.Constants
import com.example.kotlinweather.databinding.FragmentMainBinding
import com.example.kotlinweather.ui.viewmodel.MainFragmentViewModel
import com.squareup.picasso.Picasso


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var vm: MainFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm = ViewModelProvider(this)[MainFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.mainBackground.setImageResource(R.drawable._0492524)
        binding.mainInfo.setImageResource(R.drawable.info)
        binding.mainSettings.setImageResource(R.drawable.settings)
        binding.mainUpdate.setImageResource(R.drawable.autorenew)
        binding.mainWeatherIcon.setImageResource(R.drawable.weather)



        vm.getTimeAndDate()
        //vm.getWeather()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val nav = findNavController()
        binding.mainInfo.setOnClickListener { nav.navigate(R.id.action_mainFragment_to_infoFragment) }
        binding.mainSettings.setOnClickListener { nav.navigate(R.id.action_mainFragment_to_settingsFragment) }

        vm.getTime.observe(viewLifecycleOwner, androidx.lifecycle.Observer { binding.mainTime.text = it })
        vm.getDate.observe(viewLifecycleOwner, androidx.lifecycle.Observer { binding.mainDate.text = it })
        vm.getDay.observe(viewLifecycleOwner, androidx.lifecycle.Observer { binding.mainDay.text = it })


        binding.mainUpdate.setOnClickListener { vm.getWeather(Constants.API_KEY, Constants.MOSCOW, Constants.THREE_DAYS) }

        vm.getWeather.observe(viewLifecycleOwner){
            binding.mainCountry.text = it.city
            binding.mainCast.text = it.condition
            binding.mainWeather.text = it.recentTemperature.dropLast(2) + "\u00B0"
            Picasso.get().load("https:" + it.weatherImage).into(binding.mainWeatherIcon)
            binding.mainMaxMinTemperature.text = "${it.maxTemperature.dropLast(2)}/${it.minTemperature.dropLast(2)}"
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}