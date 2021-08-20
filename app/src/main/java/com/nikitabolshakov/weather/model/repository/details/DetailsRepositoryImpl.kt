package com.nikitabolshakov.weather.model.repository.details

import com.nikitabolshakov.weather.model.dto.WeatherDTO
import com.nikitabolshakov.weather.model.repository.RemoteDataSource
import retrofit2.Callback

class DetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource) : DetailsRepository {
    override fun getWeatherDetailsFromServer(
        lat: Double,
        lon: Double,
        callback: Callback<WeatherDTO>
    ) {
        remoteDataSource.getWeatherDetails(lat, lon, callback)
    }
}