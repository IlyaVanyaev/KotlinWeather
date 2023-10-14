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
import com.example.kotlinweather.databinding.ForecastItemBinding
import com.squareup.picasso.Picasso

class WeatherAdapter: ListAdapter<WeatherModel, WeatherAdapter.ViewHolder>(Comparator()) {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ForecastItemBinding.bind(view)

        @SuppressLint("SetTextI18n")
        fun bind(weatherModel: WeatherModel) = with(binding){
            hourDay.text = weatherModel.hours
            maxMin.text = "${weatherModel.maxTemperature}/${weatherModel.minTemperature}"
            Picasso.get().load("https" + weatherModel.weatherImage).into(conditionImage)
            condition.text = weatherModel.condition
            temperature.text = weatherModel.recentTemperature
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.forecast_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}