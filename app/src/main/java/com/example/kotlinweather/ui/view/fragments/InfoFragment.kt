package com.example.kotlinweather.ui.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.example.kotlinweather.ui.view.composable.InfoCard


class InfoFragment : Fragment() {



    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        return ComposeView(requireContext()).apply {
            setContent{
                InfoCard()
            }
        }
    }





    companion object{
        @JvmStatic
        fun newInstance() = InfoFragment()
    }

}
