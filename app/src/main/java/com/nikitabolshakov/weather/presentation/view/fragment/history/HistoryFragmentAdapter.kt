package com.nikitabolshakov.weather.presentation.view.fragment.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikitabolshakov.weather.data.model.Weather
import com.nikitabolshakov.weather.databinding.ItemHistoryRecyclerViewBinding

class HistoryFragmentAdapter : RecyclerView.Adapter<HistoryFragmentAdapter.HistoryViewHolder>() {

    inner class HistoryViewHolder(
        private val binding: ItemHistoryRecyclerViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Weather) {
            binding.apply {
                cityName.text = data.city.city
                weatherCondition.text = data.condition
                weatherTemperature.text = data.temperature.toString()
            }
        }
    }

    private var data: List<Weather> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Weather>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}