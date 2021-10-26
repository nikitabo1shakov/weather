package com.nikitabolshakov.weather.data.repository.weather

import com.nikitabolshakov.weather.data.model.response.WeatherDTO
import io.reactivex.rxjava3.core.Single

interface WeatherRepository {
    fun getWeatherDetailsFromServer(
        lat: Double,
        lon: Double,
    ): Single<WeatherDTO>
}