package com.nikitabolshakov.weather.presentation.view.fragment.citylist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikitabolshakov.weather.data.models.local.Weather
import com.nikitabolshakov.weather.databinding.ItemCityListRecyclerViewBinding

class CityListFragmentAdapter : RecyclerView.Adapter<CityListFragmentAdapter.CityListViewHolder>() {

    inner class CityListViewHolder(
        private val binding: ItemCityListRecyclerViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(weather: Weather) {
            binding.apply {
                itemCityListRecyclerView.text = weather.city.city
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityListViewHolder {
        val binding = ItemCityListRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CityListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityListViewHolder, position: Int) {
        holder.bind(weatherData[position])
    }

    override fun getItemCount() = weatherData.size
}