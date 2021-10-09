package com.nikitabolshakov.weather.data.repository.weather

import com.nikitabolshakov.weather.data.models.response.WeatherDTO
import retrofit2.Callback

interface WeatherRepository {
    fun getWeatherDetailsFromServer(
        lat: Double,
        lon: Double,
        callback: Callback<WeatherDTO>
    )
}