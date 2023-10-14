package com.example.kotlinweather.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinweather.R
import com.example.kotlinweather.data.adapters.WeatherAdapter
import com.example.kotlinweather.data.adapters.WeatherDaysAdapter
import com.example.kotlinweather.databinding.FragmentDaysBinding
import com.example.kotlinweather.ui.viewmodel.MainFragmentViewModel


class DaysFragment : Fragment() {

    private lateinit var binding: FragmentDaysBinding
    private lateinit var adapter: WeatherDaysAdapter
    private val vm: MainFragmentViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        vm.getWeatherDays.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    private fun setRecyclerView() = with(binding){
        adapter = WeatherDaysAdapter()
        daysRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        daysRecyclerView.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = DaysFragment()
    }
}