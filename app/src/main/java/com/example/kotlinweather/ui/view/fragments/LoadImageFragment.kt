package com.example.kotlinweather.ui.view.fragments


import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.airbnb.lottie.LottieDrawable
import com.example.kotlinweather.R
import com.example.kotlinweather.databinding.ActivityMainBinding
import com.example.kotlinweather.databinding.FragmentLoadImageBinding
import com.example.kotlinweather.ui.view.activities.MainActivity
import com.example.kotlinweather.ui.viewmodel.MainFragmentViewModel


class LoadImageFragment : Fragment() {

    private lateinit var binding: FragmentLoadImageBinding
    private val viewModel: MainFragmentViewModel by activityViewModels()
    private lateinit var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>


    private val requiredPermissions = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){
            if (it != null){
                Log.d("PHOTO_PICKER_BABY", "$it")
                binding.loadImageBackground.setImageURI(it)
            }
            else Log.d("PHOTO_PICKER_BABY", "no media selected")
        }
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
            viewModel.playAnimation(binding.loadImageDownload, 0.0f, 1.0f, 10.0f, 1, LottieDrawable.REVERSE)
            viewModel.setBackground(viewModel.getImageFromView(binding.loadImageBackground))
            Toast.makeText(activity, "Background changed", Toast.LENGTH_SHORT).show()
        }



        binding.loadClearText.setOnClickListener {
            binding.loadImageBackground.setImageResource(R.drawable._0492524)
        }

        binding.loadSaveImage.setOnClickListener {

            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            val mimeType = "image/jpg"
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.SingleMimeType(mimeType)))

        }

    }

    private fun getPermission(){
        val granted = requiredPermissions.all { ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED }
        if (granted) Toast.makeText(activity, "granted", Toast.LENGTH_SHORT).show()
        else ActivityCompat.requestPermissions(requireActivity(), requiredPermissions, 1)
    }


    companion object {
        @JvmStatic
        fun newInstance() = LoadImageFragment()
    }
}

