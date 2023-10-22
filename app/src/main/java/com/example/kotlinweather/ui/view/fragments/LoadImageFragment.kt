package com.example.kotlinweather.ui.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.example.kotlinweather.R
import com.example.kotlinweather.databinding.FragmentLoadImageBinding
import com.example.kotlinweather.ui.viewmodel.MainFragmentViewModel
import com.squareup.picasso.Picasso


class LoadImageFragment : Fragment() {

    private lateinit var binding: FragmentLoadImageBinding
    private val viewModel: MainFragmentViewModel by activityViewModels()

    private val url = "https://i.pinimg.com/originals/7f/31/13/7f311392f877a110a5eaf3dea02e9c1e.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoadImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loadImageDownload.setOnClickListener {
            if (binding.loadSearchImage.text.isEmpty()){
                viewModel.playAnimation(binding.loadImageDownload, 0.0f, 1.0f, 10.0f, 1, LottieDrawable.REVERSE)
                viewModel.downloadImage(url, binding.loadShowImage)
            }
            else{
                viewModel.playAnimation(binding.loadImageDownload, 0.0f, 1.0f, 10.0f, 1, LottieDrawable.REVERSE)
                viewModel.downloadImage(binding.loadSearchImage.text.toString(), binding.loadShowImage)
            }
        }

        binding.loadShowImage.setOnClickListener {
            binding.loadShowImage.setImageDrawable(null)
        }

        binding.loadClearText.setOnClickListener { binding.loadSearchImage.text.clear() }

        binding.loadSaveImage.setOnClickListener {
            if (binding.loadShowImage.drawable != null){
                Toast.makeText(activity, "saved", Toast.LENGTH_SHORT).show()
            }
            else Toast.makeText(activity, "nothing to save", Toast.LENGTH_SHORT).show()
        }

    }



    companion object {
        @JvmStatic
        fun newInstance() = LoadImageFragment()
    }
}