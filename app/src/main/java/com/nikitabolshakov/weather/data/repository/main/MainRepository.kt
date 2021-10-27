package com.nikitabolshakov.weather.data.repository.main

import com.nikitabolshakov.weather.data.model.Weather

interface MainRepository {

    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorageRus(): MutableList<Weather>
    fun getWeatherFromLocalStorageWorld(): MutableList<Weather>
}