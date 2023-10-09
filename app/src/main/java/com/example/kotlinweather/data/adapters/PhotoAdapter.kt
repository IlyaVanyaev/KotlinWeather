package com.example.kotlinweather.data.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinweather.R
import com.example.kotlinweather.databinding.PhotoItemBinding
import java.io.BufferedReader
import java.io.File
import java.io.FileReader


class PhotoAdapter(val context: Context): RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {


    private val photoList = ArrayList<String>()

    inner class ViewHolder(item: View): RecyclerView.ViewHolder(item){

        private val binding = PhotoItemBinding.bind(item)

        fun bind(data:String){
            binding.photoText.text = data
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photoList[position])
    }

    override fun getItemCount(): Int {
        return photoList.size
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setDate(){
        val file = File(context.getExternalFilesDir("photos"), "date.txt")
        if (file.exists()) {
            photoList.clear()
            val reader = BufferedReader(FileReader(file))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                photoList.add(line!!)
            }
            reader.close()
            notifyDataSetChanged()
        }
    }

}