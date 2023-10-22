package com.example.kotlinweather.ui.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.example.kotlinweather.R
import com.example.kotlinweather.databinding.FragmentLoadImageBinding
import com.squareup.picasso.Picasso


class LoadImageFragment : Fragment() {

    private lateinit var binding: FragmentLoadImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoadImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loadImageDownload.setOnClickListener {
            if (binding.loadSearchImage.text.toString() == ""){
                playAnimation(binding.loadImageDownload, 0.0f, 1.0f, 10.0f, 1, LottieDrawable.REVERSE)
                Picasso.get().load("https://i.pinimg.com/originals/7f/31/13/7f311392f877a110a5eaf3dea02e9c1e.jpg").into(binding.loadShowImage)
            }
            else{
                playAnimation(binding.loadImageDownload, 0.0f, 1.0f, 10.0f, 1, LottieDrawable.REVERSE)
                Picasso.get().load(binding.loadSearchImage.text.toString()).into(binding.loadShowImage)
            }
        }

        binding.loadShowImage.setOnClickListener {
            binding.loadShowImage.setImageDrawable(null)
            //Log.d("SEARCH_BABY", binding.loadSearchImage.text.toString())
        }

    }

    private fun playAnimation(icon: LottieAnimationView, min:Float, max: Float, speed: Float, repeat: Int, mode:Int) = with(binding){
        loadImageDownload.setMinAndMaxProgress(min, max)
        loadImageDownload.repeatCount = repeat
        loadImageDownload.repeatMode= mode
        loadImageDownload.speed = speed
        loadImageDownload.playAnimation()
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoadImageFragment()
    }
}