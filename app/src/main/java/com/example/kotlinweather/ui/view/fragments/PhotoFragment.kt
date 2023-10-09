package com.example.kotlinweather.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinweather.R
import com.example.kotlinweather.data.adapters.PhotoAdapter
import com.example.kotlinweather.databinding.FragmentPhotoBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class PhotoFragment : BottomSheetDialogFragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PhotoAdapter
    private lateinit var binding: FragmentPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.transparent)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPhotoBinding.inflate(inflater, container, false)

        recyclerView = binding.photoRecycler
        adapter = PhotoAdapter(inflater.context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(inflater.context)
        adapter.setDate()

        return binding.root
    }

    companion object {
        fun newInstance() = PhotoFragment
    }
}