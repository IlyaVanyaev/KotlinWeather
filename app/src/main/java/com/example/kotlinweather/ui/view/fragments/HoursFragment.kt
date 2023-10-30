package com.example.kotlinweather.ui.view.fragments

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinweather.data.adapters.WeatherAdapter
import com.example.kotlinweather.databinding.FragmentHoursBinding
import com.example.kotlinweather.ui.view.composable.HoursList
import com.example.kotlinweather.ui.viewmodel.MainFragmentViewModel


class HoursFragment : Fragment() {

    private lateinit var binding: FragmentHoursBinding


    //private lateinit var vm: MainFragmentViewModel
    private val vm: MainFragmentViewModel by activityViewModels()
    private lateinit var adapter: WeatherAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //vm = ViewModelProvider(this)[MainFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        binding = FragmentHoursBinding.inflate(inflater, container, false)
//
//        return binding.root

        return ComposeView(requireContext()).apply {
            setContent {
                Column() {
                    HoursList(context = requireContext())
                }
            }
        }
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//        setRecyclerView()
//        vm.getWeather.observe(viewLifecycleOwner){
//            //Log.d("HOURS_BABY", "Hours: ${it.hours}")
//            vm.getHours(it)
//            adapter.submitList(vm.getWeatherHours.value)
//        }
//
//    }
//
//    private fun setRecyclerView() = with(binding){
//        hoursRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
//        adapter = WeatherAdapter()
//        hoursRecyclerView.adapter = adapter
//    }



    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}