package com.nikitabolshakov.weather.presentation.adapters.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikitabolshakov.weather.data.model.Weather
import com.nikitabolshakov.weather.databinding.ItemHomeRecyclerViewBinding

class HomeFragmentAdapter : RecyclerView.Adapter<HomeFragmentAdapter.HomeViewHolder>() {

    inner class HomeViewHolder(
        private val binding: ItemHomeRecyclerViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(weather: Weather) {
            binding.apply {
                itemHomeRecyclerView.text = weather.city.city
                root.setOnClickListener {
                    onItemViewClickListener(weather)
                }
            }
        }
    }

    private var weatherData: List<Weather> = listOf()
    private var onItemViewClickListener: (Weather) -> Unit = {}

    fun setOnItemViewClickListener(onItemViewClickListener: (Weather) -> Unit) {
        this.onItemViewClickListener = onItemViewClickListener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setWeather(data: List<Weather>) {
        weatherData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemHomeRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(weatherData[position])
    }

    override fun getItemCount() = weatherData.size
}