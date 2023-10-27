package com.example.kotlinweather.ui.view.fragments



import android.net.Uri
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
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.Glide
import com.example.kotlinweather.R
import com.example.kotlinweather.databinding.FragmentLoadImageBinding
import com.example.kotlinweather.ui.viewmodel.MainFragmentViewModel


class LoadImageFragment : Fragment() {

    private lateinit var binding: FragmentLoadImageBinding
    private val viewModel: MainFragmentViewModel by activityViewModels()
    private lateinit var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>
    private var photoUri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){
            if (it != null){
                Log.d("PHOTO_PICKER_BABY", "$it")
                photoUri = it
                Glide.with(this).load(photoUri).into(binding.loadImageBackground)
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
            viewModel.setBackground(photoUri)
            Toast.makeText(activity, "Background changed", Toast.LENGTH_SHORT).show()
        }



        binding.loadClearText.setOnClickListener {
            binding.loadImageBackground.setImageResource(R.drawable._0492524)
        }

        binding.loadSaveImage.setOnClickListener {

            //pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

            val mimeType = "image/*"
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.SingleMimeType(mimeType)))

        }

        binding.loadImageBackground.setOnClickListener {
            if (binding.loadImageDownload.isVisible){
                binding.loadImageDownload.visibility = View.INVISIBLE
                binding.loadSaveImage.visibility = View.INVISIBLE
                binding.loadClearText.visibility = View.INVISIBLE
            }
            else{
                binding.loadImageDownload.visibility = View.VISIBLE
                binding.loadSaveImage.visibility = View.VISIBLE
                binding.loadClearText.visibility = View.VISIBLE
            }
        }

    }



    companion object {
        @JvmStatic
        fun newInstance() = LoadImageFragment()
    }
}

