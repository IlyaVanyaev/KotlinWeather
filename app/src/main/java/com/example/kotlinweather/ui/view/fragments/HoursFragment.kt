package com.example.kotlinweather.ui.view.fragments

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinweather.data.adapters.WeatherAdapter
import com.example.kotlinweather.databinding.FragmentHoursBinding
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
        binding = FragmentHoursBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setRecyclerView()
        vm.getHoursLiveData.observe(viewLifecycleOwner){
            Log.d("HOURS_BABY", "Hours: ${it.hours}")
        }

    }

    private fun setRecyclerView() = with(binding){
        mainRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        adapter = WeatherAdapter()
        mainRecyclerView.adapter = adapter
    }

    fun getAdapter(): WeatherAdapter{
        return adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}