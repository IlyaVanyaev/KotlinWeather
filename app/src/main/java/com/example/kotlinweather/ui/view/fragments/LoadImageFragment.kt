package com.example.kotlinweather.ui.view.fragments

import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.example.kotlinweather.R
import com.example.kotlinweather.databinding.FragmentLoadImageBinding
import com.example.kotlinweather.ui.viewmodel.MainFragmentViewModel
import com.squareup.picasso.Picasso
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.net.URI


class LoadImageFragment : Fragment() {

    private lateinit var binding: FragmentLoadImageBinding
    private val viewModel: MainFragmentViewModel by activityViewModels()

    private val url = "https://i.pinimg.com/originals/7f/31/13/7f311392f877a110a5eaf3dea02e9c1e.jpg"

    private val REQUARED_PERMISSIONS = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

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
                getPermission()
                val bitmap = getImageFromView(binding.loadShowImage)
                if (bitmap != null) {
                    saveImageToStorage(bitmap)
                    Toast.makeText(activity, "saved", Toast.LENGTH_SHORT).show()
                }
            }
            else Toast.makeText(activity, "nothing to save", Toast.LENGTH_SHORT).show()
        }

    }

    private fun getPermission(){
        val granted = REQUARED_PERMISSIONS.all { ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED }
        if (granted) Toast.makeText(activity, "granted", Toast.LENGTH_SHORT).show()
        else ActivityCompat.requestPermissions(requireActivity(), REQUARED_PERMISSIONS, 1)
    }

    private fun getImageFromView(view : ImageView): Bitmap? {
        var image : Bitmap? = null
        try {
            image = Bitmap.createBitmap(view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(image)
            view.draw(canvas)
        } catch (e: Exception){
            Log.d("IMAGE_ERROR_BABY", e.toString())
        }
        return image
    }

    private fun saveImageToStorage(bitmap: Bitmap){
        val imageName = "${System.currentTimeMillis()}_image_baby.jpg"
        var outputStream: OutputStream? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            activity?.contentResolver?.also {contentResolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, imageName)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                val imageUri : Uri? = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                outputStream = imageUri?.let {it
                    contentResolver.openOutputStream(it)
                }
            }
        }
        else{
            val imageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val file = File(imageDirectory, imageName)
            outputStream = FileOutputStream(file)
        }
        outputStream?.use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
        }
    }



    companion object {
        @JvmStatic
        fun newInstance() = LoadImageFragment()
    }
}