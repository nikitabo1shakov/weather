package com.nikitabolshakov.weather.data.repository.main

import com.nikitabolshakov.weather.data.models.local.Weather

interface MainRepository {

    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorageRus(): List<Weather>
    fun getWeatherFromLocalStorageWorld(): List<Weather>
}