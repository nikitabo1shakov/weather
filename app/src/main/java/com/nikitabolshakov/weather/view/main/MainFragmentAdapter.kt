package com.nikitabolshakov.weather.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikitabolshakov.weather.databinding.RecyclerItemMainBinding
import com.nikitabolshakov.weather.model.data.Weather

class MainFragmentAdapter : RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {

    private var weatherData: List<Weather> = listOf()
    private var onItemViewClickListener: (Weather) -> Unit = {}

    fun setOnItemViewClickListener(onItemViewClickListener: (Weather) -> Unit) {
        this.onItemViewClickListener = onItemViewClickListener
    }

    fun setWeather(data: List<Weather>) {
        weatherData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = RecyclerItemMainBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(weatherData[position])
    }

    override fun getItemCount(): Int {
        return weatherData.size
    }

    inner class MainViewHolder(private val binding: RecyclerItemMainBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(weather: Weather) {
            binding.apply {
                mainFragmentRecyclerItemTextView.text = weather.city.city
                root.setOnClickListener {
                    onItemViewClickListener(weather)
                }
            }
        }
    }
}