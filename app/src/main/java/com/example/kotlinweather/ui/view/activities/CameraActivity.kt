package com.example.kotlinweather.ui.view.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinweather.R
import com.example.kotlinweather.data.Constants
import com.example.kotlinweather.databinding.ActivityCameraBinding
import com.example.kotlinweather.ui.view.fragments.PhotoFragment
import com.example.kotlinweather.ui.viewmodel.CameraActivityViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding
    private lateinit var vm: CameraActivityViewModel
    private var imageCapture:ImageCapture? = null
    private lateinit var cameraExecutor:ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm = ViewModelProvider(this)[CameraActivityViewModel::class.java]

        getPermission()

        cameraExecutor = Executors.newSingleThreadExecutor()

        binding.cameraBack.setImageResource(R.drawable.back)
        binding.cameraImages.setImageResource(R.drawable.image)
        binding.cameraTakePhoto.setImageResource(R.drawable.photo)

        binding.cameraBack.setOnClickListener { finish() }

        binding.cameraTakePhoto.setOnClickListener { takePhoto() }

        binding.cameraImages.setOnClickListener {
            PhotoFragment().show(supportFragmentManager, PhotoFragment().tag)
        }

    }

    override fun onBackPressed() {
        finish()
    }


    private fun getPermission(){
        if (vm.permissionGranted()) startCamera()
        else ActivityCompat.requestPermissions(this, Constants.REQUIRED_PERMISSIONS, Constants.REQUEST_CODE)
    }

    private fun startCamera(){
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also { it.setSurfaceProvider(binding.cameraPreview.surfaceProvider) }
            imageCapture = ImageCapture.Builder().build()
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
            }catch (e:Exception){
                Log.d(Constants.TAG, "Camera fail", e)
            }
        }, ContextCompat.getMainExecutor(this))
    }



    private fun takePhoto(){

        val date = SimpleDateFormat(Constants.FILE_FORMAT, Locale.getDefault()).format(Date())
        val file = File(getExternalFilesDir("photos"), "date.txt")
        file.appendText(date+"\n")
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == Constants.REQUEST_CODE){

            startCamera()

        }else Toast.makeText(this, "permission is not granted", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}