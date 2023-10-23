package com.example.kotlinweather.ui.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.airbnb.lottie.LottieDrawable
import com.example.kotlinweather.R
import com.example.kotlinweather.databinding.FragmentInfoBinding
import com.example.kotlinweather.ui.viewmodel.MainFragmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    private val viewModel : MainFragmentViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.infoBackground.setImageResource(R.drawable._0492524)
        binding.infoTitle.text = "KotlinWeather"
        binding.infoDescription.text = "This app was made by MIREA student, Ilya Vanyaev, IKBO-07-21"

        lifecycleScope.launch(Dispatchers.IO) { viewModel.playAnimation(binding.infoIcon, 0.0f, 1.0f, 1.0f, LottieDrawable.INFINITE, LottieDrawable.INFINITE) }
    }


    companion object{
        @JvmStatic
        fun newInstance() = InfoFragment()
    }

}