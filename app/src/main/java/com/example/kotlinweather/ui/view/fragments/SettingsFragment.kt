package com.example.kotlinweather.ui.view.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ContentInfoCompat.Flags
import com.example.kotlinweather.R
import com.example.kotlinweather.databinding.FragmentSettingsBinding
import com.example.kotlinweather.ui.view.activities.CameraActivity


class SettingsFragment : Fragment() {

    lateinit var binding: FragmentSettingsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.settingsBackground.setImageResource(R.drawable._0492524)
        binding.settingsTitle.text = "Settings"
        binding.settingsTakePhoto.text = "Take photo"
        binding.settingsCamera.setImageResource(R.drawable.camera)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.settingsCamera.setOnClickListener { startActivity(Intent(this.requireActivity(), CameraActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)) }
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}