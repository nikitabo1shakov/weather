package com.nikitabolshakov.weather.model.repository.main

import com.nikitabolshakov.weather.model.data.Weather

interface MainRepository {

    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorageRus(): List<Weather>
    fun getWeatherFromLocalStorageWorld(): List<Weather>
}