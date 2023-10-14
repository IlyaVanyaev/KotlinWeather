package com.example.kotlinweather.data.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinweather.R
import com.example.kotlinweather.data.model.WeatherModel
import com.example.kotlinweather.databinding.ForecastDaysItemBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class WeatherDaysAdapter: ListAdapter<WeatherModel, WeatherDaysAdapter.ViewHolder>(Comparator()) {


    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ForecastDaysItemBinding.bind(view)

        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        fun bind(weatherModel: WeatherModel) = with(binding){
            hourDay.text = weatherModel.date.drop(8) + ", ${LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM"))}"
            Picasso.get().load("https:" + weatherModel.weatherImage).into(conditionImage)
            condition.text = weatherModel.condition
            temperature.text = "${weatherModel.maxTemperature.dropLast(2)}/${weatherModel.minTemperature.dropLast(2)}\u00B0"
        }
    }




    class Comparator: DiffUtil.ItemCallback<WeatherModel>(){
        override fun areItemsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDaysAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.forecast_days_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}