package com.nikitabolshakov.weather.model.repository.details

import com.nikitabolshakov.weather.model.dto.WeatherDTO
import retrofit2.Callback

interface DetailsRepository {
    fun getWeatherDetailsFromServer(
        lat: Double,
        lon: Double,
        callback: Callback<WeatherDTO>
    )
}