package com.example.kotlinweather.ui.view.fragments


import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlinweather.R
import com.example.kotlinweather.data.Constants
import com.example.kotlinweather.data.adapters.ViewPagerAdapter
import com.example.kotlinweather.data.model.WeatherEntity
import com.example.kotlinweather.databinding.FragmentMainBinding
import com.example.kotlinweather.ui.viewmodel.MainFragmentViewModel
import com.example.kotlinweather.ui.viewmodel.WeatherViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    //private lateinit var vm: MainFragmentViewModel
    private val vm: MainFragmentViewModel by activityViewModels()
    private lateinit var vpAdapter: ViewPagerAdapter

    private lateinit var weatherViewModel: WeatherViewModel

    private val fragmentList = listOf(HoursFragment.newInstance(), DaysFragment.newInstance())
    private val tabList = listOf("Hours", "Days")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //vm = ViewModelProvider(this)[MainFragmentViewModel::class.java]
        weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)


        //vm.getWeather()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setViews()

        vm.getTimeAndDate()



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
            binding.mainMaxMinTemperature.text = "${it.maxTemperature.dropLast(2)}/${it.minTemperature.dropLast(2)}\u00B0"
            //Log.d("HOURS", "Hours: ${it.hours}")
            insertToDataBase()
        }

    }

    private fun setViews() = with(binding){
        mainBackground.setImageResource(R.drawable._0492524)
        mainInfo.setImageResource(R.drawable.info)
        mainSettings.setImageResource(R.drawable.settings)
        mainUpdate.setImageResource(R.drawable.autorenew)
        mainWeatherIcon.setImageResource(R.drawable.weather)

        setViewPagerAdapter()
        getFromDataBase()
    }

    private fun setViewPagerAdapter() = with(binding){
        vpAdapter = ViewPagerAdapter(activity as FragmentActivity, fragmentList)
        mainViewPager.adapter = vpAdapter
        TabLayoutMediator(mainTab, mainViewPager){
            tab, position -> tab.text = tabList[position]
        }.attach()
        mainTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) mainViewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        mainViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mainTab.selectTab(mainTab.getTabAt(position))
                if (position == 1) mainTab.setSelectedTabIndicator(R.drawable.tab_indicator)
                else mainTab.setSelectedTabIndicator(R.drawable.tab_dark)
            }
        })
    }


    private fun insertToDataBase() = with(binding){
        val temperature = mainWeather.text.toString()
        val location = mainCountry.text.toString()
        val condition = mainCast.text.toString()
        var maxT: String = ""
        var minT: String = ""
        for (i in mainMaxMinTemperature.text){
            if (i == '/') break
            maxT += i
        }
        minT = mainMaxMinTemperature.text.drop(maxT.length + 1).toString().dropLast(1)

        val weatherEntity = WeatherEntity(1, temperature, location, condition, maxT, minT)
        weatherViewModel.insertWeather(weatherEntity)
        //Log.d("DATABASE_BABY", "weather added")
    }


    @SuppressLint("SetTextI18n")
    private fun getFromDataBase() = with(binding){

        weatherViewModel.getAll.observe(viewLifecycleOwner){
            //Log.d("GET_DATA_BABY", it.last().toString())

            if (it.isNotEmpty()) {
                mainWeather.text = it.last().temperature
                mainCountry.text = it.last().location
                mainCast.text = it.last().condition
                mainMaxMinTemperature.text = "${it.last().maxTemperature}/${it.last().minTemperature}\u00B0"
            }

        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}