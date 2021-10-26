package com.nikitabolshakov.weather.data.repository.remote

import com.nikitabolshakov.weather.data.model.response.WeatherDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("v2/informers")
    fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Single<WeatherDTO>
}