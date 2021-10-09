package com.nikitabolshakov.weather.data.repository.weather

import com.nikitabolshakov.weather.data.models.response.WeatherDTO
import com.nikitabolshakov.weather.data.repository.remote.RemoteDataSource
import retrofit2.Callback

class WeatherRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : WeatherRepository {
    override fun getWeatherDetailsFromServer(
        lat: Double,
        lon: Double,
        callback: Callback<WeatherDTO>
    ) {
        remoteDataSource.getWeatherDetails(lat, lon, callback)
    }
}